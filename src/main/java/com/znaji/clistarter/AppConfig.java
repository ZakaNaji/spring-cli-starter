package com.znaji.clistarter;

import com.znaji.clistarter.cli.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.znaji.clistarter")
@EnableConsoleCommands
public class AppConfig {

    @Bean
    public CliRunner cliRunner() {
        return new CliRunner();
    }
}
