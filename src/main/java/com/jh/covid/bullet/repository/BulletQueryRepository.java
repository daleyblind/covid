package com.jh.covid.bullet.repository;

import com.jh.covid.bullet.vo.BulletVO;
import com.jh.covid.bullet.vo.QBulletVO;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jh.covid.bullet.domain.QBullet.bullet;

/**
 * 건의게시판 관련 Querydsl 레파지토리.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@RequiredArgsConstructor
@Repository
public class BulletQueryRepository {

    private final JPQLQueryFactory queryFactory;

    public List<BulletVO> findBulletList() {
        return queryFactory.select(new QBulletVO(
                bullet.no,
                bullet.title,
                bullet.writer,
                bullet.createDay,
                bullet.updateDay
        ))
                .from(bullet)
                .where(bullet.no.gt(0))
                .orderBy(bullet.no.desc())
                .fetch();
    }

    public BulletVO findBullet(Long no) {
        return queryFactory.select(new QBulletVO(
                bullet.no,
                bullet.title,
                bullet.writer,
                bullet.content,
                bullet.createDay,
                bullet.updateDay
        ))
                .from(bullet)
                .where(bullet.no.eq(no))
                .fetchOne();
    }

}
