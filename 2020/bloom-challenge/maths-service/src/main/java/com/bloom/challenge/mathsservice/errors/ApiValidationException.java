package com.bloom.challenge.mathsservice.errors;

public class ApiValidationException extends RuntimeException {

    private final String field;

    public ApiValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    @Override
    public String toString() {
        return String.format("Field %s is invalid! %s", field, super.getMessage());
    }
}
