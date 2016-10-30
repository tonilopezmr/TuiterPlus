package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;

public class SecondsFormat extends DateFormat {

  public SecondsFormat(String seconds) {
    super(1, seconds);
  }

  public SecondsFormat(String singular, String plural) {
    super(1, singular, plural);
  }

  @Override
  public String format(Duration duration) {
    return super.format(duration.getSeconds());
  }

}
