package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.base.TimeProvider;
import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;

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
      user = createUser(userName);
    } else {
      user = oUser.get();
    }

    return user;
  }

  private User createUser(String userName) {
    User user = new User(userName);
    userRepository.create(user);
    return user;
  }
}
