package com.tonilopezmr.tuiterplus.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeProvider {

  public LocalDateTime timeNow() {
    return LocalDateTime.now().minusSeconds(1); //at least one second
  }

  public Duration diffNow(LocalDateTime localDateTime) {
    return Duration.between(localDateTime, LocalDateTime.now());
  }

}
