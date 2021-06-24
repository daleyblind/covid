package com.jh.covid.dashboard.service;

import com.jh.covid.dashboard.repository.CovidQueryRepository;
import com.jh.covid.dashboard.vo.CovidVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Covid 서비스 영역 클래스
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.1
 */

@RequiredArgsConstructor
@Service
public class CovidService {

    private final CovidQueryRepository queryRepository;

    public List<CovidVO> findCovidList() {
        return queryRepository.findCovidList();
    }

}
