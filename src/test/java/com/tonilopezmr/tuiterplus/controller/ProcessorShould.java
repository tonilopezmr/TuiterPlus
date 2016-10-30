package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.GetWallTimeline;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_when_post() {
    MockCreatePost mockCreatePost = new MockCreatePost();  //To intercept arguments
    Processor processor = new Processor(null, mockCreatePost, null, null);

    processor.process(String.format("Toni %s Hello Codurance!", Processor.POST_COMMAND));

    assertThat(mockCreatePost.getUserNameArg(), is("Toni"));
    assertThat(mockCreatePost.getPostArg(), is("Hello Codurance!"));
  }

  @Test public void
  get_user_argument_when_get_wall(){
    MockGetWall mockGetWall = new MockGetWall();
    Processor processor = new Processor(null, null, mockGetWall, null);

    processor.process(String.format("Toni %s", Processor.WALL_COMMAND));

    assertThat(mockGetWall.getUserNameArg(), is("Toni"));
  }

  @Test
  public void
  get_follower_and_followed_arguments_when_user_follows() {
    MockFollowUser mockFollowUser = new MockFollowUser();
    Processor processor = new Processor(null, null, null, mockFollowUser);

    processor.process(String.format("Toni %s Rodrigo", Processor.FOLLOW_COMMAND));

    assertThat(mockFollowUser.getFollower(), is("Toni"));
    assertThat(mockFollowUser.getFollowed(), is("Rodrigo"));
  }

  private class MockCreatePost extends CreatePost {

    private String userName;
    private String post;

    MockCreatePost() {
      super(null, null);
    }

    @Override
    public void doIt(String userName, String post) {
      this.userName = userName;
      this.post = post;
    }

    String getUserNameArg() {
      return userName;
    }

    String getPostArg() {
      return post;
    }
  }

  private class MockGetWall extends GetWallTimeline {

    private String userName;

    MockGetWall() {
      super(null, null);
    }

    @Override
    public List<Post> getIt(String userName) {
      this.userName = userName;
      return null;
    }

    String getUserNameArg() {
      return this.userName;
    }
  }

  private class MockFollowUser extends FollowUser {

    private String follower;
    private String followed;

    public MockFollowUser() {
      super(null);
    }

    @Override
    public void doIt(String follower, String followed) {
      this.follower = follower;
      this.followed = followed;
    }

    public String getFollower() {
      return follower;
    }

    public String getFollowed() {
      return followed;
    }
  }
}