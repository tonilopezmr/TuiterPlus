package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;

import java.util.List;
import java.util.Optional;

public class ReadWallTimeline {

  private UserRepository userRepository;

  public ReadWallTimeline(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Timeline getIt(String userName) {
    Optional<User> oUser = userRepository.get(userName);

    if (!oUser.isPresent()) {
      return new Timeline();
    }

    return getTimeline(oUser.get());
  }

  private Timeline getTimeline(User user) {
    Timeline timeline = new Timeline();
    timeline.addAll(userRepository.getPostsBy(user));

    for (User friend : user.getFollowing()) {
      List<Post> friendPosts = userRepository.getPostsBy(friend);
      timeline.addAll(friendPosts);
    }
    return timeline;
  }

}
