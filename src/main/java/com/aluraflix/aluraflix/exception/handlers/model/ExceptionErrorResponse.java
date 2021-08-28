package com.aluraflix.aluraflix.exception.handlers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionErrorResponse {
    private int statusCode;
    private String statusName;
    private String message;
    private String date;
}
