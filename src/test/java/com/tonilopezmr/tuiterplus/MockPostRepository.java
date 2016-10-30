package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;

import java.util.List;

public class MockPostRepository implements PostRepository {

  private List<Post> posts;

  public MockPostRepository(List<Post> posts) {
    this.posts = posts;
  }

  @Override
  public List<Post> getPostsBy(User user) {
    return posts;
  }

  @Override
  public void create(Post post) {
    posts.add(post);
  }
}
