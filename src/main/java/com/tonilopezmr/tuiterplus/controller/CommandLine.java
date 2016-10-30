package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.view.View;

public class CommandLine {

  private View view;
  private boolean exit;

  public CommandLine(View view) {
    this.view = view;
    this.exit = false;
  }

  public void resume() {
    view.showPrompt();
    String commandLine = view.readCommandLine();
    process(commandLine);
  }

  private void process(String cmd) {
    if (cmd.equals("exit")) {
      view.show("exit");
      exit = true;
      return;
    }

    view.show("Hello Codurance! (2 minutes ago)\n");
  }

  public boolean isExit() {
    return exit;
  }
}
