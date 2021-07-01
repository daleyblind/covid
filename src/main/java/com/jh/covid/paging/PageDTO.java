package com.jh.covid.paging;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 페이징 클래스.
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 * @param <T> BulletVO
 */

@Getter @Setter
@ToString
public class PageDTO<T> {

    private int page = 1;
    private int size = 10;
    @Setter(AccessLevel.NONE)
    private int total;
    private List<T> content;

    /**
     * 토탈 페이지를 구하는 메서드.
     *
     * @param count 전체 리스트 수.
     * @since 1.0
     */
    public void setTotal(long count) {
        this.total = (int) Math.ceil((double) count / this.size);
    }

    /**
     * 데이터베이스에서 제한 할 Offset 을 구하는 메서드.
     *
     * @return offset 숫자.
     */
    public int calculateOffset() {
        return (page - 1) * size;
    }

}
