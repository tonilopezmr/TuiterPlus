package com.tonilopezmr.tuiterplus.controller.printer;

import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.view.View;

/**
 * Print timeline when read user.
 */
public class TimelinePrinter implements Printer<Timeline> {

  private Timeline timeline;
  private View view;

  public TimelinePrinter(View view) {
    this.view = view;
  }

  @Override
  public void print() {
    view.show(timeline);
  }

  @Override
  public void load(Timeline timeline) {
    this.timeline = timeline;
  }

}
