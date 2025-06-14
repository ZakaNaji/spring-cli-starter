package com.znaji.clistarter.cli.service;

import com.znaji.clistarter.cli.core.CommandContext;

public interface ArgsParserStrategy {

    boolean supports(String[] token);
    CommandContext parse(String[] token);
}
