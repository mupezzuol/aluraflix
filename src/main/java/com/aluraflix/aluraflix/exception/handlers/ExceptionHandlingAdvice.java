package com.aluraflix.aluraflix.exception.handlers;

import com.aluraflix.aluraflix.exception.ListOfVideoNotFoundException;
import com.aluraflix.aluraflix.exception.VideoAlreadyExistException;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.exception.handlers.model.ExceptionErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(VideoAlreadyExistException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerVideoAlreadyExistException(final VideoAlreadyExistException ex) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ListOfVideoNotFoundException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerListOfVideoNotFoundException(final ListOfVideoNotFoundException ex) {
        return this.handleError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(VideoNotFoundException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerVideoNotFoundException(final VideoNotFoundException ex) {
        return this.handleError(HttpStatus.NOT_FOUND, ex);
    }

    /**
     * The method builds an error message object {@link ExceptionErrorResponse} according to
     * the values received by the parameter.
     *
     * @param status Exception generated HttpStatus.
     * @param ex     Throwable class extended.
     * @return ExceptionResponseModel with the values received through the parameter.
     */
    private ResponseEntity<ExceptionErrorResponse> handleError(final HttpStatus status, final Throwable ex) {
        log.error("Caught {}", ex.getClass().getSimpleName(), ex);
        return new ResponseEntity<>(ExceptionErrorResponse.builder()
                .statusCode(status.value())
                .statusName(status.name())
                .message(ex.getMessage())
                .date(LocalDateTime.now().toString())
                .build(), status);
    }

}
