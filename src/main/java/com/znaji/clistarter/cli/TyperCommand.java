package com.znaji.clistarter.cli;

public interface TyperCommand<T> {
    void execute(T args);
}
