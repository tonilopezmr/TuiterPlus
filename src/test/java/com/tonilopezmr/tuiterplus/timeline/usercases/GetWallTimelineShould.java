package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.timeline.model.Post;
import com.tonilopezmr.tuiterplus.timeline.model.Timeline;
import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;
import com.tonilopezmr.tuiterplus.user.InMemoryUsers;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetWallTimelineShould {

  public static final String FAMOUS_TONI_POST = "Hello Codurance!";
  public static final String ALVARO_ANSWERS_POST =
      "@tonilopezmr please stop, you are very annoying with \"Hello Codurance!\"";
  public static final String TONI_ANSWERS_REPENTANT_ALVARO_POST =
      "@alvarobiz sorry, was a @srodrigoDev idea";
  public static final String RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING =
      "@tonilopezmr @alvarobiz  hey hey that's a lie, watch my last kotlin kata bowling video";

  private UserRepository getUsers(User toni, User alvaro, User rodrigo) {
    InMemoryUsers users = new InMemoryUsers();
    users.create(toni);
    users.create(alvaro);
    users.create(rodrigo);
    toniFollowAlvaroAndRodrigo(toni, alvaro, rodrigo, users);
    addPosts(toni, alvaro, rodrigo, users);
    return users;
  }

  private void toniFollowAlvaroAndRodrigo(User toni, User alvaro, User rodrigo, InMemoryUsers users) {
    users.follow(toni, alvaro);
    users.follow(toni, rodrigo);
  }

  private void addPosts(User toni, User alvaro, User rodrigo, InMemoryUsers users) {
    users.add(new Post(toni, FAMOUS_TONI_POST, LocalDateTime.now().minusHours(2)));
    users.add(new Post(alvaro, ALVARO_ANSWERS_POST, LocalDateTime.now().minusMinutes(32)));
    users.add(new Post(toni, TONI_ANSWERS_REPENTANT_ALVARO_POST, LocalDateTime.now().minusMinutes(29)));
    users.add(new Post(rodrigo, RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING, LocalDateTime.now().minusSeconds(1)));
  }

  private ReadWallTimeline getToniFollowsAlvaroAndRodrigoWall() {
    User toni = new User("Toni");
    User alvaro = new User("Alvaro");
    User rodrigo = new User("Rodrigo");
    UserRepository users = getUsers(toni, alvaro, rodrigo);
    return new ReadWallTimeline(users);
  }

  @Test
  public void
  get_wall_timeline_with_followers() {
    ReadWallTimeline readWallTimeline = getToniFollowsAlvaroAndRodrigoWall();

    Timeline timeline = readWallTimeline.getIt("Toni");

    assertTrue(!timeline.isEmpty());
    assertThat(timeline.get(0).getPost(), is(RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING));
    assertThat(timeline.get(1).getPost(), is(TONI_ANSWERS_REPENTANT_ALVARO_POST));
    assertThat(timeline.get(2).getPost(), is(ALVARO_ANSWERS_POST));
    assertThat(timeline.get(3).getPost(), is(FAMOUS_TONI_POST));
  }


}