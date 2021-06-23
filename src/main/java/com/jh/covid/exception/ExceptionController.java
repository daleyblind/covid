package com.jh.covid.exception;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 각종 에러 핸들러 및 컨트롤러 클래스
 *
 * @since 1.0
 * @author Jang Hyun
 * @version 1.0
 */

@ControllerAdvice
@Log
public class ExceptionController {

    /**
     * 데이터가 null 일 경우, 에러 핸들러
     *
     * @param ex 데이터베이스에 데이터가 하나도 없을 때 발생하는 에러 클래스
     * @return 에러 메시지
     * @see NoDataException
     */
    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<String> handleNoDataException(final NoDataException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
