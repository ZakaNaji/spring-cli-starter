package com.znaji.clistarter.cli;

public interface TypedCommand<T> {
    void execute(T args);
}
