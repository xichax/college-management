package com.bharath.college_management.Exceptions;

public class BranchInvalidException extends RuntimeException {
    public BranchInvalidException(String message) {
        super(message);
    }

    public BranchInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
