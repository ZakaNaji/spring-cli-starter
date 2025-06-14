package com.znaji.clistarter.cli.config;

import com.znaji.clistarter.cli.parser.TokenParser;
import com.znaji.clistarter.cli.parser.Tokenizer;
import com.znaji.clistarter.cli.parser.impl.CommandTokenParser;
import com.znaji.clistarter.cli.parser.impl.LongOptionTokenParser;
import com.znaji.clistarter.cli.parser.impl.ValueTokenParser;
import com.znaji.clistarter.cli.service.ArgsParserService;
import com.znaji.clistarter.cli.service.ArgsParserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CLIConfig {

    @Bean
    public Tokenizer tokenizer() {
        return new Tokenizer();
    }

    @Bean
    public TokenParser valueTokenParser() {
        return new ValueTokenParser();
    }

    @Bean
    public TokenParser commandTokenParser() {
        return new CommandTokenParser();
    }

    @Bean
    public TokenParser longOptionTokenParser() {
        return new LongOptionTokenParser();
    }

    @Bean
    public ArgsParserService argsParserService(Tokenizer tokenizer, List<TokenParser> parsers) {
        return new ArgsParserServiceImpl(tokenizer, parsers);
    }
}
