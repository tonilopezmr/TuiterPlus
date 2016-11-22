package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.cmdcontroller.CommandProcessor;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.Command;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.ReadTimelineCommand;
import com.tonilopezmr.tuiterplus.cmdcontroller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.ArrayList;
import java.util.List;

public class CommandsAssembler {

  private ServiceLocator serviceLocator;

  public CommandsAssembler(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  public List<Command> assemble() {
    View view = serviceLocator.getView();
    AddPost createPostUseCase = serviceLocator.getCreatePostUseCase();
    FollowUser followUserUseCase = serviceLocator.getFollowUserUseCase();
    ReadWallTimeline wallTimelineUseCase = serviceLocator.getWallTimelineUseCase();
    ReadUserTimeline postsUseCase = serviceLocator.getPostsUseCase();

    ArrayList<Command> commands = new ArrayList<>();
    commands.add(getPostCommand(createPostUseCase));
    commands.add(getFollowCommand(followUserUseCase));
    commands.add(getWallCommand(view, wallTimelineUseCase));
    commands.add(getReadTimelineCommand(view, postsUseCase));
    return commands;
  }

  private ReadTimelineCommand getReadTimelineCommand(View view, ReadUserTimeline postsUseCase) {
    return new ReadTimelineCommand(CommandProcessor.READ_COMMAND, view, postsUseCase);
  }

  private WallCommand getWallCommand(View view, ReadWallTimeline wallTimelineUseCase) {
    return new WallCommand(CommandProcessor.WALL_COMMAND, view, wallTimelineUseCase);
  }

  private FollowCommand getFollowCommand(FollowUser followUserUseCase) {
    return new FollowCommand(CommandProcessor.FOLLOW_COMMAND, followUserUseCase);
  }

  private PostCommand getPostCommand(AddPost createPostUseCase) {
    return new PostCommand(CommandProcessor.POST_COMMAND, createPostUseCase);
  }

}
