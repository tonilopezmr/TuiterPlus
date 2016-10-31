package com.tonilopezmr.tuiterplus.view.dateformatter;

import com.tonilopezmr.tuiterplus.model.TimeProvider;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.DaysFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.HourFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.MinutesFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.SecondsFormat;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateFormatterShould {

  private DateFormatter getDateFormatter() {
    DateFormatter dateFormatter = new DateFormatter(new TimeProvider());
    dateFormatter.addDateFormat(new HourFormat("an hour", "%d hours"));
    dateFormatter.addDateFormat(new SecondsFormat("a moment", "%d seconds"));
    dateFormatter.addDateFormat(new MinutesFormat("a minute", "%d minutes"));
    dateFormatter.addDateFormat(new DaysFormat("a day", "%d days"));
    return dateFormatter;
  }

  @Test
  public void
  return_difference_in_seconds_when_is_lower_than_a_minute() {
    DateFormatter dateFormatter = getDateFormatter();
    LocalDateTime after = LocalDateTime.now().minusSeconds(30);
    Duration duration = dateFormatter.diff(after);

    String format = dateFormatter.format(duration);

    assertThat(format, is("30 seconds"));
  }

  @Test
  public void
  return_difference_in_minutes_when_is_lower_than_a_hour() {
    DateFormatter dateFormatter = getDateFormatter();
    LocalDateTime after = LocalDateTime.now().minusMinutes(30);
    Duration duration = dateFormatter.diff(after);

    String format = dateFormatter.format(duration);

    assertThat(format, is("30 minutes"));
  }

  @Test
  public void
  return_difference_in_hours_when_is_lower_than_a_day() {
    DateFormatter dateFormatter = getDateFormatter();
    LocalDateTime after = LocalDateTime.now().minusHours(3);
    Duration duration = dateFormatter.diff(after);

    String format = dateFormatter.format(duration);

    assertThat(format, is("3 hours"));
  }

  @Test
  public void
  return_difference_in_days_when_is_more_than_a_hour() {
    DateFormatter dateFormatter = getDateFormatter();
    LocalDateTime after = LocalDateTime.now().minusDays(30);
    Duration duration = dateFormatter.diff(after);

    String format = dateFormatter.format(duration);

    assertThat(format, is("30 days"));
  }

  @Test
  public void
  return_difference_in_seconds_format_when_is_singular() {
    DateFormatter dateFormatter = getDateFormatter();
    LocalDateTime after = LocalDateTime.now().minusSeconds(1);
    Duration duration = dateFormatter.diff(after);

    String format = dateFormatter.format(duration);

    assertThat(format, is("a moment"));
  }

}