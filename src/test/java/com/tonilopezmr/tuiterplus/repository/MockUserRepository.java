package com.tonilopezmr.tuiterplus.repository;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.List;
import java.util.Optional;

public class MockUserRepository implements UserRepository {

  private List<User> userList;
  private User user;

  public MockUserRepository(User user) {
    this.user = user;
  }

  public MockUserRepository(List<User> users) {
    this.userList = users;
  }

  @Override
  public Optional<User> get(String username) {
    return Optional.of(user);
  }

  @Override
  public void create(User user) {
    this.userList.add(user);
  }

  @Override
  public void follow(User follower, User followed) {
  }
}
