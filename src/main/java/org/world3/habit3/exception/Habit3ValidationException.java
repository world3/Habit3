package org.world3.habit3.exception;

public class Habit3ValidationException extends Habit3Exception {
    public Habit3ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public Habit3ValidationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}