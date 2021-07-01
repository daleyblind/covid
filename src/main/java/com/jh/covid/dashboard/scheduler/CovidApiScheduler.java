package com.jh.covid.dashboard.scheduler;

import com.jh.covid.dashboard.domain.Covid;
import com.jh.covid.dashboard.parsing.CovidXmlTagParser;
import com.jh.covid.dashboard.repository.CovidRepository;
import com.jh.covid.dashboard.vo.CovidXmlTagVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 공공데이터 API 의 XML 데이터를 받기 위해 HTTP 통신하는 클래스.
 * 해당 클래스는 정해진 시간마다 스케줄링.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.3
 */

@Log
@RequiredArgsConstructor
@Component
public class CovidApiScheduler {

    private final CovidRepository covidRepository;

    private static String initPrefix;
    private static String startDatePrefix;
    private static String endDatePrefix;

    static {
        try {
            initPrefix = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" +
                    "?" + URLEncoder.encode("ServiceKey", "UTF-8") +
                    "=RoJPxbJ22lkQO2sh7Oi1r9l%2BgjfLpfzXF9IjjEM%2FZyw3Uyf3QS%2FpEMnBP7TG5INi1uyZ3zpKbwCVApwmkfOcdQ%3D%3D" + /*Service Key*/
                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지번호*/
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            startDatePrefix = "&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=";
            endDatePrefix = "&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 특정 시간마다 공공데이터 API 통신으로 XML 데이터를 받아오는 메서드.
     * 데이터는 코로나 관련 정보를 담고 있음. ex) 확진자 수, 자가격리 수 등등
     *
     * @since 1.3
     */
    @Scheduled(cron = "0 0/30 9-18 * * *")
    private void transferCovidAPI() {
        HttpURLConnection conn = null;
        BufferedReader rd = null;
        try {
            URL url = new URL(makeFullUrlString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            int responseCode = conn.getResponseCode();
            log.info("응답 코드: " + responseCode);
            if (responseCode >= 200 && responseCode <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            String xmlString = sb.toString();
            CovidXmlTagVO vo = parsingToObjectFromXmlString(xmlString);
            if (vo == null) {
                log.info("Covid API 데이터가 아직 준비되지 않았습니다.");
                return;
            }
            saveCovid(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 실제 엔티티에 저장하는 메서드.
     *
     * @param vo 실제 엔티티에 저장하는 파싱 된 객체.
     * @since 1.0
     */
    private void saveCovid(CovidXmlTagVO vo) {
        Covid covid = new Covid();
        covid.setSeq(vo.getSeq());
        covid.setStateDt(vo.getStateDt());
        covid.setDecideCnt(vo.getDecideCnt());
        covid.setClearCnt(vo.getClearCnt());
        covid.setExamCnt(vo.getExamCnt());
        covid.setDeathCnt(vo.getDeathCnt());
        covid.setCareCnt(vo.getCareCnt());
        covid.setResultNegCnt(vo.getResutlNegCnt());
        covid.setAccExamCnt(vo.getAccExamCnt());
        covid.setAccExamCompCnt(vo.getAccExamCompCnt());
        covidRepository.save(covid);
    }

    /**
     * 오늘 날짜에 해당하는 쿼리스트링을 만들어 전체 URL 을 완성하는 메서드.
     *
     * @return 공공데이터 API 에 통신하는 URL 문자열.
     * @since 1.0
     */
    private String makeFullUrlString() {
        String now = LocalDate.now().toString().replace("-", "");   /* ex) 20210622 */
        String queryString = null;
        try {
            queryString = URLEncoder.encode(now, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return initPrefix + startDatePrefix + queryString + endDatePrefix + queryString;
    }

    /**
     * XML 형식의 문자열을 객체로 파싱해주는 메서드.
     *
     * @param xmlString XML 형식의 문자열.
     * @return XML 데이터가 파싱 된 객체.
     * @since 1.0
     */
    private CovidXmlTagVO parsingToObjectFromXmlString(String xmlString) {
        if (!containsTag(xmlString)) return null;
        CovidXmlTagVO vo = new CovidXmlTagVO();
        Field[] fields = CovidXmlTagVO.class.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            String tagName = f.getName();
            String startTag = "<" + tagName + ">";
            String endTag = "</" + tagName + ">";
            int startIndex = xmlString.indexOf(startTag);
            int endIndex = xmlString.indexOf(endTag);
            String value = xmlString.substring(startIndex + startTag.length(), endIndex);
            // XML 데이터를 객체로 파싱
            CovidXmlTagParser.setValueToCovidInfoByTagName(tagName, value, vo);
        });
        return vo;
    }

    /**
     * XML 데이터가 존재하면 seq 태그는 무조건 있으므로, 존재하는 지 체크하느 메서드.
     * (정보 : 데이터가 없으면 seq 태그가 존재하지 않으며, seq 는 해당 데이터의 PK 값을 나타냄.)
     *
     * @param xmlString XML 형식의 문자열.
     * @return seq 라는 태그가 존재하는 지에 대한 조건식.
     * @since 1.0
     */
    private boolean containsTag(String xmlString) {
        return xmlString.contains("<seq>");
    }

}
