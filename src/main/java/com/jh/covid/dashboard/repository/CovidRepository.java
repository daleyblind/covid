package com.jh.covid.dashboard.repository;

import com.jh.covid.dashboard.domain.Covid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidRepository extends JpaRepository<Covid, Long> {
}
