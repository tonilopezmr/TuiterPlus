package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.cmdcontroller.printer.Printer;

import java.util.regex.Matcher;

public class WallCommand extends Command {

  private Printer<Timeline> printer;
  private ReadWallTimeline wallTimeline;

  public WallCommand(String pattern, Printer<Timeline> printer, ReadWallTimeline wallTimeline) {
    super(pattern);
    this.printer = printer;
    this.wallTimeline = wallTimeline;
  }

  @Override
  public Printer process() {
    Matcher matcher = getMatcher();
    Timeline timeline = wallTimeline.getIt(matcher.group(1));
    printer.load(timeline);
    return printer;
  }
}
