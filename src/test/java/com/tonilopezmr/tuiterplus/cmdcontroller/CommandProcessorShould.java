package com.tonilopezmr.tuiterplus.cmdcontroller;

import com.tonilopezmr.tuiterplus.cmdcontroller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.view.View;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandProcessorShould {

  @Test
  public void
  get_user_and_post_arguments_when_post() {
    AddPost createPost = mock(AddPost.class);
    ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
    PostCommand postCommand = new PostCommand(CommandProcessor.POST_COMMAND, createPost);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(postCommand));

    processor.process(String.format("Toni %s Hello Codurance!", CommandProcessor.POST));

    verify(createPost).doIt(argsCaptor.capture(), argsCaptor.capture());
    assertThat(argsCaptor.getAllValues(), is(Arrays.asList("Toni", "Hello Codurance!")));
  }

  @Test
  public void
  get_user_argument_when_get_wall() {
    ReadWallTimeline readWallTimeline = mock(ReadWallTimeline.class);
    View printer = mock(View.class);
    ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
    WallCommand wallCommand = new WallCommand(CommandProcessor.WALL_COMMAND, printer, readWallTimeline);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(wallCommand));

    processor.process(String.format("Toni %s", CommandProcessor.WALL));

    verify(readWallTimeline).getIt(argsCaptor.capture());
    assertThat(argsCaptor.getValue(), is("Toni"));
  }

  @Test
  public void
  get_follower_and_followed_arguments_when_user_follows() {
    FollowUser followUser = mock(FollowUser.class);
    ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, followUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

    verify(followUser).doIt(argsCaptor.capture(), argsCaptor.capture());
    assertThat(argsCaptor.getAllValues(), is(Arrays.asList("Toni", "Rodrigo")));
  }

}