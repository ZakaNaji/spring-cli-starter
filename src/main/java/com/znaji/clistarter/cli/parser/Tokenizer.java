package com.znaji.clistarter.cli.parser;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] parts = input.trim().split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == 0) {
                tokens.add(new Token(TokenType.COMMAND, part));
            } else if (part.startsWith("--")) {
                tokens.add(new Token(TokenType.LONG_OPTION, part.substring(2)));
            } else {
                tokens.add(new Token(TokenType.VALUE, part));
            }
        }
        return tokens;
    }
}
