package com.znaji.clistarter;

import com.znaji.clistarter.cli.CLIArg;

public class NumToSum {
    @CLIArg(value = "a", description = "A + ?", required = true)
    public int a;
    @CLIArg(value = "b", description = "? + B", required = true)
    public int b;

    public int getSum() {
        return a + b;
    }
}
