package com.znaji.clistarter.cli.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ConsoleCommandRegistrar.class, CLIConfig.class})
public @interface EnableConsoleCommands {
}
