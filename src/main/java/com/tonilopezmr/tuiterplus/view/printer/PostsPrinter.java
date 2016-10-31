package com.tonilopezmr.tuiterplus.view.printer;

import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.view.Printer;
import com.tonilopezmr.tuiterplus.view.View;

public class PostsPrinter implements Printer<Timeline> {

  private Timeline timeline;
  private View view;

  public PostsPrinter(View view) {
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
