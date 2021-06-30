package com.jh.covid.bullet.service;

import com.jh.covid.bullet.domain.Bullet;
import com.jh.covid.bullet.repository.BulletQueryRepository;
import com.jh.covid.bullet.repository.BulletRepository;
import com.jh.covid.bullet.vo.BulletVO;
import com.jh.covid.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 건의 게시판 관련 서비스 클래스.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@RequiredArgsConstructor
@Service
public class BulletService {

    private final BulletRepository repository;
    private final BulletQueryRepository queryRepository;

    public List<BulletVO> findBulletList() {
        return queryRepository.findBulletList();
    }

    public BulletVO findBullet(Long no) {
        return queryRepository.findBullet(no);
    }

    public boolean insertBullet(BulletVO vo) {
        Bullet bullet = new Bullet();
        bullet.setTitle(vo.getTitle());
        bullet.setWriter(vo.getWriter());
        bullet.setContent(vo.getContent());
        return repository.save(bullet).getNo() > 0;
    }

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
