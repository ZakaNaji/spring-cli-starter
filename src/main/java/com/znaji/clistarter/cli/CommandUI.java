package com.znaji.clistarter.cli;

public class CommandUI {
    private final InputSource inputSource;

    public CommandUI(InputSource inputSource) {
        this.inputSource = inputSource;
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
}
