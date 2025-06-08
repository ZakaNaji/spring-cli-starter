package com.znaji.clistarter;

import com.znaji.clistarter.cli.CLICommand;
import com.znaji.clistarter.cli.CommandContext;
import com.znaji.clistarter.cli.ConsoleCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@CLICommand(
        name = "myCommand",
        description = "This is a sample command.",
        help = "Usage: myCommand [options]"
)
public class MyCommand implements ConsoleCommand {
    @Override
    public String name() {
        return "myCommand";
    }

    @Override
    public String description() {
        return "This is a sample command.";
    }

    @Override
    public void execute(CommandContext ctx) {
        System.out.println("Executing myCommand with context: " + ctx);
        // Add your command logic here
        System.out.println("raw args: " + ctx.getArgs());
        System.out.println("named args: " + ctx.getNamedArgs());
    }
}
