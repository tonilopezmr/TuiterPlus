package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.controller.CommandLine;
import com.tonilopezmr.tuiterplus.controller.Processor;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryPosts;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;
import com.tonilopezmr.tuiterplus.usercases.FollowUser;
import com.tonilopezmr.tuiterplus.usercases.GetPosts;
import com.tonilopezmr.tuiterplus.usercases.GetWallTimeline;
import com.tonilopezmr.tuiterplus.view.ConsoleCLI;
import com.tonilopezmr.tuiterplus.view.View;
import com.tonilopezmr.tuiterplus.view.dateformatter.DateFormatter;
import com.tonilopezmr.tuiterplus.view.dateformatter.DaysFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.HourFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.MinutesFormat;
import com.tonilopezmr.tuiterplus.view.dateformatter.SecondsFormat;

import java.io.PrintStream;
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

  public View getConsoleCLI() {
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
    return new CreatePost(getUserRepository(), getPostRepository());
  }

  public GetPosts getPostsUseCase() {
    return new GetPosts(getPostRepository());
  }

  public GetWallTimeline getWallTimeline() {
    return new GetWallTimeline(getUserRepository(), getPostRepository());
  }

  public FollowUser getFollowUser() {
    return new FollowUser(getUserRepository());
  }

  public Processor getProcessor() {
    return new Processor(getPostsUseCase(), getCreatePostUseCase(), getWallTimeline(), getFollowUser());
  }

  public CommandLine getCommandLine() {
    return new CommandLine(getConsoleCLI(), getProcessor());
  }

  public TuiterPlus getTuiterPlus() {
    return new TuiterPlus(getCommandLine());
  }
}
