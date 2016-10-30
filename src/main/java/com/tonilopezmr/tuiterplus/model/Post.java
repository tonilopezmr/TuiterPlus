package com.tonilopezmr.tuiterplus.model;

public class Post {

  private User user;
  private String post;
  private Long timestamp;

  public Post(User user, String post, Long timestamp) {
    this.user = user;
    this.post = post;
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public String getPost() {
    return post;
  }

  public Long getTimestamp() {
    return timestamp;
  }

}
