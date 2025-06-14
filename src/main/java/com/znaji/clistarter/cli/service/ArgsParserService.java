package com.znaji.clistarter.cli.service;

import com.znaji.clistarter.cli.core.CommandContext;

public interface ArgsParserService {
    CommandContext parse(String input);
}
