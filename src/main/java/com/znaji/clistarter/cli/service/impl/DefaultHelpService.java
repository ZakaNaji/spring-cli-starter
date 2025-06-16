package com.znaji.clistarter.cli.service.impl;

import com.znaji.clistarter.cli.core.ArgumentDescriptor;
import com.znaji.clistarter.cli.core.CommandDescriptor;
import com.znaji.clistarter.cli.core.CommandRegistry;
import com.znaji.clistarter.cli.io.OutputTarget;
import com.znaji.clistarter.cli.service.HelpService;

import java.util.Optional;

public class DefaultHelpService implements HelpService {
    private final OutputTarget out;
    private final CommandRegistry registry;

    public DefaultHelpService(OutputTarget outputTarget, CommandRegistry registry) {
        this.out = outputTarget;
        this.registry = registry;
    }

    @Override
    public void printGeneralHelp() {
        out.print("Available Commands:");
        for (CommandDescriptor cmd : registry.getAll()) {
            out.print(String.format("  %s - %s", cmd.name(), cmd.description()));
        }
        out.print("Use '--help' with a command or 'help <command>' for command details.");
    }

    @Override
    public void printCommandHelp(String name) {
        if (!registry.has(name)) {
            out.error("Unknown command: " + name);
            return;
        }
        Optional<CommandDescriptor> optional = registry.get(name);
        CommandDescriptor cmd = optional.get();
        out.print(cmd.name() + " - " + cmd.description());

        StringBuilder usage = new StringBuilder("  " + cmd.name());
        for (ArgumentDescriptor arg : cmd.arguments()) {
            if (arg.flag()) {
                usage.append(" [--").append(arg.name()).append("]");
            } else if (arg.required()) {
                usage.append(" --").append(arg.name()).append(" <value>");
            } else {
                usage.append(" [--").append(arg.name()).append(" <value>]");
            }
        }
        out.print("Usage:");
        out.print(usage.toString());

        out.print("\nOptions:");
        for (ArgumentDescriptor arg : cmd.arguments()) {
            String type = arg.flag() ? "(flag)" : (arg.required() ? "(required)" : "(optional)");
            out.print(String.format("  --%-12s %s %s", arg.name(), type, arg.description()));
        }
    }
}
