package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;

public abstract class DateFormat implements Comparable<DateFormat> {

  private Long dateInSeconds;
  protected String singular;
  protected String plural;

  public DateFormat(long dateInSeconds, String format) {
    this.dateInSeconds = dateInSeconds;
    this.singular = format;
    this.plural = format;
  }

  public DateFormat(long dateInSeconds, String singular, String plural) {
    this.dateInSeconds = dateInSeconds;
    this.singular = singular;
    this.plural = plural;
  }

  public String singlePlural(int count, String singular, String plural) {
    return count==1 ? singular : plural;
  }

  protected String format(long duration) {
    String output = singlePlural((int) duration, singular, plural);

    return duration > 0? String.format("%d %s", duration, output) : "";
  }

  @Override
  public int compareTo(DateFormat o) {
    return o.dateInSeconds.compareTo(dateInSeconds);
  }

  public abstract String format(Duration duration);
}
