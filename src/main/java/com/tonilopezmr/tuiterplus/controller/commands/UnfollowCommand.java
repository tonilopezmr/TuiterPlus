package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.controller.printer.Printer;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.usercases.UnfollowUser;

import java.util.regex.Matcher;

public class UnfollowCommand extends Command {

  private UnfollowUser unfollow;
  private Printer<User> printer;

  public UnfollowCommand(String pattern, Printer<User> printer, UnfollowUser unfollow) {
    super(pattern);
    this.unfollow = unfollow;
    this.printer = printer;
  }

  @Override
  public Printer process() {
    Matcher matcher = getMatcher();
    unfollow.doIt(matcher.group(1), matcher.group(2));
    printer.load(new User(matcher.group(2)));
    return printer;
  }
}
