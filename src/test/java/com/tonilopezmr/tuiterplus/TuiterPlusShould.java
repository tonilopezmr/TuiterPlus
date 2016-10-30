package com.tonilopezmr.tuiterplus;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  @Test
  public void
  show_greeting_example() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));

    TuiterPlus.main();

    assertThat(output.toString(), is("Hello Codurance!"));
  }


}