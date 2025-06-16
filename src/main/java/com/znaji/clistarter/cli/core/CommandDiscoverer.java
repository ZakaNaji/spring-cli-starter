package com.znaji.clistarter.cli.core;

import com.znaji.clistarter.cli.annotation.CLICommand;
import com.znaji.clistarter.cli.utils.Utils;
import jdk.jshell.execution.Util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CommandDiscoverer implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final CommandArgsBinder commandArgsBinder;

    public CommandDiscoverer(CommandArgsBinder commandArgsBinder) {
        this.commandArgsBinder = commandArgsBinder;
    }

    public List<ResolvedCommand> discover() {
        List<ResolvedCommand> resolvedCommands = new ArrayList<>();
        Map<String, Object> cliBeans = applicationContext.getBeansWithAnnotation(CLICommand.class);
        for (Object bean : cliBeans.values()) {
            if (bean instanceof TypedCommand<?>) {
                CLICommand annotation = bean.getClass().getAnnotation(CLICommand.class);

                Consumer<CommandContext> executor = commandContext -> {
                    Class<?> commandArgsType = Utils.getCommandArgsType(bean);
                    Object parsedArgs = commandArgsBinder.bind(commandArgsType, commandContext);
                    ((TypedCommand<Object>)bean).execute(parsedArgs);
                };

                resolvedCommands.add(new ResolvedCommand(
                        annotation.name(),
                        annotation.description(),
                        executor));
            }
        }
        return resolvedCommands;
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
