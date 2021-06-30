package com.jh.covid.bullet.controller;

import com.jh.covid.bullet.service.BulletService;
import com.jh.covid.bullet.vo.BulletVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 건의게시판 관련 컨트롤러.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/bullet")
@RestController
public class BulletRestController {

    private final BulletService service;

    @GetMapping
    public ResponseEntity<List<BulletVO>> findBulletList() {
        return new ResponseEntity<>(service.findBulletList(), HttpStatus.OK);
    }

    @GetMapping("/{no}")
    public ResponseEntity<BulletVO> findBullet(@PathVariable("no") Long no) {
        return new ResponseEntity<>(service.findBullet(no), HttpStatus.OK);
    }

    @PostMapping
    public boolean writeBullet(@RequestBody BulletVO vo) {
        return service.insertBullet(vo);
    }

    @DeleteMapping("/{no}")
    public boolean removeBullet(@PathVariable("no") Long no) {
        return service.deleteBullet(no);
    }

    @PutMapping
    public boolean modifyBullet(@RequestBody BulletVO vo) {
        return service.modifyBullet(vo);
    }

}
