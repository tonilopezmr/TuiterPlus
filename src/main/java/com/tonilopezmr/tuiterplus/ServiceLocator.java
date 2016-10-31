package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.CommandProcessor;
import com.tonilopezmr.tuiterplus.model.TimeProvider;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import com.tonilopezmr.tuiterplus.usercases.AddPost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.View;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Service Locator with all dependencies.
 * <p>
 * To replace dependencies It is necessary to do by extension.
 */
public class ServiceLocator {

  private static ServiceLocator injector;
  private UserRepository userRepository;

  public static void load(ServiceLocator serviceLocator) {
    injector = serviceLocator;
  }

  public static ServiceLocator get() {
    return injector;
  }

  public DataFormatterAssembler getDataFormatterAssembler() {
    return new DataFormatterAssembler(getCreationTime());
  }

  public Scanner getScanner() {
    return new Scanner(System.in);
  }

  public PrintStream getPrintStream() {
    return System.out;
  }

  public TimeProvider getCreationTime() {
    return new TimeProvider();
  }

  public View getView() {
    return new ConsoleCLI(getScanner(), getPrintStream(), getDataFormatterAssembler().assemble());
  }

  public UserRepository getUserRepository() {
    if (userRepository == null) userRepository = new InMemoryUsers();

    return userRepository;
  }

  public AddPost getCreatePostUseCase() {
    return new AddPost(getUserRepository(), getCreationTime());
  }

  public ReadUserTimeline getPostsUseCase() {
    return new ReadUserTimeline(getUserRepository());
  }

  public ReadWallTimeline getWallTimelineUseCase() {
    return new ReadWallTimeline(getUserRepository());
  }

  public FollowUser getFollowUserUseCase() {
    return new FollowUser(getUserRepository());
  }

  public CommandsAssembler commandsAssembler() {
    return new CommandsAssembler(this);
  }

  public CommandProcessor getProcessor() {
    return new CommandProcessor(commandsAssembler().assemble());
  }

  public CommandLine getCommandLine() {
    return new CommandLine(getView(), getProcessor());
  }

  public TuiterPlus getTuiterPlus() {
    return new TuiterPlus(getCommandLine());
  }

}
