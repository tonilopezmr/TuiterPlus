package com.tonilopezmr.tuiterplus.cmdcontroller.commands;

import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.cmdcontroller.printer.Printer;

import java.util.regex.Matcher;

public class ReadTimelineCommand extends Command {

  private Printer<Timeline> printer;
  private ReadUserTimeline readUserTimeline;

  public ReadTimelineCommand(String pattern, Printer<Timeline> printer, ReadUserTimeline readUserTimeline) {
    super(pattern);
    this.printer = printer;
    this.readUserTimeline = readUserTimeline;
  }

  @Override
  public Printer process() {
    Matcher matcher = getMatcher();
    Timeline timeline = readUserTimeline.getIt(matcher.group(1));
    printer.load(timeline);
    return printer;
  }
}
