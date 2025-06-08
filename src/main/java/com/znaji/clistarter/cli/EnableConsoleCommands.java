package com.znaji.clistarter.cli;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ConsoleCommandRegistrar.class)
public @interface EnableConsoleCommands {
}
