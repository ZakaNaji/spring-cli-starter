package com.znaji.clistarter.cli;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class CliRunner implements ApplicationContextAware {

    List<ResolvedCommand> commands = new ArrayList<>();
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("Initializing CLI Runner...");
        Map<String, Object> cliCommandBeans = applicationContext.getBeansWithAnnotation(CLICommand.class);

        for (Object bean : cliCommandBeans.values()) {
            CLICommand meta = bean.getClass().getAnnotation(CLICommand.class);
            String name = meta.name();
            String description = meta.description();
            Consumer<CommandContext> executor;

            if (bean instanceof ConsoleCommand consoleCommand) {
                executor = consoleCommand::execute;
            } else {
                executor = ctx -> {
                    try {
                        bean.getClass().getMethod("execute", CommandContext.class)
                                .invoke(bean, ctx);
                    } catch (Exception e) {
                        throw new RuntimeException("Error invoking command: " + name, e);
                    }
                };
            }
            commands.add(new ResolvedCommand(name, description, executor));
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            for (int i = 0; i < commands.size(); i++) {
                System.out.printf("[%d] %s — %s%n", i + 1, commands.get(i).name(), commands.get(i).description());
            }

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("Exiting CLI...");
                break;
            }

            String commandName = input.split("\\s+")[0];
            CommandContext context = ArgsParser.parse(input);

            List<ResolvedCommand> resolvedCommands = commands.stream()
                    .filter(command -> command.name().equals(commandName))
                    .toList();

            if (resolvedCommands.isEmpty()) {
                System.out.println("Unknown command: " + commandName);
            } else {
                resolvedCommands.get(0).run(context);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
