package com.znaji.clistarter.cli.parser;

import com.znaji.clistarter.cli.core.CommandContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseState {
    private String commandName;
    private List<String> rawArgs = new ArrayList<>();
    private Map<String, String> namedArgs = new HashMap<>();

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<String> getRawArgs() {
        return rawArgs;
    }

    public Map<String, String> getNamedArgs() {
        return namedArgs;
    }

    public CommandContext toCommandContext() {
        return new CommandContext(rawArgs, namedArgs, commandName);
    }

}
