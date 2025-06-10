package com.znaji.clistarter.cli;

public class DefaultOutputTarget implements OutputTarget{
    @Override
    public void print(String out) {
        System.out.println(out);
    }
}
