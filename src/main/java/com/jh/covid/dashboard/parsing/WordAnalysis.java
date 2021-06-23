package com.jh.covid.dashboard.parsing;

import com.jh.covid.dashboard.vo.CovidXmlTagVO;

@FunctionalInterface
public interface WordAnalysis {

    void analysis(String value, CovidXmlTagVO vo);

}
