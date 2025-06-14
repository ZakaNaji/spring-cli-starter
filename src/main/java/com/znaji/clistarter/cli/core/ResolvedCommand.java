package com.znaji.clistarter.cli.core;

import java.util.function.Consumer;

public class ResolvedCommand {
    private final String name;
    private final String description;
    private final Consumer<CommandContext> executor;


    public ResolvedCommand(String name, String description, Consumer<CommandContext> executor) {
        this.name = name;
        this.description = description;
        this.executor = executor;
    }

    public void run(CommandContext commandContext) {
        executor.accept(commandContext);
    }

    public String name() {
        return name;
    }
    public String description() {
        return description;
    }
}
