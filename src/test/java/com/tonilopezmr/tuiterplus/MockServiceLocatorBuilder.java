package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.CreationTime;
import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Mock Service Locator to allow replace the dependencies.
 * <p>
 * Very helpful to mock in high level tests.
 */
public class MockServiceLocatorBuilder {

  private Scanner scanner;
  private PrintStream printStream;
  private PostRepository postRepository;
  private UserRepository userRepository;
  private int timeDelay;

  public MockServiceLocatorBuilder scanner(Scanner scanner) {
    this.scanner = scanner;
    return this;
  }

  public MockServiceLocatorBuilder printStream(PrintStream printStream) {
    this.printStream = printStream;
    return this;
  }

  public MockServiceLocatorBuilder postRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
    return this;
  }

  public MockServiceLocatorBuilder userRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
    return this;
  }

  public MockServiceLocatorBuilder creationTimeBeetweenPosts(int millis) {
    this.timeDelay = millis;
    return this;
  }

  public ServiceLocator build() {
    return new ServiceLocator() {
      @Override
      public Scanner getScanner() {
        return scanner != null ? scanner : super.getScanner();
      }

      @Override
      public PrintStream getPrintStream() {
        return printStream != null ? printStream : super.getPrintStream();
      }

      @Override
      public PostRepository getPostRepository() {
        return postRepository != null ? postRepository : super.getPostRepository();
      }

      @Override
      public UserRepository getUserRepository() {
        return userRepository != null ? userRepository : super.getUserRepository();
      }

      @Override
      public CreationTime getCreationTime() {
        return timeDelay > 0 ? new MockCreationTime(timeDelay) : super.getCreationTime();
      }
    };
  }

  private class MockCreationTime extends CreationTime {

    private int timeDelay;

    public MockCreationTime(int timeDelay) {
      this.timeDelay = timeDelay;
    }

    @Override
    public LocalDateTime now() {
      try {
        Thread.sleep(timeDelay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return super.now();
    }
  }
}
