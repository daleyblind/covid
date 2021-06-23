package com.jh.covid.dashboard.repository;

import com.jh.covid.dashboard.domain.Covid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Covid 관련 스프링 데이터 JPA.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

public interface CovidRepository extends JpaRepository<Covid, Long> {
}
