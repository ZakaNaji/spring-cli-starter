package com.znaji.clistarter.cli;

import com.znaji.clistarter.cli.core.CommandContext;
import com.znaji.clistarter.cli.core.CommandDiscoverer;
import com.znaji.clistarter.cli.core.CommandDispatcher;
import com.znaji.clistarter.cli.core.ResolvedCommand;
import com.znaji.clistarter.cli.exception.CLIExceptionHandler;
import com.znaji.clistarter.cli.exception.UnknownCommandException;
import com.znaji.clistarter.cli.service.ArgsParserService;
import jakarta.annotation.PostConstruct;

public class CliRunner {

    private final CommandDiscoverer commandDiscoverer;
    private final CommandUI commandUI;
    private final CLIExceptionHandler handler;
    private final ArgsParserService argsParserService;
    private CommandDispatcher commandDispatcher;

    public CliRunner(CommandDiscoverer commandDiscoverer, CommandUI commandUI, CLIExceptionHandler handler, ArgsParserService argsParserService) {
        this.commandDiscoverer = commandDiscoverer;
        this.commandUI = commandUI;
        this.handler = handler;
        this.argsParserService = argsParserService;
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
                commandUI.printToOutput("Exiting CLI...");
                break;
            }

            CommandContext commandContext = null;
            try {
                commandContext = argsParserService.parse(input);
                ResolvedCommand resolvedCommand = commandDispatcher.resolvedCommand(commandContext.getCommandName());

                if (resolvedCommand == null) {
                    throw new UnknownCommandException(commandContext.getCommandName());
                } else {
                    resolvedCommand.run(commandContext);
                }
            } catch (Exception e) {
                handler.handle(e, commandContext);
            }
        }
    }

}
