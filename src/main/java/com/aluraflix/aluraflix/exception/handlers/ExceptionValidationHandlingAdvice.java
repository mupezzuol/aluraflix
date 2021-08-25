package com.aluraflix.aluraflix.exception.handlers;

import com.aluraflix.aluraflix.exception.handlers.model.ExceptionErrorResponse;
import com.aluraflix.aluraflix.exception.handlers.model.ValidationErrorResponse;
import com.aluraflix.aluraflix.exception.handlers.model.ViolationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ExceptionValidationHandlingAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ValidationErrorResponse> handlerConstraintValidationException(final ConstraintViolationException ex) {
        Set<ViolationError> violationErrorList = new HashSet<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            violationErrorList.add(new ViolationError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return this.handleErrorValidation(HttpStatus.BAD_REQUEST, violationErrorList);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ValidationErrorResponse> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        Set<ViolationError> violationErrorList = new HashSet<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            violationErrorList.add(new ViolationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return this.handleErrorValidation(HttpStatus.BAD_REQUEST, violationErrorList);
    }

    /**
     * The method builds an error message about Validation object {@link ValidationErrorResponse} according to
     * the values received by the parameters/validations.
     *
     * @param status             Exception generated HttpStatus.
     * @param violationErrorList List containing all validation errors.
     * @return ValidationErrorResponse with the values validated.
     */
    private ResponseEntity<ValidationErrorResponse> handleErrorValidation(final HttpStatus status, final Set<ViolationError> violationErrorList) {
        return new ResponseEntity<>(ValidationErrorResponse.builder()
                .violationErrors(violationErrorList)
                .info(ExceptionErrorResponse.builder()
                        .statusCode(status.value())
                        .statusName(status.name())
                        .date(LocalDateTime.now().toString())
                        .build())
                .build(), status);
    }

}
