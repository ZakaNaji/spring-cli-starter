package com.znaji.clistarter.cli.config;

import com.znaji.clistarter.cli.service.ArgsParserService;
import com.znaji.clistarter.cli.service.ArgsParserServiceImpl;
import com.znaji.clistarter.cli.service.ArgsParserStrategy;
import com.znaji.clistarter.cli.service.DefaultArgsParserStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class CLIConfig {
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public ArgsParserStrategy defaultParsingStrategy() {
        return new DefaultArgsParserStrategy();
    }

    @Bean
    public ArgsParserService argsParserService(List<ArgsParserStrategy> strategies) {
        return new ArgsParserServiceImpl(strategies);
    }
}
