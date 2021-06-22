package com.jh.covid.dashboard.scheduler;

import com.jh.covid.dashboard.domain.Covid;
import com.jh.covid.dashboard.parsing.CovidInfoEnum;
import com.jh.covid.dashboard.repository.CovidRepository;
import com.jh.covid.dashboard.vo.CovidInfoVO;
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

//    @Scheduled(fixedDelay = 5000)
    @Scheduled(cron = "0 0 0/2 * * *")
    private void saveCovidInformation() throws IOException {
        URL url = new URL(makeFullUrlString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try (AutoCloseable ignored = conn::disconnect) {
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            log.info("응답 코드: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            String xmlString = sb.toString();
            CovidInfoVO vo = parsingToObjectFromXmlString(xmlString);
            if (vo == null) {
                log.info("Covid API 데이터가 아직 준비되지 않았습니다.");
                return;
            }
            Covid covid = new Covid();
            covid.setSeq(vo.getSeq());
            covid.setStateDt(vo.getStateDt());
            covid.setStateTime(vo.getStateTime());
            covid.setDecideCnt(vo.getDecideCnt());
            covid.setClearCnt(vo.getClearCnt());
            covid.setExamCnt(vo.getExamCnt());
            covid.setDeathCnt(vo.getDeathCnt());
            covid.setCareCnt(vo.getCareCnt());
            covid.setResultNegCnt(vo.getResutlNegCnt());
            covid.setAccExamCnt(vo.getAccExamCnt());
            covid.setAccExamCompCnt(vo.getAccExamCompCnt());
            covid.setAccDefRate(vo.getAccDefRate());
            covid.setCreateDt(vo.getCreateDt());
            covid.setUpdateDt(vo.getUpdateDt());
            covidRepository.save(covid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeFullUrlString() {
        String now = LocalDate.now().toString().replace("-", "");   /* ex) 20210622 */
        String queryString = null;
        try {
            queryString = URLEncoder.encode("20210902", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return initPrefix + startDatePrefix + queryString + endDatePrefix + queryString;
    }

    private CovidInfoVO parsingToObjectFromXmlString(String xmlString) {
        if (!containsTag(xmlString)) return null;
        CovidInfoVO vo = new CovidInfoVO();
        Field[] fields = CovidInfoVO.class.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            String tagName = f.getName();
            String startTag = "<" + tagName + ">";
            String endTag = "</" + tagName + ">";
            int startIndex = xmlString.indexOf(startTag);
            int endIndex = xmlString.indexOf(endTag);
            String value = xmlString.substring(startIndex + startTag.length(), endIndex);
            // XML 데이터를 CovidInfoVO 객체로 파싱
            CovidInfoEnum.setValueToCovidInfoByTagName(tagName, value, vo);
        });
        return vo;
    }

    private boolean containsTag(String xmlString) {
        return xmlString.contains("<seq>");
    }

}
