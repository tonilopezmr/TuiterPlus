package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;

import java.util.List;

public class GetPosts {

  private PostRepository postRepository;

  public GetPosts(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getIt(String userName) {
    return postRepository.getPostsBy(new User(userName));
  }

}
