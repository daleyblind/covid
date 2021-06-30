package com.jh.covid.bullet.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 건의 게시판 VO
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@NoArgsConstructor
@Data
public class BulletVO {

    private Long no;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime create;
    private LocalDateTime update;

    @QueryProjection
    public BulletVO(Long no, String title, String writer, LocalDateTime create, LocalDateTime update) {
        this.no = no;
        this.title = title;
        this.writer = writer;
        this.create = create;
        this.update = update;
    }

    @QueryProjection
    public BulletVO(Long no, String title, String writer, String content, LocalDateTime create, LocalDateTime update) {
        this.no = no;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.create = create;
        this.update = update;
    }
}
