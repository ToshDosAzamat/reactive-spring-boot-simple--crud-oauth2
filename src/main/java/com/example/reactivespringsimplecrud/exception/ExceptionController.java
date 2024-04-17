package com.example.reactivespringsimplecrud.exception;


import com.example.reactivespringsimplecrud.dto.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ExceptionMessage> exceptionRuntime(RuntimeException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .date(new Date())
                .message(exception.getMessage())
                .build();
        return Mono.just(exceptionMessage);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ExceptionMessage> exceptionAny (Exception exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .date(new Date())
                .message(exception.getMessage())
                .build();
        return Mono.just(exceptionMessage);
    }
}
