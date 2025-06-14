package com.znaji.clistarter.cli;

import com.znaji.clistarter.cli.core.CommandDispatcher;
import com.znaji.clistarter.cli.core.ResolvedCommand;

public class CommandUI {
    private final InputSource inputSource;
    private final OutputTarget outputTarget;

    public CommandUI(InputSource inputSource, OutputTarget outputTarget) {
        this.inputSource = inputSource;
        this.outputTarget = outputTarget;
    }

    public void welcome(CommandDispatcher dispatcher) {
        for (int i = 0; i < dispatcher.getCommands().size(); i++) {
            ResolvedCommand cmd = dispatcher.getCommands().get(i);
            System.out.printf("[%d] %s — %s%n", i + 1, cmd.name(), cmd.description());
        }

    }

    public String readInput() {
        return inputSource.nextInput();
    }

    public void printToOutput(String out) {
        outputTarget.print(out);
    }
}
