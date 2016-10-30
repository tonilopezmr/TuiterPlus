package com.tonilopezmr.tuiterplus.view.dateformatter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DateFormatter {

  private List<DateFormat> dateFormats;

  public DateFormatter() {
    this.dateFormats = new ArrayList<>();
  }

  public void addDateFormat(DateFormat dateFormat) {
    this.dateFormats.add(dateFormat);
  }

  public List<DateFormat> getDateFormats() {
    return dateFormats;
  }

  public String format(Duration duration) {
    Optional<String> first = dateFormats.stream()
        .sorted((o1, o2) -> o1.compareTo(o2))
        .filter(it -> !it.format(duration).isEmpty())
        .map(dateFormat -> dateFormat.format(duration))
        .findFirst();

    return first.get();
  }

}
