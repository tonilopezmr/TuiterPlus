package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.repository.InMemoryPosts;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostsShould {

  private PostRepository getToniPosts() {
    InMemoryPosts posts = new InMemoryPosts();
    User toni = new User("Toni");
    posts.create(new Post(toni, "Hello Codurance!", LocalDateTime.now()));
    posts.create(new Post(toni, "Tomorrow I have a Software Engineer exam :(", LocalDateTime.now().minusMinutes(30)));

    return posts;
  }

  @Test
  public void
  return_posts_when_the_command_is_a_user(){
    PostRepository postRepository = getToniPosts();
    ReadUserTimeline readUserTimeline = new ReadUserTimeline(postRepository);

    Timeline posts = readUserTimeline.getIt("Toni");

    assertTrue(!posts.isEmpty());
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
    assertThat(posts.get(1).getPost(), is("Tomorrow I have a Software Engineer exam :("));
  }

}