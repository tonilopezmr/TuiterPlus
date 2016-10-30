package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.post.PostRepository;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.usercases.CreatePost;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Mock Service Locator to allow replace the dependencies.
 */
public class MockServiceLocatorBuilder {

  private Scanner scanner;
  private PrintStream printStream;
  private PostRepository postRepository;
  private UserRepository userRepository;
  private CreatePost createPost;

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

  public MockServiceLocatorBuilder createPost(CreatePost createPost) {
    this.createPost = createPost;
    return this;
  }

  public ServiceLocator build() {
    return new ServiceLocator() {
      @Override
      public Scanner getScanner() {
        return scanner != null? scanner : super.getScanner();
      }

      @Override
      public PrintStream getPrintStream() {
        return printStream != null? printStream : super.getPrintStream();
      }

      @Override
      public PostRepository getPostRepository() {
        return postRepository != null? postRepository : super.getPostRepository();
      }

      @Override
      public UserRepository getUserRepository() {
        return userRepository != null? userRepository : super.getUserRepository();
      }

      @Override
      public CreatePost getCreatePostUseCase() {
        return createPost != null? createPost : super.getCreatePostUseCase();
      }
    };
  }
}
