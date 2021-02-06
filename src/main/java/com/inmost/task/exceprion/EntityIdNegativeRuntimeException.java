package com.inmost.task.exceprion;

public class EntityIdNegativeRuntimeException extends RuntimeException {
    public EntityIdNegativeRuntimeException() {

    }

    public EntityIdNegativeRuntimeException(String message) {
        super(message);
    }

    public EntityIdNegativeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
