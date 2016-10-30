package com.tonilopezmr.tuiterplus.model;

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

}
