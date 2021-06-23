package com.jh.covid.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * QueryDsl 을 쓰기 위해
 * EntityManager 를 스프링이 주입시키도록 설정하고,
 * JPAQueryFactory 를 전역에서 쓰기 위해 빈으로 설정
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

}
