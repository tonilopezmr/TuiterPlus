package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;

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
