package com.tonilopezmr.tuiterplus.view.dateformatter;

import com.tonilopezmr.tuiterplus.base.TimeProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DateFormatter is a class to format Duration to String with a collection of DateFormat to choose one.
 * <p>
 * It is easy to create news DateFormat.
 */
public class DateFormatter {

  private TimeProvider timeProvider;
  private List<DateFormat> dateFormats;

  public DateFormatter(TimeProvider timeProvider) {
    this.dateFormats = new ArrayList<>();
    this.timeProvider = timeProvider;
  }

  public void addDateFormat(DateFormat dateFormat) {
    this.dateFormats.add(dateFormat);
  }

  public Duration diff(LocalDateTime dateTime) {
    return timeProvider.diffNow(dateTime);
  }

  /**
   * Format Duration to String depends on the DateFormat objects.
   * <p>
   * * Filter by results with String length > 0 (has result)
   *
   * @param duration
   * @return
   */
  public String format(Duration duration) {
    String first = dateFormats.stream()
        .sorted(DateFormat::compareTo)
        .filter(it -> !it.format(duration).isEmpty())
        .map(dateFormat -> dateFormat.format(duration))
        .findFirst().get();

    return first;
  }

}
