package com.chatting.room.common.exception;

import com.chatting.room.common.exception.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException e) {
        log.info(String.format("Application Exception!! type : %s", e.getClass().getSimpleName()));

        return ResponseEntity.status(e.getStatus())
                .body(new ErrorResponse(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(String.format("MethodArgumentNotValidException : %s", e));
        String errorCode = ExceptionType.METHOD_ARG_NOT_VALID_EXCEPTION.getErrorCode();
        String message = e.getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity.badRequest().body(new ErrorResponse(errorCode, message));
    }

    // 그 외 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unknownException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();

        log.error(String.format("Unknown Exception !! : %s\n" + "%s:%s:%s", e, stackTrace[0].getClassName(),
                stackTrace[0].getMethodName(), stackTrace[0].getLineNumber()), e);

        String errorCode = ExceptionType.UNKNOWN_EXCEPTION.getErrorCode();
        String message = ExceptionType.UNKNOWN_EXCEPTION.getErrorMessage();
        return ResponseEntity.internalServerError().body(new ErrorResponse(errorCode, message));
    }
}
