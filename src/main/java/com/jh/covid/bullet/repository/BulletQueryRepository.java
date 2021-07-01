package com.jh.covid.bullet.repository;

import com.jh.covid.bullet.vo.BulletVO;
import com.jh.covid.bullet.vo.QBulletVO;
import com.jh.covid.paging.PageDTO;
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
 * @version 1.1
 */

@RequiredArgsConstructor
@Repository
public class BulletQueryRepository {

    private final JPQLQueryFactory queryFactory;

    /**
     * 게시판 리스트들을 반환하는 Querydsl. (페이징 처리 추가)
     *
     * @param pageDTO 페이징 시 필요한 정보를 가지고 있는 객체.
     * @return 게시판 리스트.
     * @since 1.1
     */
    public List<BulletVO> findBulletList(PageDTO<BulletVO> pageDTO) {
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
                .offset(pageDTO.calculateOffset())
                .limit(pageDTO.getSize())
                .fetch();
    }

    /**
     * 상세 페이지를 구하는 Querydsl.
     *
     * @param no 게시판 번호.
     * @return 해당 상세 페이지.
     * @since 1.1
     */
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
