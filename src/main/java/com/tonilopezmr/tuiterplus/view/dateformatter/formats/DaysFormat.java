package com.tonilopezmr.tuiterplus.view.dateformatter.formats;

import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormat;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DaysFormat extends DateFormat {

  public DaysFormat(String singular, String plural) {
    super(ChronoUnit.DAYS.getDuration().getSeconds(), singular, plural);
  }

  @Override
  public String format(Duration duration) {
    return super.format(duration.toDays());
  }

}
