package org.world3.habit3.exception;

public class Habit3Exception extends RuntimeException {

    private ErrorCode errorCode;

    public Habit3Exception(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public Habit3Exception(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
