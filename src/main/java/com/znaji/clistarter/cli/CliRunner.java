package com.znaji.clistarter.cli;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class CliRunner implements ApplicationContextAware {

    List<ResolvedCommand> commands = new ArrayList<>();
    private ApplicationContext applicationContext;
    private final CommandArgsBinder commandArgsBinder;

    public CliRunner(CommandArgsBinder commandArgsBinder) {
        this.commandArgsBinder = commandArgsBinder;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing CLI Runner...");
        Map<String, Object> cliCommandBeans = applicationContext.getBeansWithAnnotation(CLICommand.class);

        for (Object bean : cliCommandBeans.values()) {
            if (bean instanceof TyperCommand<?>) {
                CLICommand meta = bean.getClass().getAnnotation(CLICommand.class);
                String name = meta.name();
                String description = meta.description();
                Consumer<CommandContext> executor= commandContext -> {
                    Class<?> type = findGenericType(bean.getClass());
                    Object parsed = commandArgsBinder.bind(type, commandContext);
                    ((TyperCommand<Object>)bean).execute(parsed);
                };
                commands.add(new ResolvedCommand(name, description, executor));
            }

        }
    }

    private Class<?> findGenericType(Class<?> aClass) {
        for (Type type : aClass.getGenericInterfaces()) {
            if (type instanceof ParameterizedType pt && pt.getRawType() == TyperCommand.class) {
                return (Class<?>) pt.getActualTypeArguments()[0];
            }
        }
        throw new IllegalStateException("Cannot determine generic type of TypedCommand");
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
