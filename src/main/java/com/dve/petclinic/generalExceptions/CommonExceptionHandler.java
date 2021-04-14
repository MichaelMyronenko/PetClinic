package com.dve.petclinic.generalExceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponseDto handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        return ImmutableErrorResponseDto.builder()
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponseDto handleConflictException(ConflictException exception) {
        log.error(exception.getMessage());
        return ImmutableErrorResponseDto.builder()
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponseDto handleInvalidCredentialsException(InvalidCredentialsException exception) {
        log.error(exception.getMessage());
        return ImmutableErrorResponseDto.builder()
                .message(exception.getMessage()).build();
    }
}
