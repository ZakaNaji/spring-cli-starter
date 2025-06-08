package com.znaji.clistarter.cli;

public interface ConsoleCommand {
    String name();
    String description();
    void execute(CommandContext ctx);
}
