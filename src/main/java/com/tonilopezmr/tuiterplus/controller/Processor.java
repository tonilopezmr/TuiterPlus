package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.GetPosts;
import com.tonilopezmr.tuiterplus.usercases.GetWallTimeline;

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
  private GetWallTimeline wallTimeline;
  private FollowUser follow;

  public Processor(GetPosts getPosts, CreatePost createPost, GetWallTimeline wallTimeline, FollowUser follow) {
    this.getPosts = getPosts;
    this.createPost = createPost;
    this.wallTimeline = wallTimeline;
    this.follow = follow;
  }

  public List<Post> process(String cmd) {
    List<Post> posts = new ArrayList<>();

    Matcher matcher;

    if ((matcher = getMatcher(POST, cmd)).matches()) {
      createPost.doIt(matcher.group(1), matcher.group(2));
    }else if ((matcher = getMatcher(FOLLOW, cmd)).matches()){
      follow.doIt(matcher.group(1), matcher.group(2));
    }else if ((matcher = getMatcher(WALL, cmd)).matches()) {
      posts = wallTimeline.getIt(matcher.group(1));
    }else {
      posts = getPosts.getIt(cmd);
    }

    return posts;
  }

  private Matcher getMatcher(String pattern, String cmd) {
    return Pattern.compile(pattern).matcher(cmd);
  }

}
