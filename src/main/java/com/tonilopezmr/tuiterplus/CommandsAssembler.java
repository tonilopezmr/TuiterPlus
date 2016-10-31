package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandProcessor;
import com.tonilopezmr.tuiterplus.controller.commands.Command;
import com.tonilopezmr.tuiterplus.controller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.controller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.controller.commands.ReadTimelineCommand;
import com.tonilopezmr.tuiterplus.controller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.controller.printer.EmptyPrinter;
import com.tonilopezmr.tuiterplus.controller.printer.TimelinePrinter;
import com.tonilopezmr.tuiterplus.controller.printer.WallTimelinePrinter;
import com.tonilopezmr.tuiterplus.usercases.AddPost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.usercases.ReadWallTimeline;
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
    commands.add(new PostCommand(CommandProcessor.POST_COMMAND, new EmptyPrinter(), createPostUseCase));
    commands.add(new FollowCommand(CommandProcessor.FOLLOW_COMMAND, new EmptyPrinter(), followUserUseCase));
    commands.add(new WallCommand(CommandProcessor.WALL_COMMAND, new WallTimelinePrinter(view), wallTimelineUseCase));
    commands.add(new ReadTimelineCommand(CommandProcessor.READ_COMMAND, new TimelinePrinter(view), postsUseCase));
    return commands;
  }

}
