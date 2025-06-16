package com.znaji.clistarter.cli.core;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class CommandRegistry {
    private Map<String, CommandDescriptor> commands = new HashMap<>();

    public void register(CommandDescriptor descriptor) {
        commands.put(descriptor.name(), descriptor);
    }

    public Collection<CommandDescriptor> getAll() {
        return commands.values();
    }

    public Optional<CommandDescriptor> get(String name) {
        return Optional.ofNullable(commands.get(name));
    }

    public boolean has(String name) {
        return commands.containsKey(name);
    }
}
