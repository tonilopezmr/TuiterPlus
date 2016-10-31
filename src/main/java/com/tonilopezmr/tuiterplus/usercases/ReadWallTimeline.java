package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class ReadWallTimeline {

  private UserRepository userRepository;

  public ReadWallTimeline(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Timeline getIt(String userName) {
    Timeline timeline = new Timeline();
    Optional<User> oUser = userRepository.get(userName);

    if (oUser.isPresent()) {
      User user = oUser.get();
      timeline.addAll(userRepository.getPostsBy(user));
      for (User friend : user.getFollowing()) {
        List<Post> friendPosts = userRepository.getPostsBy(friend);
        timeline.addAll(friendPosts);
      }
    }

    return timeline;
  }

}
