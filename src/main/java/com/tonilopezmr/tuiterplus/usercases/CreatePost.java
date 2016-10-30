package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class CreatePost {

  private UserRepository userRepository;
  private PostRepository postRepository;

  public CreatePost(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  public void doIt(String userName, String post) {
    Optional<User> oUser = userRepository.get(userName);
    User user;

    if (!oUser.isPresent()) {
      user = new User(userName);
      userRepository.create(user);
    } else {
      user = oUser.get();
    }

    postRepository.create(new Post(user, post, LocalDateTime.now()));
  }
}
