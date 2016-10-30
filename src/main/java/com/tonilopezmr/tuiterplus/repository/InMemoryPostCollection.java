package com.tonilopezmr.tuiterplus.repository;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPostCollection implements PostRepository {

  private List<Post> posts = new ArrayList<>();

  @Override
  public List<Post> getPostsBy(User user) {
    return posts.stream()
        .filter(it -> it.getUser().equals(user))
        .collect(Collectors.toList());
  }

  @Override
  public void create(Post post) {
    posts.add(post);
  }

}
