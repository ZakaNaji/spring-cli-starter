package com.znaji.clistarter.cli.core;

import java.util.List;

public record CommandDescriptor(
        String name,
        String description,
        Object commandInstance,
        List<ArgumentDescriptor> arguments
) {
}
