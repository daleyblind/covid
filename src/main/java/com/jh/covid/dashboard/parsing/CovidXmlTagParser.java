package com.jh.covid.dashboard.parsing;

import com.jh.covid.dashboard.vo.CovidXmlTagVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.Arrays;

/**
 * 공공데이터 API 에서 받은 XML 문자열을 CovidXmlTagVO 로 파싱하기 위한 Enum
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@Log
@Getter
@AllArgsConstructor
public enum CovidXmlTagParser implements ValueInjector {

    ACC_EXAM_CNT("accExamCnt", (value, vo) -> vo.setAccExamCnt(Integer.parseInt(value))),
    ACC_EXAM_COMP_CNT("accExamCompCnt", (value, vo) -> vo.setAccExamCompCnt(Integer.parseInt(value))),
    CARE_CNT("careCnt", (value, vo) -> vo.setCareCnt(Integer.parseInt(value))),
    CLEAR_CNT("clearCnt", (value, vo) -> vo.setClearCnt(Integer.parseInt(value))),
    DEATH_CNT("deathCnt", (value, vo) -> vo.setDeathCnt(Integer.parseInt(value))),
    DECIDE_CNT("decideCnt", (value, vo) -> vo.setDecideCnt(Integer.parseInt(value))),
    EXAM_CNT("examCnt", (value, vo) -> vo.setExamCnt(Integer.parseInt(value))),
    RESULT_NEG_CNT("resutlNegCnt", (value, vo) -> vo.setResutlNegCnt(Integer.parseInt(value))),
    SEQ("seq", (value, vo) -> vo.setSeq(Long.parseLong(value))),
    STATE_DT("stateDt", (value, vo) -> vo.setStateDt(value)),
    NOT_DEFINE("", (value, vo) -> log.info("NOT_DEFINE : 알맞은 태그(필드명)이(가) 존재하지 않습니다."));

    private final String tagName;
    private final ValueInjector valueInjector;

    /**
     * 함수형 인터페이스를 이용하여
     * 각각의 태그의 값들을 알맞은 멤버 변수에 주입하는 메서드.
     * 내부적으로는 Enum 안에 있는 람다식이 실행.
     *
     * @param value 공공데이터 API 의 태그의 Value
     * @param vo 공공데이터 API 의 태그들을 파싱하기 위한 VO
     * @since 1.0
     * @see ValueInjector
     */
    @Override
    public void setValue(String value, CovidXmlTagVO vo) {
        valueInjector.setValue(value, vo);
    }

    /**
     * XML 태그명에 해당하는 열거형을 반환 하는 메서드.
     * 정확히는 해당하는 열거형의 람다식을 실행시키기 위함임.
     *
     * @param tagName 공공데이터 API 의 태그명
     * @return 태그 명에 해당하는 열거형을 반환.
     * @since 1.0
     */
    private static CovidXmlTagParser findByTagName(String tagName) {
        return Arrays.stream(CovidXmlTagParser.values())
                .filter(e -> e.getTagName().equals(tagName))
                .findAny()
                .orElse(NOT_DEFINE);
    }

    /**
     * 파싱할 객체에 태그 명을 분석해
     * 알맞은 멤버 변수에 파라미터로 받은 value 를 주입시키는 메서드.
     *
     * @param tagName 공공데이터 API 의 태그명
     * @param value 공공데이터 API 의 태그의 Value
     * @param vo 공공데이터 API 의 태그들을 파싱하기 위한 VO
     * @since 1.0
     */
    public static void setValueToCovidInfoByTagName(String tagName, String value, CovidXmlTagVO vo) {
        if (value.equals("null")) value = null;
        findByTagName(tagName).setValue(value, vo);
    }

}
