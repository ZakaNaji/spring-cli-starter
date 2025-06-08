package com.znaji.clistarter.cli;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface CLICommand {
    String name();
    String description();
    String help() default "";
}
