package com.tonilopezmr.tuiterplus.view.dateformatter;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateFormatterShould {

  private DateFormatter getDateFormatter() {
    DateFormatter dateFormatter = new DateFormatter();
    dateFormatter.addDateFormat(new HourFormat("an hour", "%d hours"));
    dateFormatter.addDateFormat(new SecondsFormat("a moment", "%d seconds"));
    dateFormatter.addDateFormat(new MinutesFormat("a minute", "%d minutes"));
    dateFormatter.addDateFormat(new DaysFormat("a day", "%d days"));
    return dateFormatter;
  }

  @Test
  public void
  return_difference_in_seconds_when_is_lower_than_a_minute(){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after = now.minusSeconds(30);
    Duration duration = Duration.between(after, now);

    DateFormatter dateFormatter =   getDateFormatter();
    String format = dateFormatter.format(duration);

    assertThat(format, is("30 seconds"));
  }

  @Test
  public void
  return_difference_in_minutes_when_is_lower_than_a_hour(){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after = now.minusMinutes(30);
    Duration duration = Duration.between(after, now);

    DateFormatter dateFormatter = getDateFormatter();
    String format = dateFormatter.format(duration);

    assertThat(format, is("30 minutes"));
  }

  @Test
  public void
  return_difference_in_hours_when_is_lower_than_a_day(){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after = now.minusHours(3);
    Duration duration = Duration.between(after, now);

    DateFormatter dateFormatter = getDateFormatter();
    String format = dateFormatter.format(duration);

    assertThat(format, is("3 hours"));
  }

  @Test
  public void
  return_difference_in_days_when_is_more_than_a_hour(){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after = now.minusDays(30);
    Duration duration = Duration.between(after, now);

    DateFormatter dateFormatter = getDateFormatter();
    String format = dateFormatter.format(duration);

    assertThat(format, is("30 days"));
  }

  @Test public void
  return_difference_in_seconds_format_when_is_singular(){
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime after = LocalDateTime.now();
    Duration duration = Duration.between(after, now);

    DateFormatter dateFormatter = getDateFormatter();
    String format = dateFormatter.format(duration);

    assertThat(format, is("a moment"));
  }

}