package com.tonilopezmr.tuiterplus.model.user;

import java.util.ArrayList;
import java.util.List;

public class User {

  private String name;
  private List<User> following;

  public User(String name) {
    this.name = name;
    this.following = new ArrayList<User>();
  }

  public String getName() {
    return name;
  }

  public List<User> getFollowing() {
    return new ArrayList<User>(following);
  }

  public void addFollow(User followed) {
    this.following.add(followed);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return name.equals(user.name);

  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
