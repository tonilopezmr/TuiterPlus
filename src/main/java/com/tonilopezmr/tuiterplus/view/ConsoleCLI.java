package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.Post;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleCLI implements View {

  private Scanner scanner;
  private PrintStream output;

  public ConsoleCLI(Scanner scanner, PrintStream output) {
    this.scanner = scanner;
    this.output = output;
  }

  public void showPrompt() {
    output.print("> ");
  }

  public String readCommandLine() {
    return scanner.nextLine();
  }

  public void show(String message) {
    output.print(message);
  }

  public void show(List<Post> posts) {
    for (Post post : posts) {

      LocalDateTime now = LocalDateTime.now();
      LocalDateTime postDate = post.getDateTime();
      Duration duration = Duration.between(postDate, now);

      String result = "";
      int time = 0;

      if (duration.toDays() > 0) {
        time = (int) duration.toDays();
        result = "days";
      }else if (duration.toHours() > 0) {
        time = (int) duration.toHours();
        result = "hours";
      }else if (duration.toMinutes() > 0) {
        time = (int) duration.toMinutes();
        result = "minutes";
      }else if (duration.getSeconds() > 0) {
        time = (int) duration.getSeconds();
        result = "seconds";
      }

      output.print(output.printf(post.getPost() + " (%d %s ago)\n", time, result));
    }
  }

}
