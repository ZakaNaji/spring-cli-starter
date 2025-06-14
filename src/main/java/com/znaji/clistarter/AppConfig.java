package com.znaji.clistarter;

import com.znaji.clistarter.cli.config.EnableConsoleCommands;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.znaji.clistarter")
@EnableConsoleCommands
public class AppConfig {

}
