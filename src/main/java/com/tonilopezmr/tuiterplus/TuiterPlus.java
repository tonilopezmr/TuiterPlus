package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;

/**
 * Application Class, start the CommandLine application.
 */
public class TuiterPlus {

  private CommandLine commandLine;

  public TuiterPlus(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  public void run() {
    commandLine.start();

    while (!commandLine.isExit()) {
      commandLine.resume();
    }

  }

  public static void main(String... args) {
    ServiceLocator.load(new ServiceLocator());
    CommandLine commandLine = ServiceLocator.get().getCommandLine();
    new TuiterPlus(commandLine).run();
  }

}
