package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.model.post.Post;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  private ByteArrayOutputStream out = new ByteArrayOutputStream();

  private Scanner willTypeLine(String line) {
    return new Scanner(line);
  }

  private String getOutput() {
    Scanner scanner = new Scanner(out.toString());
    scanner.skip("> ");
    scanner.useDelimiter("> ");
    return scanner.nextLine();
  }

  private Post getToniPostTwoMinutesAgo() {
    User toni = new User("Toni");
    return new Post(toni, "Hello Codurance!", LocalDateTime.now().minusMinutes(2));
  }

  private PrintStream recordOutPut() {
    return new PrintStream(out);
  }

  @Test
  public void
  exit_when_command_is_exit() {
    ServiceLocator serviceLocator = new ServiceLocatorMockBuilder()
        .scanner(willTypeLine("exit"))              //simulate input actions
        .printStream(recordOutPut())
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput();
    assertThat(output, is("exit"));
  }

  @Test
  public void
  show_posts_when_read_user_timeline() {
    Post post = getToniPostTwoMinutesAgo();

    ServiceLocator serviceLocator = new ServiceLocatorMockBuilder()
          .scanner(willTypeLine("Toni\nexit"))              //simulate input actions
          .printStream(recordOutPut())
          .postRepository(new MockPostRepository(Arrays.asList(post)))     //mock output
          .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput();
    assertThat(output, is("Hello Codurance! (2 minutes ago)"));
  }

}