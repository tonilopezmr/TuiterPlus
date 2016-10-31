package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
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

  @Override
  public void showFareWell() {
    show("exit");
  }

  public void show(String message) {
    output.print(message);
  }

  public void show(Timeline posts) {
    for (Post post : posts) {

      LocalDateTime now = LocalDateTime.now();
      LocalDateTime postDate = post.getDateTime();
      Duration duration = Duration.between(postDate, now);

      String result = dateFormatter.format(duration);

      output.printf("%s (%s ago)\n", post.getPost(), result);
    }
  }

  @Override
  public void showWallTimeline(Timeline posts) {
    for (Post post : posts) {

      LocalDateTime now = LocalDateTime.now();
      LocalDateTime postDate = post.getDateTime();
      Duration duration = Duration.between(postDate, now);

      String result = dateFormatter.format(duration);

      User user = post.getUser();
      output.printf("%s - %s (%s ago)\n", user.getName(), post.getPost(), result);
    }
  }

}
