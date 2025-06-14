package com.znaji.clistarter.cli.core;

public interface TypedCommand<T> {
    void execute(T args);
}
