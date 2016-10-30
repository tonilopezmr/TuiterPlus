package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetWallTimeline {

  private PostRepository postRepository;
  private UserRepository userRepository;

  public GetWallTimeline(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  public List<Post> getIt(String userName) {
    List<Post> timeline = new ArrayList<>();
    Optional<User> oUser = userRepository.get(userName);

    if (oUser.isPresent()){
      User user = oUser.get();
      timeline = postRepository.getPostsBy(user);
      for (User friend : user.getFollowing()) {
        List<Post> friendPosts = postRepository.getPostsBy(friend);
        timeline.addAll(friendPosts);
      }
    }

    return timeline.stream()
        .sorted(Post::compareTo)
        .collect(Collectors.toList());
  }

}
