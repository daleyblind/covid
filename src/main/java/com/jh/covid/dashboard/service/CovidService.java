package com.jh.covid.dashboard.service;

import com.jh.covid.dashboard.repository.CovidQueryRepository;
import com.jh.covid.dashboard.vo.CovidVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Covid 서비스 영역 클래스
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@RequiredArgsConstructor
@Service
public class CovidService {

    private final CovidQueryRepository queryRepository;

    public Optional<CovidVO> findLastCovid() {
        return queryRepository.findLastCovid();
    }

}
