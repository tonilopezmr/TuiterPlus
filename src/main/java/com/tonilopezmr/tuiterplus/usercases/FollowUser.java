package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.Optional;

public class FollowUser {

  private UserRepository userRepository;

  public FollowUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void doIt(String followerName, String followedName) {
    Optional<User> oFollower = userRepository.get(followerName);
    Optional<User> oFollowed = userRepository.get(followedName);

    if (oFollower.isPresent() && oFollowed.isPresent()) {
      User follower = oFollower.get();
      follower.addFollow(oFollowed.get());
    }

  }

}
