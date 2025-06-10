package com.znaji.clistarter.cli;

public interface OutputTarget {
    void print(String out);
    void error(String error);
}
