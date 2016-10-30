package com.tonilopezmr.tuiterplus;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.repository.MockPostRepository;
import com.tonilopezmr.tuiterplus.repository.MockUserRepository;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TuiterPlusShould {

  private ByteArrayOutputStream out = new ByteArrayOutputStream();

  private Scanner willTypeLine(String line) {
    return new Scanner(line);
  }

  /**
   * Return a output of the "turn" in console
   * <p>
   * for example console:
   * > Charlie             index 0
   * Hey! (1 minute ago)   index 1
   * > exit                index 3
   * exit                  index 4
   *
   * @param index When the expected output index should be
   * @return the expected output
   */
  private String getOutput(int index) {
    Scanner scanner = new Scanner(out.toString().replace("> ", "\n"));
    String result = null;
    for (int i = 0; i <= index && scanner.hasNextLine(); i++) {
      result = scanner.nextLine();
    }
    return result;
  }

  private Post getToniPostTwoMinutesAgo() {
    User toni = new User("Toni");
    return new Post(toni, "Hello Codurance!", LocalDateTime.now().minusMinutes(2));
  }

  private PrintStream recordOutPut() {
    return new PrintStream(out);
  }

  @Test
  public void
  exit_when_command_is_exit() {
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine("exit"))              //simulate input actions
        .printStream(recordOutPut())
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput(1);
    assertThat(output, is("exit"));
  }

  @Test
  public void
  show_posts_when_read_user_timeline() {
    Post post = getToniPostTwoMinutesAgo();

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine("Toni\nexit"))              //simulate input actions
        .printStream(recordOutPut())
        .postRepository(new MockPostRepository(Arrays.asList(post)))     //mock output
        .build();

    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();
    tuiterPlus.run();

    String output = getOutput(1);
    assertThat(output, is("Hello Codurance! (2 minutes ago)"));
  }

  @Test
  public void
  not_show_anything_when_create_a_post() {
    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine("Toni -> Hello Codurance!\nexit"))
        .printStream(recordOutPut())
        .build();
    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();

    tuiterPlus.run();

    String output = getOutput(1); //1 is the index of response
    assertThat(output, is(""));
  }

  @Test
  public void
  show_post_when_get_wall_timeline() {
    Post post = getToniPostTwoMinutesAgo();
    Post post2 = new Post(post.getUser(), "Hey Alvaro have a look my repo", LocalDateTime.now().minusSeconds(1));

    ServiceLocator serviceLocator = new MockServiceLocatorBuilder()
        .scanner(willTypeLine("Toni wall\nexit"))
        .printStream(recordOutPut())
        .postRepository(new MockPostRepository(Arrays.asList(post, post2)))     //mock output
        .userRepository(new MockUserRepository(new User("Toni")))
        .build();
    TuiterPlus tuiterPlus = serviceLocator.getTuiterPlus();

    tuiterPlus.run();

    String output = getOutput(1);
    assertThat(output, is("Hey Alvaro have a look my repo (a moment ago)"));
  }

}