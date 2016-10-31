package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.post.Timeline;
import com.tonilopezmr.tuiterplus.model.user.User;

/**
 * What my view can do
 */
public interface View {

  void showWelcome();
  void showFareWell();
  void showPrompt();
  String readCommandLine();
  void show(Timeline timeline);
  void showWallTimeline(Timeline posts);
  void showUnfollow(User user);

}
