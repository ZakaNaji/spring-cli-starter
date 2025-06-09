package com.znaji.clistarter.cli;

import jakarta.annotation.PostConstruct;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class CliRunner {

    private final CommandDiscoverer commandDiscoverer;
    private final CommandUI commandUI;
    private CommandDispatcher commandDispatcher;

    public CliRunner(CommandDiscoverer commandDiscoverer, CommandUI commandUI) {
        this.commandDiscoverer = commandDiscoverer;
        this.commandUI = commandUI;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing CLI Runner...");
        commandDispatcher = new CommandDispatcher(commandDiscoverer.discover());
    }


    public void run() {
        while (true) {
            commandUI.welcome(commandDispatcher);
            String input = commandUI.readInput().trim();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting CLI...");
                break;
            }

            CommandContext commandContext = ArgsParser.parse(input);
            ResolvedCommand resolvedCommand = commandDispatcher.resolvedCommand(commandContext.getCommandName());

            if (resolvedCommand == null) {
                System.out.println("Unknown command: " + commandContext.getCommandName());
            } else {
                resolvedCommand.run(commandContext);
            }
        }
    }

}
