package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.controller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.controller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.controller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.view.printer.EmptyPrinter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_when_post() {
    MockCreatePost mockCreatePost = new MockCreatePost();  //To intercept arguments
    PostCommand postCommand = new PostCommand(CommandProcessor.POST_COMMAND, new EmptyPrinter(), mockCreatePost);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(postCommand));

    processor.process(String.format("Toni %s Hello Codurance!", CommandProcessor.POST));

    assertThat(mockCreatePost.getUserNameArg(), is("Toni"));
    assertThat(mockCreatePost.getPostArg(), is("Hello Codurance!"));
  }

  @Test public void
  get_user_argument_when_get_wall(){
    MockReadWall mockGetWall = new MockReadWall();
    WallCommand wallCommand = new WallCommand(CommandProcessor.WALL_COMMAND, new EmptyPrinter(), mockGetWall);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(wallCommand));

    processor.process(String.format("Toni %s", CommandProcessor.WALL));

    assertThat(mockGetWall.getUserNameArg(), is("Toni"));
  }

  @Test
  public void
  get_follower_and_followed_arguments_when_user_follows() {
    MockFollowUser mockFollowUser = new MockFollowUser();
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, new EmptyPrinter(), mockFollowUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

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

  private class MockReadWall extends ReadWallTimeline {

    private String userName;

    MockReadWall() {
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