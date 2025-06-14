package com.znaji.clistarter.cli.io;

import java.util.Scanner;

public class DefaultInputSource implements InputSource{
    private final Scanner scanner;

    public DefaultInputSource() {

        this.scanner = new Scanner(System.in);
    }
    @Override
    public String nextInput() {
        return scanner.nextLine();
    }
}
