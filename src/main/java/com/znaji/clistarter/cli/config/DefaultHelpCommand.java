package com.znaji.clistarter.cli.config;

import com.znaji.clistarter.cli.annotation.CLIArg;
import com.znaji.clistarter.cli.annotation.CLICommand;
import com.znaji.clistarter.cli.core.TypedCommand;
import com.znaji.clistarter.cli.service.HelpService;

@CLICommand(name = "help", description = "Display help information")
public class DefaultHelpCommand implements TypedCommand<DefaultHelpCommand.Args> {

    private final HelpService helpService;

    public DefaultHelpCommand(HelpService helpService) {
        this.helpService = helpService;
    }

    @Override
    public void execute(Args args) {
        if (args.command == null) {
            helpService.printGeneralHelp();
        } else {
            helpService.printCommandHelp(args.command);
        }
    }


    public static class Args {
        @CLIArg(value = "command", description = "Command name", required = false)
        public String command;
    }
}
