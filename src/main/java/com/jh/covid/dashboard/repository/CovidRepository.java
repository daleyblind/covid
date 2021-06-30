package com.jh.covid.dashboard.repository;

import com.jh.covid.dashboard.domain.Covid;
import org.springframework.data.repository.CrudRepository;

/**
 * Covid 관련 스프링 데이터 JPA.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.1
 */

public interface CovidRepository extends CrudRepository<Covid, Long> {
}
