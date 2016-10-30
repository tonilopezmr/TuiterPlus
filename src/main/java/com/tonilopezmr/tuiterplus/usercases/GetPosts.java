package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class GetPosts {

  private PostRepository postRepository;

  public GetPosts(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getIt(String userName) {
    return postRepository.getPostsBy(new User(userName))
        .stream().sorted(Post::compareTo)
        .collect(Collectors.toList());
  }

}
