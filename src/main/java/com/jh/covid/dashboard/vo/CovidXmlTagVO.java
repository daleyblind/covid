package com.jh.covid.dashboard.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 공공데이터 API 의 태그들을 파싱하기 위한 VO
 *
 * @author Jang Hyun
 * @since 1.0
 * @version 1.1
 */

@ToString
@Getter
@Setter
public class CovidXmlTagVO {

    private Long seq;               // pk             ex) 74
    private String stateDt;         // 기준일          ex) 20200315
    private int decideCnt;          // 확진자 수       ex) 8162
    private int clearCnt;           // 격리해제 수     ex) 834
    private int examCnt;            // 검사진행 수     ex) 16272
    private int deathCnt;           // 사망자 수       ex) 75
    private int careCnt;            // 치료중 환자 수   ex) 7253
    private int resutlNegCnt;       // 결과 음성 수    ex) 243778
    private int accExamCnt;         // 누적 검사 수    ex) 251940
    private int accExamCompCnt;     // 누적검사완료 수  ex) 251940


}
