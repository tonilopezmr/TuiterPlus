package com.tonilopezmr.tuiterplus.cmdcontroller;

import com.tonilopezmr.tuiterplus.cmdcontroller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.cmdcontroller.printer.Printer;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_when_post() {
    MockAddPost mockCreatePost = new MockAddPost();  //To intercept arguments
    MyCustomPrinter customPrinter = new MyCustomPrinter();
    PostCommand postCommand = new PostCommand(CommandProcessor.POST_COMMAND, customPrinter, mockCreatePost);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(postCommand));

    processor.process(String.format("Toni %s Hello Codurance!", CommandProcessor.POST));

    assertThat(mockCreatePost.getUserNameArg(), is("Toni"));
    assertThat(mockCreatePost.getPostArg(), is("Hello Codurance!"));
  }

  @Test
  public void
  get_user_argument_when_get_wall() {
    MockReadWall mockGetWall = new MockReadWall();
    MyCustomPrinter customPrinter = new MyCustomPrinter();
    WallCommand wallCommand = new WallCommand(CommandProcessor.WALL_COMMAND, customPrinter, mockGetWall);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(wallCommand));

    processor.process(String.format("Toni %s", CommandProcessor.WALL));

    assertThat(mockGetWall.getUserNameArg(), is("Toni"));
  }

  @Test
  public void
  get_follower_and_followed_arguments_when_user_follows() {
    MockFollowUser mockFollowUser = new MockFollowUser();
    MyCustomPrinter customPrinter = new MyCustomPrinter();
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, customPrinter, mockFollowUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

    assertThat(mockFollowUser.getFollower(), is("Toni"));
    assertThat(mockFollowUser.getFollowed(), is("Rodrigo"));
  }

  @Test
  public void
  return_a_expected_printer_when_follows() {
    MockFollowUser mockFollowUser = new MockFollowUser();
    MyCustomPrinter customPrinter = new MyCustomPrinter();
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, customPrinter, mockFollowUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    Printer printer = processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

    assertThat(printer, instanceOf(MyCustomPrinter.class));
  }

  @Test
  public void
  return_a_expected_output_when_read_wall_timeline() {
    MockReadWall mockGetWall = new MockReadWall();
    MyCustomPrinter customPrinter = new MyCustomPrinter();
    WallCommand wallCommand = new WallCommand(CommandProcessor.WALL_COMMAND, customPrinter, mockGetWall);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(wallCommand));

    Printer printer = processor.process(String.format("Toni %s", CommandProcessor.WALL));

    assertThat(printer, instanceOf(MyCustomPrinter.class));
  }

  private class MockAddPost extends AddPost {

    private String userName;
    private String post;

    MockAddPost() {
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
      super(null);
    }

    @Override
    public Timeline getIt(String userName) {
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

  private class MyCustomPrinter implements Printer {
    @Override
    public void print() {

    }

    @Override
    public void load(Object printable) {

    }
  }
}