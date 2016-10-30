package com.tonilopezmr.tuiterplus.view.dateformatter;


import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HourFormat extends DateFormat {

  public HourFormat(String format) {
    super(ChronoUnit.HOURS.getDuration().getSeconds(), format);
  }

  public HourFormat(String singular, String plural) {
    super(ChronoUnit.HOURS.getDuration().getSeconds(), singular, plural);
  }

  @Override
  public String format(Duration duration) {
    return super.format(duration.toHours());
  }

}
