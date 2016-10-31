package com.tonilopezmr.tuiterplus.model.user;

import com.tonilopezmr.tuiterplus.model.post.Post;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  Optional<User> get(String username);
  void add(User user);
  void follow(User follower, User followed);
  List<Post> getPostsBy(User user);
  void add(Post post);

}
