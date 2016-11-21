package com.tonilopezmr.tuiterplus.user.model;

import com.tonilopezmr.tuiterplus.timeline.model.Post;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> get(String username);
  void create(User user);
  void follow(User follower, User followed);
  List<Post> getPostsBy(User user);
  void add(Post post);

}
