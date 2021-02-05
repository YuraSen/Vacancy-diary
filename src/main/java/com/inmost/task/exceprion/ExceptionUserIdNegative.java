package com.inmost.task.exceprion;

public class ExceptionUserIdNegative extends RuntimeException {
    public ExceptionUserIdNegative() {

    }

    public ExceptionUserIdNegative(String message) {
        super(message);
    }

    public ExceptionUserIdNegative(String message, Throwable cause) {
        super(message, cause);
    }

}
