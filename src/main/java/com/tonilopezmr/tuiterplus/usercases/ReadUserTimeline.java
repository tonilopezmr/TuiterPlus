package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.List;

public class ReadUserTimeline {

  private UserRepository userRepository;

  public ReadUserTimeline(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Timeline getIt(String userName) {
    Timeline timeline = new Timeline();
    List<Post> postsByUser = userRepository.getPostsBy(new User(userName));
    timeline.addAll(postsByUser);
    return timeline;
  }

}
