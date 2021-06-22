package com.jh.covid;

import com.jh.covid.dashboard.parsing.CovidInfoEnum;
import com.jh.covid.dashboard.vo.CovidInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootTest
class CovidApplicationTests {

    private static String prefix;
    private static String prefix1;
    private static String prefix2;

    static {
        try {
            prefix = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" + "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=RoJPxbJ22lkQO2sh7Oi1r9l%2BgjfLpfzXF9IjjEM%2FZyw3Uyf3QS%2FpEMnBP7TG5INi1uyZ3zpKbwCVApwmkfOcdQ%3D%3D" + /*Service Key*/
                    "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + /*페이지번호*/
                    "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
            prefix1 = "&" + URLEncoder.encode("startCreateDt", "UTF-8") + "=";
            prefix2 = "&" + URLEncoder.encode("endCreateDt", "UTF-8") + "=";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void contextLoads() throws IOException {
        String now = LocalDate.now().toString().replace("-", "");
        String queryString = URLEncoder.encode(now, "UTF-8");
        String urlString = prefix + /*한 페이지 결과 수*/
                prefix1 + queryString + /*검색할 생성일 범위의 시작*/
                prefix2 + queryString;/*URL*//*검색할 생성일 범위의 종료*/
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
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
        conn.disconnect();
        showFieldName(sb.toString());
    }

    @Test
    void showFieldName(String xmlString) {
        CovidInfoVO vo = new CovidInfoVO();
        Field[] fields = CovidInfoVO.class.getDeclaredFields();
        Arrays.stream(fields).forEach(f -> {
            String tagName = f.getName();
            String firstTag = "<" + tagName + ">";
            String lastTag = "</" + tagName + ">";
            int index = xmlString.indexOf(firstTag);
            int index2 = xmlString.indexOf(lastTag);
            String value = xmlString.substring(index + firstTag.length(), index2);

            CovidInfoEnum.setValueToCovidInfoByTagName(tagName, value, vo);
        });
        System.out.println("covidInfoVO : " + vo);
    }

}
