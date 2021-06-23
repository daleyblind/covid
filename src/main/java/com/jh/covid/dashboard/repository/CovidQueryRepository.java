package com.jh.covid.dashboard.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Covid 관련 QueryDsl 를 이용하여 검색 동적 쿼리를 하기 위한 클래스
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@RequiredArgsConstructor
@Repository
public class CovidQueryRepository {

    private final JPAQueryFactory queryFactory;

}
