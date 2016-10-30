package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.Processor;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryPostCollection;
import com.tonilopezmr.tuiterplus.usercases.GetPosts;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.View;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;
import com.tonilopezmr.tuiterplus.view.dateformatter.DaysFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.HourFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.MinutesFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.SecondsFormat;

import java.io.PrintStream;
import java.util.Scanner;

public class ServiceLocator {

  private static ServiceLocator injector;

  public static void load(ServiceLocator serviceLocator) {
    injector = serviceLocator;
  }

  public static ServiceLocator get() {
    return injector;
  }

  public DateFormatter getDatterFormater() {
    DateFormatter dateFormatter = new DateFormatter();
    dateFormatter.addDateFormat(new HourFormat("an hour", "%d hours"));
    dateFormatter.addDateFormat(new SecondsFormat("a moment", "%d seconds"));
    dateFormatter.addDateFormat(new DaysFormat("a day", "%d days"));
    dateFormatter.addDateFormat(new MinutesFormat("a minute", "%d minutes"));
    return dateFormatter;
  }

  public Scanner getScanner() {
    return new Scanner(System.in);
  }

  public PrintStream getPrintStream() {
    return System.out;
  }

  public View getConsoleCLI() {
    return new ConsoleCLI(getScanner(), getPrintStream(), getDatterFormater());
  }

  public PostRepository getPostRepository() {
    return new InMemoryPostCollection();
  }

  public GetPosts getPostsUseCase() {
    return new GetPosts(getPostRepository());
  }

  public Processor getProcessor() {
    return new Processor(getPostsUseCase());
  }

  public CommandLine getCommandLine() {
    return new CommandLine(getConsoleCLI(), getProcessor());
  }

  public TuiterPlus getTuiterPlus() {
    return new TuiterPlus(getCommandLine());
  }
}
