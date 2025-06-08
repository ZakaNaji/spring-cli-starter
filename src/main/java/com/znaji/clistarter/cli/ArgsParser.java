package com.znaji.clistarter.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsParser {

    public static CommandContext parse(String input) {
        String[] tokens = input.trim().split("\\s+");
        List<String> rawArgs = new ArrayList<>();
        Map<String, String> namedArgs = new HashMap<>();

        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];
            if( token.startsWith("--") && i+1 < tokens.length) {
                namedArgs.put(token.substring(2), tokens[++i]);
            } else {
                rawArgs.add(token);
            }
        }
        return new CommandContext(rawArgs, namedArgs);
    }
}
