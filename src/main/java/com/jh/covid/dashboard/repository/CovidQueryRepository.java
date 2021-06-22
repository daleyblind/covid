package com.jh.covid.dashboard.repository;

import com.jh.covid.dashboard.domain.Covid;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jh.covid.dashboard.domain.QCovid.covid;

@RequiredArgsConstructor
@Repository
public class CovidQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Covid> findById(Long seq) {
        return queryFactory.selectFrom(covid)
                .where(covid.seq.eq(seq))
                .fetch();
    }

}
