package com.bloom.challenge.mathsservice.errors;

public class ApiValidationException extends RuntimeException {

    public ApiValidationException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Validation error! %s", super.getMessage());
    }
}
