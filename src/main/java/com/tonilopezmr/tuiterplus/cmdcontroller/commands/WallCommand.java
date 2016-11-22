package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.regex.Matcher;

public class WallCommand extends Command {

  private View view;
  private ReadWallTimeline wallTimeline;

  public WallCommand(String pattern, View view, ReadWallTimeline wallTimeline) {
    super(pattern);
    this.view = view;
    this.wallTimeline = wallTimeline;
  }

  @Override
  public void process() {
    Matcher matcher = getMatcher();
    Timeline timeline = wallTimeline.getIt(matcher.group(1));
    view.showWall(timeline);
  }
}
