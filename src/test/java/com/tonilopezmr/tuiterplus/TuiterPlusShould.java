package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandProcessor;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  private ByteArrayOutputStream out = new ByteArrayOutputStream();

  private Scanner willTypeLine(String line) {
    return new Scanner(line);
  }

  /**
   * Return a output of the "turn" in console
   * <p>
   * for example console:
   * > Charlie             index 0
   * Hey! (1 minute ago)   index 1
   * > exit                index 3
   * exit                  index 4
   *
   * @param index When the expected output index should be
   * @return the expected output
   */
  private String getOutput(int index) {
    Scanner scanner = new Scanner(out.toString().replace("> ", "\n"));
    String result = null;
    for (int i = 0; i <= index && scanner.hasNextLine(); i++) {
      result = scanner.nextLine();
    }
    return result;
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
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine(CommandProcessor.EXIT_COMMAND))              //simulate input actions
        .printStream(recordOutPut())
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput(1);
    assertThat(output, is("exit"));
  }

  @Test
  public void
  show_user_posts_after_create_his_posts() {
    String commands = "Toni -> Hello Codurance!\n";
    commands += "SomeBody\n";
    commands += "Toni -> Cat Cat\n";
    commands += "Toni\n";
    commands += "exit";

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine(commands))
        .printStream(recordOutPut())
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput(4);
    assertThat(output, is("Cat Cat (a moment ago)"));
    output = getOutput(5);
    assertThat(output, is("Hello Codurance! (a moment ago)"));
  }

  @Test
  public void
  user_follows_another_user_after_create_their_posts() {
    String commands = "Toni -> Hello Codurance!\n";
    commands += "Alvaro -> Hello Toni\n";
    commands += "Toni follows Alvaro\n";
    commands += "Toni wall\n";
    commands += "exit\n";

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine(commands))
        .printStream(recordOutPut())
        .build();
    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();

    tuiterPlus.run();

    String output = getOutput(4);
    assertThat(output, is("Hello Toni (a moment ago)"));
    output = getOutput(5);
    assertThat(output, is("Hello Codurance! (a moment ago)"));
  }

}