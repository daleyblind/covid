package com.jh.covid.dashboard.controller;

import com.jh.covid.dashboard.service.CovidService;
import com.jh.covid.dashboard.vo.CovidVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Covid 관련 컨트롤러
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.2
 */

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@RestController
public class CovidRestController {

    private final CovidService covidService;

    /**
     * Covid 정보를 보여주는 응답 메서드.
     *
     * @return Covid 엔티티 VO
     */
    @GetMapping
    public ResponseEntity<List<CovidVO>> findCovidList() {
        List<CovidVO> vo = covidService.findCovidList();
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

}
