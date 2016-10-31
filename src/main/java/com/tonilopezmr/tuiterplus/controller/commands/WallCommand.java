package com.tonilopezmr.tuiterplus.controller.commands;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.view.Printer;

import java.util.List;
import java.util.regex.Matcher;

public class WallCommand extends Command {

  private Printer<List<Post>> printer;
  private ReadWallTimeline wallTimeline;

  public WallCommand(String pattern, Printer<List<Post>> printer, ReadWallTimeline wallTimeline) {
    super(pattern);
    this.printer = printer;
    this.wallTimeline = wallTimeline;
  }

  @Override
  public Printer process() {
    Matcher matcher = getMatcher();
    List<Post> timeline = wallTimeline.getIt(matcher.group(1));
    printer.load(timeline);
    return printer;
  }
}
