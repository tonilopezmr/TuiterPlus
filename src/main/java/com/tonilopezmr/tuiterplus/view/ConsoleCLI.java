package com.tonilopezmr.tuiterplus.view;

import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleCLI implements View {

  private Scanner scanner;
  private PrintStream output;

  public ConsoleCLI(Scanner scanner, PrintStream output) {
    this.scanner = scanner;
    this.output = output;
  }

  public void showPrompt() {
    output.print("> ");
  }

  public String readCommandLine() {
    return scanner.nextLine();
  }

  public void show(String message) {
    output.print(message);
  }

}
