package com.znaji.clistarter.cli.parser;

import java.util.ListIterator;

public interface TokenParser {
    boolean supports(Token token);
    void parse(Token token, ListIterator<Token> tokens, ParseState state);
}
