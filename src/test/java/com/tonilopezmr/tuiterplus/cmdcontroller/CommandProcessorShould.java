package com.tonilopezmr.tuiterplus.cmdcontroller;

import com.tonilopezmr.tuiterplus.cmdcontroller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.printer.EmptyPrinter;
import com.tonilopezmr.tuiterplus.cmdcontroller.printer.Printer;
import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
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
    Printer printer = mock(Printer.class);
    PostCommand postCommand = new PostCommand(CommandProcessor.POST_COMMAND, printer, createPost);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(postCommand));

    processor.process(String.format("Toni %s Hello Codurance!", CommandProcessor.POST));

    verify(createPost).doIt(argsCaptor.capture(), argsCaptor.capture());
    assertThat(argsCaptor.getAllValues(), is(Arrays.asList("Toni", "Hello Codurance!")));
  }

  @Test
  public void
  get_user_argument_when_get_wall() {
    ReadWallTimeline readWallTimeline = mock(ReadWallTimeline.class);
    Printer printer = mock(Printer.class);
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
    Printer printer = mock(Printer.class);
    ArgumentCaptor<String> argsCaptor = ArgumentCaptor.forClass(String.class);
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, printer, followUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

    assertThat(argsCaptor.getAllValues(), is(Arrays.asList("Toni", "Rodrigo")));
  }

  @Test
  public void
  return_a_expected_printer_when_follows() {
    FollowUser followUser = mock(FollowUser.class);
    Printer emptyPrinter = new EmptyPrinter();
    FollowCommand followCommand = new FollowCommand(CommandProcessor.FOLLOW_COMMAND, emptyPrinter, followUser);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(followCommand));

    Printer printer = processor.process(String.format("Toni %s Rodrigo", CommandProcessor.FOLLOWS));

    assertThat(printer, instanceOf(EmptyPrinter.class));
  }

  @Test
  public void
  return_a_expected_output_when_read_wall_timeline() {
    ReadWallTimeline mockGetWall = mock(ReadWallTimeline.class);
    Printer emptyPrinter = new EmptyPrinter();
    WallCommand wallCommand = new WallCommand(CommandProcessor.WALL_COMMAND, emptyPrinter, mockGetWall);
    CommandProcessor processor = new CommandProcessor(Arrays.asList(wallCommand));

    Printer printer = processor.process(String.format("Toni %s", CommandProcessor.WALL));

    assertThat(printer, instanceOf(EmptyPrinter.class));
  }

}