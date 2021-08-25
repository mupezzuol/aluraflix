package com.aluraflix.aluraflix.exception.handlers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private Set<ViolationError> violationErrors = new HashSet<>();
    @JsonIgnoreProperties(value = {"message"})
    private ExceptionErrorResponse info;
}
