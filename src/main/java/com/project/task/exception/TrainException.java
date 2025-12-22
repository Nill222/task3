package com.project.task.exception;

public class TrainException extends Exception {

    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainException(Throwable cause) {
        super(cause);
    }
}
