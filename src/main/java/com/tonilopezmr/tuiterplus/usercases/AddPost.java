package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.TimeProvider;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.Optional;

public class AddPost {

  private UserRepository userRepository;
  private TimeProvider localDateTime;

  public AddPost(UserRepository userRepository, TimeProvider localDateTime) {
    this.userRepository = userRepository;
    this.localDateTime = localDateTime;
  }

  public void doIt(String userName, String post) {
    User user = getUser(userName);

    userRepository.add(new Post(user, post, localDateTime.timeNow()));
  }

  private User getUser(String userName) {
    Optional<User> oUser = userRepository.get(userName);
    User user;

    if (!oUser.isPresent()) {
      user = new User(userName);
      userRepository.add(user);
    } else {
      user = oUser.get();
    }
    return user;
  }
}
