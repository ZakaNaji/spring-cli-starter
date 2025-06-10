package com.znaji.clistarter.cli.exception;

public class MissingArgumentException extends CLIException{

    public MissingArgumentException(String arg) {
        super("Missing required argument: " + arg);
    }
}
