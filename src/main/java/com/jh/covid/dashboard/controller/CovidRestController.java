package com.jh.covid.dashboard.controller;

import com.jh.covid.dashboard.service.CovidService;
import com.jh.covid.dashboard.vo.CovidVO;
import com.jh.covid.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Covid 관련 컨트롤러
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@CrossOrigin(origins = "http://localhost:8081")
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@RestController
public class CovidRestController {

    private final CovidService covidService;

    /**
     * Covid 정보를 보여주는 응답 메서드.
     *
     * @return Covid 엔티티 VO
     * @throws NoDataException 데이터베이스에 데이터가 하나도 없을 때 발생하는 에러 클래스
     */
    @GetMapping
    public ResponseEntity<CovidVO> findLastCovid() throws NoDataException {
        CovidVO vo = covidService.findLastCovid().orElseThrow(NoDataException::new);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

}
