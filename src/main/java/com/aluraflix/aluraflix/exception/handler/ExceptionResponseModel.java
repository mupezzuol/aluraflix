package com.aluraflix.aluraflix.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseModel {
    private int statusCode;
    private String statusName;
    private String message;
    private String date;
}
