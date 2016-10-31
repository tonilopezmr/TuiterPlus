package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.post.Timeline;

/**
 * What my view can do
 */
public interface View {

  void showPrompt();
  String readCommandLine();
  void showFareWell();
  void show(Timeline timeline);
  void showWallTimeline(Timeline posts);

}
