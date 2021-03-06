package com.tonilopezmr.tuiterplus.cmdcontroller;

import com.tonilopezmr.tuiterplus.view.View;

/**
 * This is a CommandLine cmdcontroller, It is responsible to manage the flow of commands to the view.
 */
public class CommandLine {

  private View view;
  private boolean exit;
  private CommandProcessor processor;

  public CommandLine(View view, CommandProcessor processor) {
    this.view = view;
    this.exit = false;
    this.processor = processor;
  }

  public void start() {
    view.showWelcome();
  }

  public void resume() {
    view.showPrompt();
    String commandLine = view.readCommandLine();
    process(commandLine);
  }

  private void process(String cmd) {
    if (processor.exit(cmd)) {
      view.showFareWell();
      exit = true;
      return;
    }

    processor.process(cmd);
  }

  public boolean isExit() {
    return exit;
  }
}
