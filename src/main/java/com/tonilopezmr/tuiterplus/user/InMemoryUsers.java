package com.tonilopezmr.tuiterplus.user;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUsers implements UserRepository {

  private List<User> userList;
  private List<Post> postList;

  public InMemoryUsers() {
    this.userList = new ArrayList<>();
    this.postList = new ArrayList<>();
  }

  @Override
  public Optional<User> get(String username) {
    return userList.stream()
        .filter(it -> it.getName().equals(username))
        .findFirst();
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

  @Override
  public List<Post> getPostsBy(User user) {
    return postList.stream()
        .filter(it -> it.getUser().equals(user))
        .collect(Collectors.toList());
  }

  @Override
  public void add(Post post) {
    postList.add(post);
  }

}
