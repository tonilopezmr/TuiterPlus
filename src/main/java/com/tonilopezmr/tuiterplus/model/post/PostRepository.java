package com.tonilopezmr.tuiterplus.model.post;

import com.tonilopezmr.tuiterplus.model.User;

import java.util.List;

public interface PostRepository {
  List<Post> getPostsBy(User user);
  void create(Post post);
}
