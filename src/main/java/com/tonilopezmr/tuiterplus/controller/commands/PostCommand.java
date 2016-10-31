package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.AddPost;
import com.tonilopezmr.tuiterplus.controller.printer.Printer;

import java.util.regex.Matcher;

public class PostCommand extends Command {

  private Printer<Post> printer;
  private AddPost addPost;

  public PostCommand(String pattern, Printer<Post> printer, AddPost addPost) {
    super(pattern);
    this.printer = printer;
    this.addPost = addPost;
  }

  public Printer process() {
    Matcher matcher = getMatcher();
    addPost.doIt(matcher.group(1), matcher.group(2));
    return printer;
  }

}
