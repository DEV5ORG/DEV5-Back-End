package com.dev5.backenddev5.Exception;

// Custom exception
public class PasswordChangeException extends RuntimeException {
    public PasswordChangeException(String message) {
        super(message);
    }
}
