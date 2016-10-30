package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.MockServiceLocatorBuilder;
import com.tonilopezmr.tuiterplus.ServiceLocator;
import com.tonilopezmr.tuiterplus.controller.Processor;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.repository.InMemoryPosts;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
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
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .userRepository(inMemoryUsers)
        .build();
    CreatePost createPost = serviceLocator.getCreatePostUseCase();

    createPost.doIt("Toni", "Hello Codurance!");

    Optional<User> optional = inMemoryUsers.get("Toni");
    assertTrue(optional.isPresent());
    assertThat(optional.get().getName(), is("Toni"));
  }

  @Test
  public void
  create_post() {
    InMemoryPosts postRepository = new InMemoryPosts();
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .postRepository(postRepository)
        .build();
    Processor processor = serviceLocator.getProcessor();

    processor.process("Toni -> Hello Codurance!");

    List<Post> posts = postRepository.getPostsBy(new User("Toni"));
    assertTrue(posts.size() > 0);
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }
}