package com.jh.covid.bullet.repository;

import com.jh.covid.bullet.domain.Bullet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Bullet 관련 스프링 데이터 JPA.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

public interface BulletRepository extends JpaRepository<Bullet, Long> {
}
