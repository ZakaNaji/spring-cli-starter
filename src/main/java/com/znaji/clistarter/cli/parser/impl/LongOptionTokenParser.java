package com.znaji.clistarter.cli.parser.impl;

import com.znaji.clistarter.cli.parser.ParseState;
import com.znaji.clistarter.cli.parser.Token;
import com.znaji.clistarter.cli.parser.TokenParser;
import com.znaji.clistarter.cli.parser.TokenType;

import java.util.ListIterator;

public class LongOptionTokenParser implements TokenParser {
    @Override
    public boolean supports(Token token) {
        return token.type().equals(TokenType.LONG_OPTION);
    }

    @Override
    public void parse(Token token, ListIterator<Token> tokens, ParseState state) {
        String name = token.text();
        String value = "true";

        if (tokens.hasNext()) {
            Token next = tokens.next();
            if (next.type().equals(TokenType.VALUE)) {
                value = next.text();
            } else {
                tokens.previous();
            }
        }
        state.getNamedArgs().put(name, value);
    }
}
