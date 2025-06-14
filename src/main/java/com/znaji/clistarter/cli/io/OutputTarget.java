package com.znaji.clistarter.cli.io;

public interface OutputTarget {
    void print(String out);
    void error(String error);
}
