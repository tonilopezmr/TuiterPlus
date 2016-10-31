package com.tonilopezmr.tuiterplus.view.printer;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.view.Printer;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.List;

public class WallTimelinePrinter implements Printer<List<Post>> {

  private List<Post> posts;
  private View view;

  public WallTimelinePrinter(View view) {
    this.view = view;
  }

  @Override
  public void print() {
    view.showWallTimeline(posts);
  }

  @Override
  public void load(List<Post> posts) {
    this.posts = posts;
  }
}
