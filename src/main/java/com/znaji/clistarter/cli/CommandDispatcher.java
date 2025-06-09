package com.znaji.clistarter.cli;

import java.util.List;

public class CommandDispatcher {
    private List<ResolvedCommand> commands;

    public CommandDispatcher(List<ResolvedCommand> commands) {
        this.commands = commands;
    }

    public ResolvedCommand resolvedCommand(String name) {
        return commands.stream()
                .filter(cmd -> cmd.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<ResolvedCommand> getCommands() {
        return commands;
    }
}
