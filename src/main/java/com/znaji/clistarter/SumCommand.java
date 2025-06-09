package com.znaji.clistarter;

import com.znaji.clistarter.cli.CLICommand;
import com.znaji.clistarter.cli.TyperCommand;

@CLICommand(
        name = "sum",
        description = "sum a + b"
)
public class SumCommand implements TyperCommand<NumToSum> {
    @Override
    public void execute(NumToSum args) {
        System.out.println(args.a +" + "+args.b + " = " + args.getSum());
    }

}
