package com.tonilopezmr.tuiterplus.view.printer;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.view.Printer;
import com.tonilopezmr.tuiterplus.view.View;

import java.util.ArrayList;
import java.util.List;

public class PostsPrinter implements Printer<List<Post>> {

  private List<Post> posts;
  private View view;

  public PostsPrinter(View view) {
    this.view = view;
  }

  @Override
  public void print() {
    view.show(posts);
  }

  @Override
  public void load(List<Post> posts) {
    this.posts = new ArrayList<>(posts);
  }

}
