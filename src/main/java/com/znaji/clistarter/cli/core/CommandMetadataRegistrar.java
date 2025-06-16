package com.znaji.clistarter.cli.core;

import com.znaji.clistarter.cli.annotation.CLIArg;
import com.znaji.clistarter.cli.annotation.CLICommand;
import com.znaji.clistarter.cli.annotation.CLIFlag;
import com.znaji.clistarter.cli.utils.Utils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandMetadataRegistrar implements BeanPostProcessor {

    private final CommandRegistry registry;

    public CommandMetadataRegistrar(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (!(bean instanceof TypedCommand<?>)) return bean;

        CLICommand cmdMeta = bean.getClass().getAnnotation(CLICommand.class);
        if (cmdMeta == null) return bean;
        String name = cmdMeta.name();
        String description = cmdMeta.description();

        List<ArgumentDescriptor> arguments = new ArrayList<>();
        Class<?> commandArgsType = Utils.getCommandArgsType(bean);
        for (Field field : commandArgsType.getDeclaredFields()) {
            if (field.isAnnotationPresent(CLIArg.class)) {
                CLIArg cliArg = field.getAnnotation(CLIArg.class);
                arguments.add(new ArgumentDescriptor(cliArg.value(), cliArg.description(), cliArg.required(), false));
            }
            if (field.isAnnotationPresent(CLIFlag.class)) {
                CLIFlag flag = field.getAnnotation(CLIFlag.class);
                arguments.add(new ArgumentDescriptor(flag.value(), flag.description(), false, true));
            }
        }

        registry.register(new CommandDescriptor(name, description, bean, arguments));
        return bean;
    }
}
