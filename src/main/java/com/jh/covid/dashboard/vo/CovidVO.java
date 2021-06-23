package com.jh.covid.dashboard.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.ToString;

/**
 * Covid 엔티티 VO
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@ToString
public class CovidVO {

    private final Long seq;               // pk             ex) 74
    private final String stateDt;         // 기준일          ex) 20200315
    private String stateTime;       // 기준시간        ex) 00:00
    private final int decideCnt;          // 확진자 수       ex) 8162
    private final int clearCnt;           // 격리해제 수     ex) 834
    private int examCnt;            // 검사진행 수     ex) 16272
    private final int deathCnt;           // 사망자 수       ex) 75
    private int careCnt;            // 치료중 환자 수   ex) 7253
    private int resultNegCnt;       // 결과 음성 수    ex) 243778
    private int accExamCnt;         // 누적 검사 수    ex) 251940
    private int accExamCompCnt;     // 누적검사완료 수  ex) 251940
    private double accDefRate;      // 누적 확진률     ex) 3.2396602365
    private String createDt;        // 등록일시분초    ex) 2020-03-15 10:01:22.000
    private String updateDt;        // 수정일시분초    ex) null or 2020-03-15 14:01:22.000

    @QueryProjection
    public CovidVO(Long seq, int decideCnt, int clearCnt, int deathCnt, String stateDt) {
        this.seq = seq;
        this.decideCnt = decideCnt;
        this.clearCnt = clearCnt;
        this.deathCnt = deathCnt;
        this.stateDt = stateDt;
    }

}
