package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DateFormatter is a class to format Duration to String with a collection of DateFormat to choose one.
 * <p>
 * It is easy to add news DateFormat.
 */
public class DateFormatter {

  private List<DateFormat> dateFormats;

  public DateFormatter() {
    this.dateFormats = new ArrayList<>();
  }

  public void addDateFormat(DateFormat dateFormat) {
    this.dateFormats.add(dateFormat);
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
    Optional<String> first = dateFormats.stream()
        .sorted(DateFormat::compareTo)
        .filter(it -> !it.format(duration).isEmpty())
        .map(dateFormat -> dateFormat.format(duration))
        .findFirst();

    return first.get();
  }

}
