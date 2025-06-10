package com.znaji.clistarter.cli.exception;

import com.znaji.clistarter.cli.CommandContext;

public interface CLIExceptionHandler {
    void handle(Exception e, CommandContext commandContext);
}
