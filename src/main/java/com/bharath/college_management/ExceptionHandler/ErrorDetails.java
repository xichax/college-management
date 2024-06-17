package com.bharath.college_management.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private int statusCode;
    private String message;
    private String Details;

}
