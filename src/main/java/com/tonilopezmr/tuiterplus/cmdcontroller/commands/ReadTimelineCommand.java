package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.regex.Matcher;

public class ReadTimelineCommand extends Command {

  private View view;
  private ReadUserTimeline readUserTimeline;

  public ReadTimelineCommand(String pattern, View view, ReadUserTimeline readUserTimeline) {
    super(pattern);
    this.view = view;
    this.readUserTimeline = readUserTimeline;
  }

  @Override
  public void process() {
    Matcher matcher = getMatcher();
    Timeline timeline = readUserTimeline.getIt(matcher.group(1));
    view.show(timeline);
  }
}
