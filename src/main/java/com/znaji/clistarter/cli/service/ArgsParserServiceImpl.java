package com.znaji.clistarter.cli.service;

import com.znaji.clistarter.cli.core.CommandContext;
import com.znaji.clistarter.cli.exception.CLIException;

import java.util.List;

public class ArgsParserServiceImpl implements ArgsParserService {
    private final List<ArgsParserStrategy> argsParserStrategies;

    public ArgsParserServiceImpl(List<ArgsParserStrategy> argsParserStrategies) {
        this.argsParserStrategies = argsParserStrategies;
    }

    @Override
    public CommandContext parse(String input) {
        String[] tokens = input.trim().split("\\s+");

        if (tokens.length == 0) {
            throw new CLIException("No input provided.");
        }

        for (ArgsParserStrategy strategy : argsParserStrategies) {
            if (strategy.supports(tokens)) {
                return strategy.parse(tokens);
            }
        }

        throw new CLIException("No suitable parsing strategy found for input: " + input);
    }
}
