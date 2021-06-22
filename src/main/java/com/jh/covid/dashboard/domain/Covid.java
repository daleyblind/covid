package com.jh.covid.dashboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@ToString
@Table(name = "tbl_covid")
@Entity
public class Covid {

    @Id
    private Long seq;               // pk             ex) 74
    @Column(name = "state_dt", length = 30)
    private String stateDt;         // 기준일          ex) 20200315
    @Column(name = "state_time", length = 30)
    private String stateTime;       // 기준시간        ex) 00:00
    @Column(name = "decide_cnt")
    private int decideCnt;          // 확진자 수       ex) 8162
    @Column(name = "clear_cnt")
    private int clearCnt;           // 격리해제 수     ex) 834
    @Column(name = "exam_cnt")
    private int examCnt;            // 검사진행 수     ex) 16272
    @Column(name = "death_cnt")
    private int deathCnt;           // 사망자 수       ex) 75
    @Column(name = "care_cnt")
    private int careCnt;            // 치료중 환자 수   ex) 7253
    @Column(name = "result_neg_cnt")
    private int resultNegCnt;       // 결과 음성 수    ex) 243778
    @Column(name = "acc_exam_cnt")
    private int accExamCnt;         // 누적 검사 수    ex) 251940
    @Column(name = "acc_exam_comp_cnt")
    private int accExamCompCnt;     // 누적검사완료 수  ex) 251940
    @Column(name = "acc_def_rate")
    private double accDefRate;      // 누적 확진률     ex) 3.2396602365
    @Column(name = "create_dt", length = 30)
    private String createDt;        // 등록일시분초    ex) 2020-03-15 10:01:22.000
    @Column(name = "update_dt", length = 30)
    private String updateDt;        // 수정일시분초    ex) null or 2020-03-15 14:01:22.000

}
