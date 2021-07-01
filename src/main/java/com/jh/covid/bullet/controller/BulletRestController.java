package com.jh.covid.bullet.controller;

import com.jh.covid.bullet.service.BulletService;
import com.jh.covid.bullet.vo.BulletVO;
import com.jh.covid.paging.PageDTO;
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
 * @version 1.1
 */

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/bullet")
@RestController
public class BulletRestController {

    private final BulletService service;

    /**
     * 게시글 리스트를 가져오는 컨트롤러 메서드.
     *
     * @param pageDTO 페이징 정보를 담고 있는 객체.
     * @return 시글 리스트와 페이징 정보를 담고 있는 객체.
     * @since 1.1
     */
    @GetMapping
    public ResponseEntity<PageDTO<BulletVO>> findBulletList(PageDTO<BulletVO> pageDTO) {
        return new ResponseEntity<>(service.findBulletList(pageDTO), HttpStatus.OK);
    }

    /**
     * 게시글의 상세 페이지를 가져오는 컨트롤러 메서드.
     *
     * @param no 게시글 번호
     * @return 해당 게시글 VO
     * @since 1.1
     */
    @GetMapping("/{no}")
    public ResponseEntity<BulletVO> findBullet(@PathVariable("no") Long no) {
        return new ResponseEntity<>(service.findBullet(no), HttpStatus.OK);
    }

    /**
     * 게시글 추가 컨트롤러 메서드.
     *
     * @param vo 게시글 VO
     * @return 게시글이 추가됐는지 확인하는 boolean
     * @since 1.1
     */
    @PostMapping
    public boolean writeBullet(@RequestBody BulletVO vo) {
        return service.insertBullet(vo);
    }

    /**
     * 게시글 삭제 컨트롤러 메서드.
     *
     * @param no 게시글 번호
     * @return 게시글이 삭제됐는지 확인하는 boolean
     * @since 1.1
     */
    @DeleteMapping("/{no}")
    public boolean removeBullet(@PathVariable("no") Long no) {
        return service.deleteBullet(no);
    }

    /**
     * 게시글 수정 컨트롤러 메서드.
     *
     * @param vo 건의 게시판 VO
     * @return 게시글이 수정됐는지 확인하는 boolean
     * @since 1.1
     */
    @PutMapping
    public boolean modifyBullet(@RequestBody BulletVO vo) {
        return service.modifyBullet(vo);
    }

}
