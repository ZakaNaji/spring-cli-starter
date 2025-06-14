package com.znaji.clistarter;

import com.znaji.clistarter.cli.runner.CliRunner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            CliRunner cliRunner = context.getBean(CliRunner.class);

            cliRunner.run();
        }
    }
}
