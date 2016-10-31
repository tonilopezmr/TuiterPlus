package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.controller.commands.Command;
import com.tonilopezmr.tuiterplus.controller.printer.Printer;

import java.util.List;

/**
 * Process the commands and return a printable output.
 */
public class CommandProcessor {

  public static final String POST = "->";
  public static final String FOLLOWS = "follows";
  public static final String WALL = "wall";

  public static final String POST_COMMAND = String.format("(.*) %s (.*)", POST);
  public static final String FOLLOW_COMMAND = String.format("(.*) %s (.*)", FOLLOWS);
  public static final String WALL_COMMAND = String.format("(.*) %s", WALL);
  public static final String EXIT_COMMAND = "exit";
  public static final String READ_COMMAND = "(.*)";
  public static final String UNFOLLOW_COMMAND = String.format("(.*) %s (.*)", "unfollows");

  private List<Command> commands;

  public CommandProcessor(List<Command> commands) {
    this.commands = commands;
  }

  public Printer process(String cmd) {

    return commands.stream()
        .filter(it -> it.matches(cmd))
        .map(Command::process)
        .findFirst().get();
  }

  /**
   * Because always needs a command to exit.
   *
   * @param cmd command line
   * @return true if the command is to exit
   */
  public boolean exit(String cmd) {
    return cmd.equals(EXIT_COMMAND);
  }
}
