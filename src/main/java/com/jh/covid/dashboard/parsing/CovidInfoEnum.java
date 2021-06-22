package com.jh.covid.dashboard.parsing;

import com.jh.covid.dashboard.vo.CovidInfoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Arrays;

@Log
@Getter
@AllArgsConstructor
public enum CovidInfoEnum implements WordAnalysis {

    ACC_DEF_RATE("accDefRate", (value, vo) -> vo.setAccDefRate(Double.parseDouble(value))),
    ACC_EXAM_CNT("accExamCnt", (value, vo) -> vo.setAccExamCnt(Integer.parseInt(value))),
    ACC_EXAM_COMP_CNT("accExamCompCnt", (value, vo) -> vo.setAccExamCompCnt(Integer.parseInt(value))),
    CARE_CNT("careCnt", (value, vo) -> vo.setCareCnt(Integer.parseInt(value))),
    CLEAR_CNT("clearCnt", (value, vo) -> vo.setClearCnt(Integer.parseInt(value))),
    CREATE_DT("createDt", (value, vo) -> vo.setCreateDt(value)),
    DEATH_CNT("deathCnt", (value, vo) -> vo.setDeathCnt(Integer.parseInt(value))),
    DECIDE_CNT("decideCnt", (value, vo) -> vo.setDecideCnt(Integer.parseInt(value))),
    EXAM_CNT("examCnt", (value, vo) -> vo.setExamCnt(Integer.parseInt(value))),
    RESULT_NEG_CNT("resutlNegCnt", (value, vo) -> vo.setResutlNegCnt(Integer.parseInt(value))),
    SEQ("seq", (value, vo) -> vo.setSeq(Long.parseLong(value))),
    STATE_DT("stateDt", (value, vo) -> vo.setStateDt(value)),
    STATE_TIME("stateTime", (value, vo) -> vo.setStateTime(value)),
    UPDATE_DT("updateDt", (value, vo) -> vo.setUpdateDt(value)),
    NOT_DEFINE("", (value, vo) -> log.info("NOT_DEFINE : 알맞은 태그(필드명)이(가) 존재하지 않습니다."));

    private final String tagName;
    private final WordAnalysis wordAnalysis;

    @Override
    public void analysis(String value, CovidInfoVO vo) {
        wordAnalysis.analysis(value, vo);
    }

    private static CovidInfoEnum findAnalysis(String tagName) {
        return Arrays.stream(CovidInfoEnum.values())
                .filter(e -> e.getTagName().equals(tagName))
                .findAny()
                .orElse(NOT_DEFINE);
    }

    public static void setValueToCovidInfoByTagName(String tagName, String value, CovidInfoVO vo) {
        if (value.equals("null")) value = null;
        findAnalysis(tagName).analysis(value, vo);
    }

}
