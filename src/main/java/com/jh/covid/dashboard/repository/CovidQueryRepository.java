package com.jh.covid.dashboard.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CovidQueryRepository {

    private final JPAQueryFactory queryFactory;

}
