package com.tonilopezmr.tuiterplus.controller.printer;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.view.View;

public class UnfollowPrinter implements Printer<User> {

  private User user;
  private View view;

  public UnfollowPrinter(View view) {
    this.view = view;
  }

  @Override
  public void print() {
    view.showUnfollow(user);
  }

  @Override
  public void load(User printable) {
    this.user = printable;
  }
}
