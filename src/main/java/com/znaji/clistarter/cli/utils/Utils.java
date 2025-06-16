package com.znaji.clistarter.cli.utils;

import com.znaji.clistarter.cli.core.TypedCommand;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Utils {

    public static Class<?> getCommandArgsType(Object bean) {
        for (Type type : bean.getClass().getGenericInterfaces()) {
            if (type instanceof ParameterizedType pt && pt.getRawType() == TypedCommand.class) {
                return (Class<?>) pt.getActualTypeArguments()[0];
            }
        }
        throw new IllegalStateException("Cannot determine generic type of TypedCommand");
    }
}
