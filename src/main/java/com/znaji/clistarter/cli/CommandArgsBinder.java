package com.znaji.clistarter.cli;

import org.springframework.core.convert.ConversionService;

import java.lang.reflect.Field;

public class CommandArgsBinder {

    private final ConversionService conversionService;

    public CommandArgsBinder(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public <T> T bind(Class<T> clazz, CommandContext commandContext) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(CLIArg.class)) {
                    CLIArg cliArg = field.getAnnotation(CLIArg.class);
                    String argName = cliArg.value();
                    String argValue = commandContext.get(argName);

                    if (argValue == null && cliArg.required()) {
                        throw new IllegalArgumentException("Missing required argument: " + argName);
                    }

                    if (argValue != null) {
                        Object converted = conversionService.convert(argValue, field.getType());
                        field.set(instance, converted);
                    }
                    continue;
                }

                if (field.isAnnotationPresent(CLIFlag.class)) {
                    CLIFlag cliFlag = field.getAnnotation(CLIFlag.class);
                    String flagName = cliFlag.value();

                    if (field.getType() != Boolean.class && field.getType() != boolean.class) {
                        throw new IllegalArgumentException("@CLIFlag must be used on a boolean field: " + field.getName());
                    }

                    field.set(instance, commandContext.has(flagName));
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Error binding command arguments to class: " + clazz.getName(), e);
        }
    }
}
