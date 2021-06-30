package com.jh.covid.bullet.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * tbl_bullet 테이블 엔티티
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@Getter @Setter
@ToString
@Table(name = "tbl_bullet")
@Entity
public class Bullet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String title;
    private String writer;
    private String content;
    @CreationTimestamp
    @Column(name = "create_day")
    private LocalDateTime createDay;
    @UpdateTimestamp
    @Column(name = "update_day")
    private LocalDateTime updateDay;

}
