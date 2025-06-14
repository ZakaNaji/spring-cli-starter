package com.znaji.clistarter.cli.exception;

import com.znaji.clistarter.cli.core.CommandContext;
import com.znaji.clistarter.cli.io.OutputTarget;

public class DefaultCLIExceptionHandler implements CLIExceptionHandler{

    private final OutputTarget outputTarget;

    public DefaultCLIExceptionHandler(OutputTarget outputTarget) {
        this.outputTarget = outputTarget;
    }

    @Override
    public void handle(Exception e, CommandContext commandContext) {
        outputTarget.error("Error: " + e.getMessage());
    }
}
