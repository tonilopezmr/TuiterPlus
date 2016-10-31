package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.TimeProvider;
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
    CreatePost createPost = new CreatePost(inMemoryUsers, new InMemoryPosts(), new TimeProvider());

    createPost.doIt("Toni", "Hello Codurance!");

    Optional<User> optional = inMemoryUsers.get("Toni");
    assertTrue(optional.isPresent());
    assertThat(optional.get().getName(), is("Toni"));
  }

  @Test
  public void
  create_post() {
    InMemoryPosts postRepository = new InMemoryPosts();
    CreatePost createPost = new CreatePost(new InMemoryUsers(), postRepository, new TimeProvider());

    createPost.doIt("Toni", "Hello Codurance!");

    List<Post> posts = postRepository.getPostsBy(new User("Toni"));
    assertTrue(!posts.isEmpty());
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }
}