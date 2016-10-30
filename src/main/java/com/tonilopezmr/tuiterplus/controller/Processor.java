package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.GetPosts;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Process the commands and return a result.
 */
public class Processor {

  public static final String POST = "(.*) -> (.*)";
  public static final String FOLLOW = "(.*) follow (.*)";
  public static final String WALL = "(.*) wall";

  private GetPosts getPosts;

  public Processor(GetPosts getPosts) {
    this.getPosts = getPosts;
  }

  public List<Post> process(String cmd) {
    List<Post> posts = new ArrayList<>();

    Matcher matcher;

    if ((matcher = getMatcher(POST, cmd)).matches()) {

    }else if ((matcher = getMatcher(FOLLOW, cmd)).matches()){

    }else if ((matcher = getMatcher(WALL, cmd)).matches()) {

    }else {
      posts = getPosts.getPosts(cmd);
    }

    return posts;
  }

  private Matcher getMatcher(String pattern, String cmd) {
    return Pattern.compile(pattern).matcher(cmd);
  }

}
