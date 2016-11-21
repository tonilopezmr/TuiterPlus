package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.base.TimeProvider;
import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.InMemoryUsers;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePostShould {

  @Test
  public void
  create_user_when_does_not_exist() {
    InMemoryUsers inMemoryUsers = new InMemoryUsers();    //To track the new user
    AddPost addPost = new AddPost(inMemoryUsers, new TimeProvider());

    addPost.doIt("Toni", "Hello Codurance!");

    Optional<User> optional = inMemoryUsers.get("Toni");
    assertTrue(optional.isPresent());
    assertThat(optional.get().getName(), is("Toni"));
  }

  @Test
  public void
  create_post() {
    InMemoryUsers userRepository = new InMemoryUsers();
    AddPost addPost = new AddPost(userRepository, new TimeProvider());

    addPost.doIt("Toni", "Hello Codurance!");

    List<Post> posts = userRepository.getPostsBy(new User("Toni"));
    assertTrue(!posts.isEmpty());
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }
}