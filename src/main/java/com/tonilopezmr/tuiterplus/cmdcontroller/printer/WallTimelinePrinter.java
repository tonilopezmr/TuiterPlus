package com.tonilopezmr.tuiterplus.cmdcontroller.printer;

import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.view.View;

/**
 * Print wall timeline.
 */
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
