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

}
