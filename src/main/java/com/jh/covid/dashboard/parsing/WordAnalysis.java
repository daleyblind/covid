package com.jh.covid.dashboard.parsing;

import com.jh.covid.dashboard.vo.CovidInfoVO;

@FunctionalInterface
public interface WordAnalysis {

    void analysis(String value, CovidInfoVO vo);

}
