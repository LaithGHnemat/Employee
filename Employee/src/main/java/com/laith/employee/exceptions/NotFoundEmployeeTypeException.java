package com.laith.employee.exceptions;

public class NotFoundEmployeeTypeException extends RuntimeException {
    public NotFoundEmployeeTypeException(String message) {
        super(message);
    }
}
