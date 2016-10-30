package com.tonilopezmr.tuiterplus.model.user;

import java.util.Optional;

public interface UserRepository {

  Optional<User> get(String username);
  void create(User user);
  void follow(User follower, User followed);

}
