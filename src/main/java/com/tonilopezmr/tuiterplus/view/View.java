package com.tonilopezmr.tuiterplus.view;

import com.tonilopezmr.tuiterplus.model.Post;

import java.util.List;

/**
 * What can my view do
 */
public interface View {

  void showPrompt();
  String readCommandLine();
  void show(String message);
  void show(List<Post> posts);

}
