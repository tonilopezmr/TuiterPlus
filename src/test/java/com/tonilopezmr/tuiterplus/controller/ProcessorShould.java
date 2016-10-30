package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.MockServiceLocatorBuilder;
import com.tonilopezmr.tuiterplus.ServiceLocator;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_from_the_command() {
    MockCreatePost mockCreatePost = new MockCreatePost();  //To intercept arguments
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .createPost(mockCreatePost)
        .build();
    Processor processor = serviceLocator.getProcessor();

    processor.process("Toni -> Hello Codurance!");

    assertThat(mockCreatePost.getUserName(), is("Toni"));
    assertThat(mockCreatePost.getPost(), is("Hello Codurance!"));
  }

  private class MockCreatePost extends CreatePost {

    private String userName;
    private String post;

    public MockCreatePost() {
      super(null, null);
    }

    @Override
    public void doIt(String userName, String post) {
      this.userName = userName;
      this.post = post;
    }

    public String getUserName() {
      return userName;
    }

    public String getPost() {
      return post;
    }
  }
}