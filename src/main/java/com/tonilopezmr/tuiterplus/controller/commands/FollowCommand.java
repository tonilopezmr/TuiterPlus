package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.view.Printer;

import java.util.regex.Matcher;

public class FollowCommand extends Command {

  private Printer<User> printer;
  private FollowUser followUser;

  public FollowCommand(String pattern, Printer<User> printer, FollowUser followUser) {
    super(pattern);
    this.printer = printer;
    this.followUser = followUser;
  }

  @Override
  public Printer process() {
    Matcher matcher = getMatcher();
    followUser.doIt(matcher.group(1), matcher.group(2));
    return printer;
  }
}
