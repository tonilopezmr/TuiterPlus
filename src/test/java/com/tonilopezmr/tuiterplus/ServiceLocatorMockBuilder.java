package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.post.PostRepository;

import java.io.PrintStream;
import java.util.Scanner;

public class ServiceLocatorMockBuilder {

  private Scanner scanner;
  private PrintStream printStream;
  private PostRepository postRepository;

  public ServiceLocatorMockBuilder scanner(Scanner scanner) {
    this.scanner = scanner;
    return this;
  }

  public ServiceLocatorMockBuilder postRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
    return this;
  }

  public ServiceLocatorMockBuilder printStream(PrintStream printStream) {
    this.printStream = printStream;
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
    };
  }
}
