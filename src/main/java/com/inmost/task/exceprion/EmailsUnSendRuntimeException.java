package com.inmost.task.exceprion;

public class EmailsUnSendRuntimeException extends RuntimeException{
    public EmailsUnSendRuntimeException() {
    }

    public EmailsUnSendRuntimeException(String message) {
        super(message);
    }

    public EmailsUnSendRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
