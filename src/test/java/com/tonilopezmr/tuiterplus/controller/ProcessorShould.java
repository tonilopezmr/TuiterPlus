package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.User;
import com.tonilopezmr.tuiterplus.repository.InMemoryPostCollection;
import com.tonilopezmr.tuiterplus.usercases.GetPosts;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  private GetPosts willGetPosts(List<Post> posts) {
    return new GetPosts(new InMemoryPostCollection() {
      @Override
      public List<Post> getPostsBy(User user) {
        return posts;
      }
    });
  }

  @Test
  public void
  return_posts_when_the_command_is_a_user(){
    User toni = new User("Toni");
    Post post = new Post(toni, "Hello Codurance!", LocalDateTime.now().minusMinutes(2));
    GetPosts getPosts = willGetPosts(Arrays.asList(post));
    Processor processor = new Processor(getPosts);

    List<Post> posts = processor.process("Toni");

    assertTrue(posts.size() > 0);
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }

}