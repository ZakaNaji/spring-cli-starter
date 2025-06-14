package com.znaji.clistarter.cli.io;

public class DefaultOutputTarget implements OutputTarget{
    @Override
    public void print(String out) {
        System.out.println(out);
    }

    @Override
    public void error(String error) {
        System.err.println(error);
    }
}
