package com.jh.covid.dashboard.parsing;

import com.jh.covid.dashboard.vo.CovidXmlTagVO;

/**
 * CovidXmlTagVO 객체의 멤버필드에
 * XML 의 각각의 태그들을 파싱(Setter)하기 위해 만든 함수형 인터페이스.
 * 
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@FunctionalInterface
public interface ValueInjector {

    void setValue(String value, CovidXmlTagVO vo);

}
