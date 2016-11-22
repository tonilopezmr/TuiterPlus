package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;

import java.util.regex.Matcher;

public class FollowCommand extends Command {

  private FollowUser followUser;

  public FollowCommand(String pattern, FollowUser followUser) {
    super(pattern);
    this.followUser = followUser;
  }

  @Override
  public void process() {
    Matcher matcher = getMatcher();
    followUser.doIt(matcher.group(1), matcher.group(2));
  }
}
