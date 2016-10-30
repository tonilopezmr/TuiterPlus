package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.Post;
import com.tonilopezmr.tuiterplus.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GetPosts {

  public List<Post> getPosts(User user) {
    User toni = new User(user.getName());
    Post post = new Post(toni, "Hello Codurance!", LocalDateTime.now().minusMinutes(2));
    return Arrays.asList(post);
  }

}
