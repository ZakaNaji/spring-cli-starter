package com.znaji.clistarter.cli.service.impl;

import com.znaji.clistarter.cli.core.CommandContext;
import com.znaji.clistarter.cli.exception.CLIException;
import com.znaji.clistarter.cli.parser.ParseState;
import com.znaji.clistarter.cli.parser.Token;
import com.znaji.clistarter.cli.parser.TokenParser;
import com.znaji.clistarter.cli.parser.Tokenizer;
import com.znaji.clistarter.cli.service.ArgsParserService;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArgsParserServiceImpl implements ArgsParserService {
    private final Tokenizer tokenizer;
    private final List<TokenParser> parsers;

    public ArgsParserServiceImpl(Tokenizer tokenizer, List<TokenParser> parsers) {
        this.tokenizer = tokenizer;
        this.parsers = parsers;
    }

    @Override
    public CommandContext parse(String input) {
        List<Token> tokens = tokenizer.tokenize(input);
        ParseState state = new ParseState();
        ListIterator<Token> tokenIterator = tokens.listIterator();

        if (tokens.isEmpty()) {
            throw new CLIException("No input provided.");
        }

        while(tokenIterator.hasNext()) {
            Token token = tokenIterator.next();
            for (TokenParser parser : parsers) {
                if (parser.supports(token)) {
                    parser.parse(token, tokenIterator, state);
                    break;
                }
            }
        }

        return state.toCommandContext();
    }
}
