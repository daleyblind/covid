package com.jh.covid.bullet.service;

import com.jh.covid.bullet.domain.Bullet;
import com.jh.covid.bullet.repository.BulletQueryRepository;
import com.jh.covid.bullet.repository.BulletRepository;
import com.jh.covid.bullet.vo.BulletVO;
import com.jh.covid.exception.NoDataException;
import com.jh.covid.paging.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 건의 게시판 관련 서비스 클래스.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.1
 */

@RequiredArgsConstructor
@Service
public class BulletService {

    private final BulletRepository repository;
    private final BulletQueryRepository queryRepository;

    /**
     * 게시글 리스트를 가져오는 메서드. (페이징 처리 추가)
     *
     * @param pageDTO 페이징 정보를 담고 있는 객체.
     * @return 게시글 리스트와 페이징 정보를 담고 있는 객체.
     * @since 1.1
     */
    public PageDTO<BulletVO> findBulletList(PageDTO<BulletVO> pageDTO) {
        pageDTO.setContent(queryRepository.findBulletList(pageDTO));
        pageDTO.setTotal(repository.count());
        return pageDTO;
    }

    public BulletVO findBullet(Long no) {
        return queryRepository.findBullet(no);
    }

    /**
     * 게시글 추가 메서드.
     *
     * @param vo 게시글 번호
     * @return 게시글이 추가됐는지 확인하는 boolean
     * @since 1.1
     */
    @Transactional
    public boolean insertBullet(BulletVO vo) {
        Bullet bullet = new Bullet();
        bullet.setTitle(vo.getTitle());
        bullet.setWriter(vo.getWriter());
        bullet.setContent(vo.getContent());
        return repository.save(bullet).getNo() > 0;
    }

    /**
     * 게시글 삭제 메서드.
     *
     * @param no 게시글 번호
     * @return 게시글이 삭제됐는지 확인하는 boolean
     * @since 1.1
     */
    @Transactional
    public boolean deleteBullet(Long no) {
        boolean flag = false;
        try {
            repository.deleteById(no);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 게시글 수정 메서드.
     *
     * @param vo 건의 게시판 VO
     * @return 게시글이 수정됐는지 확인하는 boolean
     * @since 1.1
     */
    @Transactional
    public boolean modifyBullet(BulletVO vo) {
        boolean flag = false;
        Optional<Bullet> optionalBullet = repository.findById(vo.getNo());
        optionalBullet.ifPresent(bullet -> {
            bullet.setTitle(vo.getTitle());
            bullet.setWriter(vo.getWriter());
            bullet.setContent(vo.getContent());
        });
        try {
            Bullet bullet = optionalBullet.orElseThrow(NoDataException::new);
            repository.save(bullet);
            flag = true;
        } catch (NoDataException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
