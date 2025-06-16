package com.znaji.clistarter.cli.core;

public record ArgumentDescriptor(
        String name,
        String description,
        boolean required,
        boolean flag
) {
}
