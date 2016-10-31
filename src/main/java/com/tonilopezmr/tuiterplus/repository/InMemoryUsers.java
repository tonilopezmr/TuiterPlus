package com.tonilopezmr.tuiterplus.repository;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

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
