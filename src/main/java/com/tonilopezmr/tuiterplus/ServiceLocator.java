package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.CommandProcessor;
import com.tonilopezmr.tuiterplus.controller.commands.Command;
import com.tonilopezmr.tuiterplus.controller.commands.FollowCommand;
import com.tonilopezmr.tuiterplus.controller.commands.PostCommand;
import com.tonilopezmr.tuiterplus.controller.commands.ReadTimelineCommand;
import com.tonilopezmr.tuiterplus.controller.commands.WallCommand;
import com.tonilopezmr.tuiterplus.model.CreationTime;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryPosts;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.ReadUserTimeline;
import com.tonilopezmr.tuiterplus.usercases.ReadWallTimeline;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.View;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;
import com.tonilopezmr.tuiterplus.view.dateformatter.DaysFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.HourFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.MinutesFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.SecondsFormat;
import com.tonilopezmr.tuiterplus.view.printer.EmptyPrinter;
import com.tonilopezmr.tuiterplus.view.printer.PostsPrinter;
import com.tonilopezmr.tuiterplus.view.printer.WallTimelinePrinter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service Locator with all dependencies.
 * <p>
 * To replace dependencies It is necessary to do by extension.
 */
public class ServiceLocator {

  private static ServiceLocator injector;
  private PostRepository postRepository;
  private UserRepository userRepository;

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

  public CreationTime getCreationTime() {
    return new CreationTime();
  }

  public View getView() {
    return new ConsoleCLI(getScanner(), getPrintStream(), getDatterFormater());
  }

  public PostRepository getPostRepository() {
    if (postRepository == null) postRepository = new InMemoryPosts();

    return postRepository;
  }

  public UserRepository getUserRepository() {
    if (userRepository == null) userRepository = new InMemoryUsers();

    return userRepository;
  }

  public CreatePost getCreatePostUseCase() {
    return new CreatePost(getUserRepository(), getPostRepository(), getCreationTime());
  }

  public ReadUserTimeline getPostsUseCase() {
    return new ReadUserTimeline(getPostRepository());
  }

  public ReadWallTimeline getWallTimelineUseCase() {
    return new ReadWallTimeline(getUserRepository(), getPostRepository());
  }

  public FollowUser getFollowUserUseCase() {
    return new FollowUser(getUserRepository());
  }

  public List<Command> getCommands() {
    ArrayList<Command> commands = new ArrayList<>();
    commands.add(new PostCommand(CommandProcessor.POST_COMMAND, new EmptyPrinter(), getCreatePostUseCase()));
    commands.add(new FollowCommand(CommandProcessor.FOLLOW_COMMAND, new EmptyPrinter(), getFollowUserUseCase()));
    commands.add(new WallCommand(CommandProcessor.WALL_COMMAND, new WallTimelinePrinter(getView()), getWallTimelineUseCase()));
    commands.add(new ReadTimelineCommand(CommandProcessor.READ_COMMAND, new PostsPrinter(getView()), getPostsUseCase()));
    return commands;
  }

  public CommandProcessor getProcessor() {
    return new CommandProcessor(getCommands());
  }

  public CommandLine getCommandLine() {
    return new CommandLine(getView(), getProcessor());
  }

  public TuiterPlus getTuiterPlus() {
    return new TuiterPlus(getCommandLine());
  }

}
