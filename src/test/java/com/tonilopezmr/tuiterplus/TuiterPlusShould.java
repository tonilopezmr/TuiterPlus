package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  @Test
  public void
  exit_when_command_is_exit() {
    String command = "exit";
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    ConsoleCLI view = new ConsoleCLI(new Scanner(command), new PrintStream(out));
    CommandLine commandLine = new CommandLine(view);

    TuiterPlus tuiterPlus = new TuiterPlus(commandLine);
    tuiterPlus.run();

    Scanner scanner = new Scanner(out.toString());
    scanner.skip("> ");
    scanner.useDelimiter("> ");

    assertThat(scanner.nextLine(), is("exit"));
  }

  @Test public void
  show_posts_when_read_user_timeline(){
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ConsoleCLI view = new ConsoleCLI(new Scanner("Toni\nexit"), new PrintStream(out));
    CommandLine commandLine = new CommandLine(view);

    TuiterPlus tuiterPlus = new TuiterPlus(commandLine);
    tuiterPlus.run();

    Scanner scanner = new Scanner(out.toString());
    scanner.skip("> ");
    scanner.useDelimiter("> ");

    assertThat(scanner.nextLine(), is("Hello Codurance! (2 minutes ago)"));
  }

}