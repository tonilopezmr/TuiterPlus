package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.Post;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.List;

public class CommandLine {

  private View view;
  private boolean exit;
  private Processor processor;

  public CommandLine(View view, Processor processor) {
    this.view = view;
    this.exit = false;
    this.processor = processor;
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

    List<Post> timeline = processor.process(cmd);
    view.show(timeline);
  }

  public boolean isExit() {
    return exit;
  }
}
