package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.Processor;
import com.tonilopezmr.tuiterplus.model.Post;
import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.View;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;
import com.tonilopezmr.tuiterplus.view.dateformatter.MinutesFormat;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  private ByteArrayOutputStream out = new ByteArrayOutputStream();

  private DateFormatter getDateFormatter() {
    DateFormatter dateFormatter = new DateFormatter();
    dateFormatter.addDateFormat(new MinutesFormat("minute", "minutes"));
    return dateFormatter;
  }

  private View willTypeLine(String line) {
    return new ConsoleCLI(new Scanner(line), new PrintStream(out), getDateFormatter());
  }

  private TuiterPlus giveTuiterPlus(View view) {
    Processor processor = new Processor();
    CommandLine commandLine = new CommandLine(view, processor);
    return new TuiterPlus(commandLine);
  }

  private Processor giveProcessor(List<Post> timeline) {
    return new Processor() {
      @Override
      public List<Post> process(String cmd) {
        return timeline;
      }
    };
  }

  private TuiterPlus getTuiterPlus(View view, Processor processor) {
    CommandLine commandLine = new CommandLine(view, processor);

    return new TuiterPlus(commandLine);
  }

  private String getOutput() {
    Scanner scanner = new Scanner(out.toString());
    scanner.skip("> ");
    scanner.useDelimiter("> ");
    return scanner.nextLine();
  }

  @Test
  public void
  exit_when_command_is_exit() {
    View view = willTypeLine("exit");
    TuiterPlus tuiterPlus = giveTuiterPlus(view);

    tuiterPlus.run();

    String output = getOutput();
    assertThat(output, is("exit"));
  }

  @Test public void
  show_posts_when_read_user_timeline(){
    User toni = new User("Toni");
    Post post = new Post(toni, "Hello Codurance!", LocalDateTime.now().minusMinutes(2));
    Processor processor = giveProcessor(Arrays.asList(post));
    View view = willTypeLine("Toni\nexit");

    TuiterPlus tuiterPlus = getTuiterPlus(view, processor);
    tuiterPlus.run();

    String output = getOutput();
    assertThat(output, is("Hello Codurance! (2 minutes ago)"));
  }

}