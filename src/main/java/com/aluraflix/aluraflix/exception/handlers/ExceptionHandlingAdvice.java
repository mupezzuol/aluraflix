package com.aluraflix.aluraflix.exception.handlers;

import com.aluraflix.aluraflix.exception.*;
import com.aluraflix.aluraflix.exception.handlers.model.ExceptionErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerEntityNotFoundException(final EntityNotFoundException ex) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(CategoryContainVideosException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerCategoryContainVideosException(final CategoryContainVideosException ex) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ListOfCategoryNotFoundException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerListOfCategoryNotFoundException(final ListOfCategoryNotFoundException ex) {
        return this.handleError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerCategoryNotFoundException(final CategoryNotFoundException ex) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public final ResponseEntity<ExceptionErrorResponse> handlerInvalidDataAccessApiUsageException(final InvalidDataAccessApiUsageException ex) {
        return this.handleError(HttpStatus.BAD_REQUEST, ex);
    }

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
     * @return ExceptionErrorResponse with the values received through the parameter.
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
