package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.TimeProvider;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.DaysFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.HourFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.MinutesFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.formats.SecondsFormat;

public class DataFormatterAssembler {

  private TimeProvider timeProvider;

  public DataFormatterAssembler(TimeProvider timeProvider) {
    this.timeProvider = timeProvider;
  }

  public DateFormatter assemble() {
    DateFormatter dateFormatter = new DateFormatter(timeProvider);
    dateFormatter.addDateFormat(new HourFormat("an hour", "%d hours"));
    dateFormatter.addDateFormat(new SecondsFormat("a moment", "%d seconds"));
    dateFormatter.addDateFormat(new DaysFormat("a day", "%d days"));
    dateFormatter.addDateFormat(new MinutesFormat("a minute", "%d minutes"));
    return dateFormatter;
  }

}
