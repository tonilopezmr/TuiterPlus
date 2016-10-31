package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.view.Printer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {

  private String pattern;
  private Matcher matcher;

  public Command(String pattern) {
    this.pattern = pattern;
  }

  Matcher getMatcher() {
    return this.matcher;
  }

  public boolean matches(String cmd) {
    this.matcher = Pattern.compile(pattern).matcher(cmd);
    return matcher.matches();
  }

  public abstract Printer process();
}
