package com.dve.petclinic.generalExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

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

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponseDto handleInvalidCredentialsException(ForbiddenException exception) {
        log.error(exception.getMessage());
        return ImmutableErrorResponseDto.builder()
                .message(exception.getMessage()).build();
    }
}
