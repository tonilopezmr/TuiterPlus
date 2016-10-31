package com.tonilopezmr.tuiterplus.controller.printer;

import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.view.Printer;
import com.tonilopezmr.tuiterplus.view.View;

public class WallTimelinePrinter implements Printer<Timeline> {

  private Timeline timeline;
  private View view;

  public WallTimelinePrinter(View view) {
    this.view = view;
  }

  @Override
  public void print() {
    view.showWallTimeline(timeline);
  }

  @Override
  public void load(Timeline timeline) {
    this.timeline = timeline;
  }
}
