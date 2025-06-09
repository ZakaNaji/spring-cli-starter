package com.znaji.clistarter.cli;

import java.util.List;
import java.util.Map;

public class CommandContext {
    private final List<String> rawArgs;
    private final Map<String, String> namedArgs;
    private final String CommandName;

    public CommandContext(List<String> rawArgs, Map<String, String> namedArgs, String commandName) {
        this.rawArgs = rawArgs;
        this.namedArgs = namedArgs;
        CommandName = commandName;
    }

    public List<String> getArgs() {
        return rawArgs;
    }

    public Map<String, String> getNamedArgs() {
        return namedArgs;
    }

    public String get(String name) {
        return namedArgs.get(name);
    }

    public boolean has(String name) {
        return namedArgs.containsKey(name);
    }

    public String getCommandName() {
        return CommandName;
    }
}
