package com.dve.petclinic.generalExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class CommonExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    private final MessageSource messageSource;

    public CommonExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponseDto handleNotFoundException(NotFoundException exception, Locale locale) {
        log.error(exception.getDetails());
        return ImmutableErrorResponseDto.builder()
                .message(messageSource.getMessage(exception.getMessageCode(), exception.getArgs(), locale))
                .detail(exception.getDetails())
                .build();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponseDto handleConflictException(ConflictException exception, Locale locale) {
        log.error(exception.getDetails());
        return ImmutableErrorResponseDto.builder()
                .message(messageSource.getMessage(exception.getMessageCode(), exception.getArgs(), locale))
                .detail(exception.getDetails())
                .build();
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponseDto handleInvalidCredentialsException(InvalidCredentialsException exception, Locale locale) {
        log.error(exception.getDetails());
        return ImmutableErrorResponseDto.builder()
                .message(messageSource.getMessage(exception.getMessageCode(), exception.getArgs(), locale))
                .detail(exception.getDetails())
                .build();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponseDto handleInvalidCredentialsException(ForbiddenException exception, Locale locale) {
        log.error(exception.getDetails());
        return ImmutableErrorResponseDto.builder()
                .message(messageSource.getMessage(exception.getMessageCode(), exception.getArgs(), locale))
                .detail(exception.getDetails())
                .build();
    }
}
