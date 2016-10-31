package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostsShould {

  private UserRepository getToniPosts() {
    InMemoryUsers users = new InMemoryUsers();
    User toni = new User("Toni");
    users.add(new Post(toni, "Hello Codurance!", LocalDateTime.now()));
    users.add(new Post(toni, "Tomorrow I have a Software Engineer exam :(", LocalDateTime.now().minusMinutes(30)));

    return users;
  }

  @Test
  public void
  return_posts_when_the_command_is_a_user() {
    UserRepository userRepository = getToniPosts();
    ReadUserTimeline readUserTimeline = new ReadUserTimeline(userRepository);

    Timeline posts = readUserTimeline.getIt("Toni");

    assertTrue(!posts.isEmpty());
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
    assertThat(posts.get(1).getPost(), is("Tomorrow I have a Software Engineer exam :("));
  }

}