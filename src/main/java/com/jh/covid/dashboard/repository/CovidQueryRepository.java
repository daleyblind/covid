package com.jh.covid.dashboard.repository;

import com.jh.covid.dashboard.vo.CovidVO;
import com.jh.covid.dashboard.vo.QCovidVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.jh.covid.dashboard.domain.QCovid.covid;

/**
 * Covid 관련 QueryDsl 를 이용하여 검색 동적 쿼리를 하기 위한 클래스
 *
 * @author Jang Hyun
 * @version 1.0
 * @since 1.1
 */

@RequiredArgsConstructor
@Repository
public class CovidQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<CovidVO> findLastCovid() {
        return Optional.ofNullable(
                queryFactory.select(new QCovidVO(
                        covid.seq,
                        covid.stateDt,
                        covid.decideCnt,
                        covid.clearCnt,
                        covid.examCnt,
                        covid.deathCnt,
                        covid.careCnt,
                        covid.resultNegCnt,
                        covid.accExamCnt,
                        covid.accExamCompCnt
                ))
                        .from(covid)
                        .where(covid.seq.gt(0))
                        .orderBy(covid.seq.desc())
                        .limit(1)
                        .fetchOne());
    }

}
