package com.znaji.clistarter.cli.exception;

public class UnknownCommandException extends CLIException{

    public UnknownCommandException(String message) {
        super("Unknown command: " + message);
    }
}
