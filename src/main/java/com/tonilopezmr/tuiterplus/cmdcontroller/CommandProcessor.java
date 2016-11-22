package com.tonilopezmr.tuiterplus.cmdcontroller;

import com.tonilopezmr.tuiterplus.cmdcontroller.commands.Command;

import java.util.List;
import java.util.Optional;

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

  private List<Command> commands;

  public CommandProcessor(List<Command> commands) {
    this.commands = commands;
  }

  public void process(String cmd) {
    Optional<Command> cmdOpt = commands.stream()
        .filter(it -> it.matches(cmd))
        .findFirst();

    if (cmdOpt.isPresent()) {
      Command command = cmdOpt.get();
      command.process();
    }
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
