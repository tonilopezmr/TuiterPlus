package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.base.TimeProvider;
import com.tonilopezmr.tuiterplus.cmdcontroller.CommandLine;
import com.tonilopezmr.tuiterplus.cmdcontroller.CommandProcessor;
import com.tonilopezmr.tuiterplus.timeline.usercases.AddPost;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.timeline.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.user.InMemoryUsers;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
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
    return new View(getScanner(), getPrintStream(), getDataFormatterAssembler().assemble());
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
    return new CommandsAssembler(injector);
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
