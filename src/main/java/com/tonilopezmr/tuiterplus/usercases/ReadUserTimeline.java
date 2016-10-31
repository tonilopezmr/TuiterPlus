package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;

import java.util.List;

public class ReadUserTimeline {

  private PostRepository postRepository;

  public ReadUserTimeline(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Timeline getIt(String userName) {
    Timeline timeline = new Timeline();
    List<Post> postsByUser = postRepository.getPostsBy(new User(userName));
    timeline.addAll(postsByUser);
    return timeline;
  }

}
