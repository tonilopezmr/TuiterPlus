package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;

import java.io.PrintStream;
import java.time.Duration;
import java.util.Scanner;

/**
 * Console View, show with console format and collect user commands.
 */
public class ConsoleCLI implements View {

  private Scanner scanner;
  private PrintStream output;
  private DateFormatter dateFormatter;

  public ConsoleCLI(Scanner scanner, PrintStream output, DateFormatter dateFormatter) {
    this.scanner = scanner;
    this.output = output;
    this.dateFormatter = dateFormatter;
  }

  public void showPrompt() {
    output.print("> ");
  }

  public String readCommandLine() {
    return scanner.nextLine();
  }

  public void showWelcome() {
    show("*************************************\n");
    show("************  Welcome  **************\n");
    show("******** Tuiter+ tonilopezmr ********\n");
    show("*************************************\n\n");

    show("IMPORTANT: Users are created as they timeline their first timeline\n\n");
    show("How to use (before ':' are not part of the commands)\n\n");
    show("\tto posting: <user name> -> <message>\n");
    show("\tto reading: <user name>\n");
    show("\tto following: <user name> follows <another user>\n");
    show("\tto wall: <user name> wall\n");
    show("\tto exit: exit \n\n\n");
  }

  @Override
  public void showFareWell() {
    show("\n\n*************************************\n");
    show("************** GoodBye **************\n");
    show("******** Tuiter+ tonilopezmr ********\n");
    show("*************************************\n\n");
  }

  public void show(String message) {
    output.print(message);
  }

  public void show(Timeline posts) {
    for (Post post : posts) {

      Duration diff = dateFormatter.diff(post.getDateTime());
      String result = dateFormatter.format(diff);

      output.printf("%s (%s ago)\n", post.getPost(), result);
    }
  }

  @Override
  public void showWallTimeline(Timeline posts) {
    for (Post post : posts) {

      Duration diff = dateFormatter.diff(post.getDateTime());
      String result = dateFormatter.format(diff);

      User user = post.getUser();
      output.printf("%s - %s (%s ago)\n", user.getName(), post.getPost(), result);
    }
  }

}
