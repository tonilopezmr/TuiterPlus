package com.tonilopezmr.tuiterplus.acceptanceTest;

import com.tonilopezmr.tuiterplus.MockServiceLocatorBuilder;
import com.tonilopezmr.tuiterplus.ServiceLocator;
import com.tonilopezmr.tuiterplus.TuiterPlus;
import com.tonilopezmr.tuiterplus.controller.CommandProcessor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusAcceptanceTest {

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
    String output = out.toString();
    output = output.substring(output.indexOf("exit"));
    Scanner scanner = new Scanner(output.replace("> ", "\n"));

    String result = null;
    for (int i = 0; i <= index+3 && scanner.hasNextLine(); i++) {
      result = scanner.nextLine();
    }

    return result;
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
    assertThat(output, is(""));
  }

  @Test
  public void
  show_user_posts_after_create_his_posts() {
    String commands = "Toni -> Hello Codurance!\n";
    commands += "Toni -> Cat Cat\n";
    commands += "Toni\n";
    commands += "exit";

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine(commands))
        .printStream(recordOutPut())
        .creationTimeBeetweenPosts(100)    //see method doc
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput(3);
    assertThat(output, is("Cat Cat (a moment ago)"));
    output = getOutput(4);
    assertThat(output, is("Hello Codurance! (a moment ago)"));
  }

  @Test
  public void
  show_wall_after_user_follows_another_user_when_have_created_their_posts() {
    String commands = "Toni -> Hello Codurance!\n";
    commands += "Alvaro -> Hello Toni\n";
    commands += "Toni follows Alvaro\n";
    commands += "Toni wall\n";
    commands += "exit\n";

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine(commands))
        .printStream(recordOutPut())
        .creationTimeBeetweenPosts(100)
        .build();
    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();

    tuiterPlus.run();

    String output = getOutput(4);
    assertThat(output, is("Alvaro - Hello Toni (a moment ago)"));
    output = getOutput(5);
    assertThat(output, is("Toni - Hello Codurance! (a moment ago)"));
  }

}