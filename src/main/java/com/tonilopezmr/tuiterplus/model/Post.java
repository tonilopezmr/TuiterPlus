package com.tonilopezmr.tuiterplus.model;

import java.time.LocalDateTime;

public class Post {

  private User user;
  private String post;
  private LocalDateTime dateTime;

  public Post(User user, String post, LocalDateTime dateTime) {
    this.user = user;
    this.post = post;
    this.dateTime = dateTime;
  }

  public User getUser() {
    return user;
  }

  public String getPost() {
    return post;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

}
