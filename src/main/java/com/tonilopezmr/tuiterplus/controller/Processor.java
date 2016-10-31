package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.controller.commands.Command;
import com.tonilopezmr.tuiterplus.view.Printer;

import java.util.List;

/**
 * Process the commands and return a result.
 */
public class Processor {

  public static final String POST = "->";
  public static final String FOLLOWS = "follows";
  public static final String WALL = "wall";

  public static final String POST_COMMAND = String.format("(.*) %s (.*)", POST);
  public static final String FOLLOW_COMMAND = String.format("(.*) %s (.*)", FOLLOWS);
  public static final String WALL_COMMAND = String.format("(.*) %s", WALL);
  public static final String EXIT_COMMAND = "exit";
  public static final String READ_COMMAND = "(.*)";

  private List<Command> commands;

  public Processor(List<Command> commands) {
    this.commands = commands;
  }

  public Printer process(String cmd) {
    Printer printer = null;

    for (Command command : commands) {
      if (command.matches(cmd)) {
        printer = command.process();
      }
    }

    return printer;
  }

  public boolean exit(String cmd) {
    return cmd.equals(EXIT_COMMAND);
  }
}
