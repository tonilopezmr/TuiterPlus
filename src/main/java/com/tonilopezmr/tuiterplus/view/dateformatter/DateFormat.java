package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;

/**
 * DateFormat is a class to format DateTime to String,
 * sometimes in some cases needs plural singular differentiate.
 *
 * For example:
 *      - plural: 46 seconds
 *      - singular: 1 second (or a second)
 *
 *      - plural: 2 days
 *      - singular: yesterday
 *
 * <b>Long dateInSeconds</b> is important because DateFormat needs a order by Time in 1 second,
 * to start with the largest time.
 *
 */
public abstract class DateFormat implements Comparable<DateFormat> {

  private Long dateInSeconds;
  private String singular;
  private String plural;

  public DateFormat(long dateInSeconds, String singular, String plural) {
    this.dateInSeconds = dateInSeconds;
    this.singular = singular;
    this.plural = plural;
  }

  public String singlePlural(int count, String singular, String plural) {
    return count==1 ? singular : plural;
  }

  String format(long duration) {
    duration = Math.abs(duration);
    String output = singlePlural((int) duration, singular, plural);

    return duration > 0? String.format(output, duration) : "";
  }

  @Override
  public int compareTo(DateFormat o) {
    return o.dateInSeconds.compareTo(dateInSeconds);
  }

  public abstract String format(Duration duration);
}
