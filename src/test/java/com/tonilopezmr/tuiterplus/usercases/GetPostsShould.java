package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.MockServiceLocatorBuilder;
import com.tonilopezmr.tuiterplus.ServiceLocator;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.repository.MockPostRepository;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostsShould {

  @Test
  public void
  return_posts_when_the_command_is_a_user(){
    User toni = new User("Toni");
    Post post = new Post(toni, "Hello Codurance!", LocalDateTime.now());

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .postRepository(new MockPostRepository(Arrays.asList(post)))
        .build();
    GetPosts getPosts = serviceLocator.getPostsUseCase();

    List<Post> posts = getPosts.getIt("Toni");

    assertTrue(posts.size() > 0);
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }

}