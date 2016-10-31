package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.post.Post;

import java.util.List;

/**
 * What can my view do
 */
public interface View {

  void showPrompt();
  String readCommandLine();
  void showFareWell();
  void show(List<Post> posts);
  void showWallTimeline(List<Post> posts);

}
