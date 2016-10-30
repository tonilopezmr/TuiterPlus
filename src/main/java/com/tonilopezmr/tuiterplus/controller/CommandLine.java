package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.Post;
import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.view.View;

import java.time.LocalDateTime;
import java.util.Arrays;

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

    view.show(Arrays.asList(new Post(new User("Toni"), "Hello Codurance!", LocalDateTime.now().minusMinutes(2))));
//    view.show("Hello Codurance! (2 minutes ago)\n");
  }

  public boolean isExit() {
    return exit;
  }
}
