package com.bloom.challenge.mathsservice.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiValidationException.class)
    protected ResponseEntity<Object> handleApiValidation(ApiValidationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HandledError error = new HandledError(status.toString(), exception.toString());
        return new ResponseEntity<>(error, status);
    }

    public static class HandledError {
        private final String type;
        private final String message;

        public HandledError(String type, String message) {
            this.type = type;
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }
    }

}
