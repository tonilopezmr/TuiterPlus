package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.Processor;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;

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
    ConsoleCLI view = new ConsoleCLI(new Scanner(System.in), System.out, new DateFormatter());
    Processor processor = new Processor();
    CommandLine commandLine = new CommandLine(view, processor);
    new TuiterPlus(commandLine).run();
  }

}
