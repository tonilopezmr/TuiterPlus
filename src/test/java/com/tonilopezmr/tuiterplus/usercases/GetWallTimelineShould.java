package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.post.Post;
import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.repository.InMemoryPosts;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetWallTimelineShould {

  public static final String FAMOUS_TONI_POST = "Hello Codurance!";
  public static final String ALVARO_ANSWERS_POST = "@tonilopezmr please stop, you are very annoying with \"Hello Codurance!\"";
  public static final String TONI_ANSWERS_REPENTANT_ALVARO_POST = "@alvarobiz sorry, was a @srodrigoDev idea";
  public static final String RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING = "@tonilopezmr @alvarobiz  hey hey that's a lie, watch my last kotlin kata bowling video";

  private InMemoryUsers getUsers(User toni, User alvaro, User rodrigo) {
    InMemoryUsers users = new InMemoryUsers();
    users.create(toni);
    users.create(alvaro);
    users.create(rodrigo);
    users.follow(toni, alvaro);
    users.follow(toni, rodrigo);
    return users;
  }

  private InMemoryPosts getPosts(User toni, User alvaro, User rodrigo) {
    InMemoryPosts posts = new InMemoryPosts();
    posts.create(new Post(toni, FAMOUS_TONI_POST, LocalDateTime.now().minusHours(2)));
    posts.create(new Post(alvaro, ALVARO_ANSWERS_POST, LocalDateTime.now().minusMinutes(32)));
    posts.create(new Post(toni, TONI_ANSWERS_REPENTANT_ALVARO_POST, LocalDateTime.now().minusMinutes(29)));
    posts.create(new Post(rodrigo, RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING, LocalDateTime.now().minusSeconds(1)));
    return posts;
  }

  private GetWallTimeline getToniFollowsAlvaroAndRodrigoWall() {
    User toni = new User("Toni");
    User alvaro = new User("Alvaro");
    User rodrigo = new User("Rodrigo");
    InMemoryUsers users = getUsers(toni, alvaro, rodrigo);
    InMemoryPosts posts = getPosts(toni, alvaro, rodrigo);
    return new GetWallTimeline(users, posts);
  }

  @Test
  public void
  get_wall_timeline_with_friends() {
    GetWallTimeline getWallTimeline = getToniFollowsAlvaroAndRodrigoWall();

    List<Post> timeline = getWallTimeline.getIt("Toni");

    assertTrue(!timeline.isEmpty());
    assertThat(timeline.get(0).getPost(), is(RODRIGO_ANSWERS_TONI_AND_DO_A_BIT_OF_ADVERTISING));
    assertThat(timeline.get(1).getPost(), is(TONI_ANSWERS_REPENTANT_ALVARO_POST));
    assertThat(timeline.get(2).getPost(), is(ALVARO_ANSWERS_POST));
    assertThat(timeline.get(3).getPost(), is(FAMOUS_TONI_POST));
  }


}