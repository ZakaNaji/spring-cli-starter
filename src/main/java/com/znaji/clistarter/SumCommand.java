package com.znaji.clistarter;

import com.znaji.clistarter.cli.annotation.CLICommand;
import com.znaji.clistarter.cli.TypedCommand;

@CLICommand(
        name = "sum",
        description = "sum a + b"
)
public class SumCommand implements TypedCommand<NumToSum> {
    @Override
    public void execute(NumToSum args) {
        System.out.println(args.a +" + "+args.b + " = " + args.getSum());
    }

}
