package com.inmost.task.exceprion;

public class ExceptionUserNotExist extends RuntimeException {
    public ExceptionUserNotExist() {

    }

    public ExceptionUserNotExist(String message) {
        super(message);
    }

    public ExceptionUserNotExist(String message, Throwable cause) {
        super(message, cause);
    }

}
