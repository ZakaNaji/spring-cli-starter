package com.znaji.clistarter.cli.exception;

public class CLIException extends RuntimeException{
    public CLIException(String message) {
        super(message);
    }

    public CLIException(String message, Throwable cause) {
        super(message, cause);
    }
}
