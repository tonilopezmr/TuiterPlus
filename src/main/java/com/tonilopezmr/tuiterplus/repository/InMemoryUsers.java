package com.tonilopezmr.tuiterplus.repository;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUsers implements UserRepository {

  private List<User> userList;

  public InMemoryUsers() {
    this.userList = new ArrayList<>();
  }

  @Override
  public Optional<User> get(String username) {
    return userList.stream().filter(it -> it.getName().equals(username)).findFirst();
  }

  @Override
  public void create(User user) {
    userList.add(user);
  }

  @Override
  public void follow(User follower, User followed) {
    Optional<User> oUser = get(follower.getName());

    if (oUser.isPresent()) {
      User user = oUser.get();
      user.addFollow(followed);
    }
  }
}
