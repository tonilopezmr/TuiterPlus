package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.MockServiceLocatorBuilder;
import com.tonilopezmr.tuiterplus.ServiceLocator;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.GetWallTimeline;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_after_post() {
    MockCreatePost mockCreatePost = new MockCreatePost();  //To intercept arguments
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .createPost(mockCreatePost)
        .build();
    Processor processor = serviceLocator.getProcessor();

    processor.process("Toni -> Hello Codurance!");

    assertThat(mockCreatePost.getUserNameArg(), is("Toni"));
    assertThat(mockCreatePost.getPostArg(), is("Hello Codurance!"));
  }

  @Test public void
  get_user_argument_when_get_wall(){
    MockGetWall mockGetWall = new MockGetWall();
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .getWall(mockGetWall)
        .build();
    Processor processor = serviceLocator.getProcessor();

    processor.process("Toni wall");

    assertThat(mockGetWall.getUserNameArg(), is("Toni"));
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

    public String getUserNameArg() {
      return userName;
    }

    public String getPostArg() {
      return post;
    }
  }

  private class MockGetWall extends GetWallTimeline {

    private String userName;

    public MockGetWall() {
      super(null, null);
    }

    @Override
    public List<Post> getIt(String userName) {
      this.userName = userName;
      return null;
    }

    public String getUserNameArg() {
      return this.userName;
    }
  }
}