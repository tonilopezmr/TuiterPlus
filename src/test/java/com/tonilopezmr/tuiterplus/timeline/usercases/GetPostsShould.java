package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;
import com.tonilopezmr.tuiterplus.user.InMemoryUsers;
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