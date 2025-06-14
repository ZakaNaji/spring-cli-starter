package com.znaji.clistarter.cli.service;

import com.znaji.clistarter.cli.core.CommandContext;
import com.znaji.clistarter.cli.exception.CLIException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultArgsParserStrategy implements ArgsParserStrategy {
    @Override
    public boolean supports(String[] token) {
        return true;
    }

    @Override
    public CommandContext parse(String[] tokens) {
        List<String> rawArgs = new ArrayList<>();
        Map<String, String> namedArgs = new HashMap<>();
        String commandName = tokens[0];
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.startsWith("--")) {
                String key = token.substring(2);
                if (key.isEmpty()) {
                    throw new CLIException("Invalid named argument: empty key at position " + i);
                }

                if (i+1 < tokens.length && !tokens[i+1].startsWith("--")) {
                    namedArgs.put(key, tokens[++i]);
                } else {
                    namedArgs.put(key, "true");
                }

            } else {
                throw new CLIException("Unsupported arg form, arg should follow the patter: --key value");
            }
        }
        return new CommandContext(rawArgs, namedArgs, commandName);
    }
}
