package com.znaji.clistarter;

import com.znaji.clistarter.cli.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@ComponentScan(basePackages = "com.znaji.clistarter")
@EnableConsoleCommands
public class AppConfig {

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }

    @Bean
    public CommandArgsBinder commandArgsBinder(ConversionService conversionService) {
        return new CommandArgsBinder(conversionService);
    }

    @Bean
    public CliRunner cliRunner(CommandArgsBinder commandArgsBinder) {
        return new CliRunner(commandArgsBinder);
    }
}
