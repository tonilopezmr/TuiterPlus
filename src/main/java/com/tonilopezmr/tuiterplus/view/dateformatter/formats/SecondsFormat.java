package com.tonilopezmr.tuiterplus.view.dateformatter.formats;

import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormat;

import java.time.Duration;

public class SecondsFormat extends DateFormat {

  public SecondsFormat(String singular, String plural) {
    super(1, singular, plural);
  }

  @Override
  public String format(Duration duration) {
    return super.format(duration.getSeconds());
  }

}
