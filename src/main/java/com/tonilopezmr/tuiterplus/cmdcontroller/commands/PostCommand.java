package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;

import java.util.regex.Matcher;

public class PostCommand extends Command {

  private AddPost addPost;

  public PostCommand(String pattern, AddPost addPost) {
    super(pattern);
    this.addPost = addPost;
  }

  public void process() {
    Matcher matcher = getMatcher();
    addPost.doIt(matcher.group(1), matcher.group(2));
  }

}
