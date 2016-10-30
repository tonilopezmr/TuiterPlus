package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
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

  private PostRepository getToniPosts() {
    User toni = new User("Toni");
    Post post = new Post(toni, "Hello Codurance!", LocalDateTime.now());
    Post post1 = new Post(toni, "Tomorrow I have a Software Engineer exam :(", LocalDateTime.now().minusMinutes(30));

    return new MockPostRepository(Arrays.asList(post1, post));
  }

  @Test
  public void
  return_posts_when_the_command_is_a_user(){
    PostRepository postRepository = getToniPosts();
    GetPosts getPosts = new GetPosts(postRepository);

    List<Post> posts = getPosts.getIt("Toni");

    assertTrue(!posts.isEmpty());
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
    assertThat(posts.get(1).getPost(), is("Tomorrow I have a Software Engineer exam :("));
  }

}