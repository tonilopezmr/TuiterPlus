package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;

import java.util.Scanner;

public class TuiterPlus {

  private CommandLine commandLine;

  public TuiterPlus(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  public void run() {

    while (!commandLine.isExit()) {
      commandLine.resume();
    }

  }

  public static void main(String... args) {
    ConsoleCLI view = new ConsoleCLI(new Scanner(System.in), System.out);
    CommandLine commandLine = new CommandLine(view);
    new TuiterPlus(commandLine).run();
  }

}