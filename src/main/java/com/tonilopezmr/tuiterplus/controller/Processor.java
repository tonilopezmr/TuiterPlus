package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
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
  private CreatePost createPost;

  public Processor(GetPosts getPosts, CreatePost createPost) {
    this.getPosts = getPosts;
    this.createPost = createPost;
  }

  public List<Post> process(String cmd) {
    List<Post> posts = new ArrayList<>();

    Matcher matcher;

    if ((matcher = getMatcher(POST, cmd)).matches()) {
      createPost.doIt(matcher.group(1), matcher.group(2));
    }else if ((matcher = getMatcher(FOLLOW, cmd)).matches()){

    }else if ((matcher = getMatcher(WALL, cmd)).matches()) {

    }else {
      posts = getPosts.getIt(cmd);
    }

    return posts;
  }

  private Matcher getMatcher(String pattern, String cmd) {
    return Pattern.compile(pattern).matcher(cmd);
  }

}
