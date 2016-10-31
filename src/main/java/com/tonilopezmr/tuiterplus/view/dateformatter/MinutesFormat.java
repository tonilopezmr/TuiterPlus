package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MinutesFormat extends DateFormat {

  public MinutesFormat(String singular, String plural) {
    super(ChronoUnit.MINUTES.getDuration().getSeconds(), singular, plural);
  }

  @Override
  public String format(Duration duration) {
    return super.format(duration.toMinutes());
  }

}
