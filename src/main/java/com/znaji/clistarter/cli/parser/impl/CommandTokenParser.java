package com.znaji.clistarter.cli.parser.impl;

import com.znaji.clistarter.cli.parser.ParseState;
import com.znaji.clistarter.cli.parser.Token;
import com.znaji.clistarter.cli.parser.TokenParser;
import com.znaji.clistarter.cli.parser.TokenType;

import java.util.ListIterator;

public class CommandTokenParser implements TokenParser {
    @Override
    public boolean supports(Token token) {
        return token.type().equals(TokenType.COMMAND);
    }

    @Override
    public void parse(Token token, ListIterator<Token> tokens, ParseState state) {
        state.setCommandName(token.text());
    }
}
