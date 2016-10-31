package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.view.Printer;

import java.util.regex.Matcher;

public class PostCommand extends Command {

  private Printer<Post> printer;
  private CreatePost createPost;

  public PostCommand(String pattern, Printer<Post> printer, CreatePost createPost) {
    super(pattern);
    this.printer = printer;
    this.createPost = createPost;
  }

  public Printer process() {
    Matcher matcher = getMatcher();
    createPost.doIt(matcher.group(1), matcher.group(2));
    return printer;
  }

}
