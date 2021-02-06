package com.inmost.task.exceprion;

public class UserIsAlsoExistRuntimeException extends RuntimeException{

    public UserIsAlsoExistRuntimeException() {
    }

    public UserIsAlsoExistRuntimeException(String message) {
        super(message);
    }


    public UserIsAlsoExistRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
