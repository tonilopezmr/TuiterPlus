package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.Optional;

public class UnfollowUser {

  private UserRepository userRepository;

  public UnfollowUser(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void doIt(String follower, String following){
    Optional<User> oFollower = userRepository.get(follower);
    Optional<User> oFollowing = userRepository.get(following);

    if (oFollower.isPresent() && oFollowing.isPresent()) {
      User user = oFollower.get();
      user.unFollow(oFollowing.get());
    }

  }

}
