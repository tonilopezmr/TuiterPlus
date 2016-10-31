package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.view.Printer;

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
