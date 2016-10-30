package com.tonilopezmr.tuiterplus.view;

import java.time.Duration;

public class DateFormatter {


  public String format(Duration duration) {
    String result = "";
    int time = 0;

    if (duration.toDays() > 0) {
      time = (int) duration.toDays();
      result = "days";
    }else if (duration.toHours() > 0) {
      time = (int) duration.toHours();
      result = "hours";
    }else if (duration.toMinutes() > 0) {
      time = (int) duration.toMinutes();
      result = "minutes";
    }else if (duration.getSeconds() > 0) {
      time = (int) duration.getSeconds();
      result = "seconds";
    }

    return time + " " + result;
  }
}
