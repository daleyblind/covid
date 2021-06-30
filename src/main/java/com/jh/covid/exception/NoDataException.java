package com.jh.covid.exception;

/**
 * 데이터베이스에 데이터가 없을 때, 발생하는 에러 클래스
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

public class NoDataException extends Exception {

    public NoDataException() {
        super("데이터베이스에 존재하지 않는 데이터입니다.");
    }

}
