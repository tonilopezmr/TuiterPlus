package com.tonilopezmr.tuiterplus.timeline.model;

import com.tonilopezmr.tuiterplus.user.model.User;

import java.time.LocalDateTime;

public class Post implements Comparable<Post> {

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

  @Override
  public int compareTo(Post o) {
    return o.dateTime.compareTo(dateTime);
  }

}
