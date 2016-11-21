package com.tonilopezmr.tuiterplus.timeline.usercases;

import com.tonilopezmr.tuiterplus.user.model.User;
import com.tonilopezmr.tuiterplus.user.model.UserRepository;
import com.tonilopezmr.tuiterplus.user.InMemoryUsers;
import com.tonilopezmr.tuiterplus.user.usercases.FollowUser;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FollowUserShould {

  private UserRepository getToniRodrigoUsers() {
    UserRepository users = new InMemoryUsers();
    User toni = new User("Toni");
    User rodrigo = new User("Rodrigo");
    users.create(toni);
    users.create(rodrigo);
    return users;
  }

  @Test
  public void
  user_follow_another_user() {
    UserRepository users = getToniRodrigoUsers();
    FollowUser followUser = new FollowUser(users);

    followUser.doIt("Toni", "Rodrigo");

    User user = users.get("Toni").get();
    List<User> following = user.getFollowing();
    assertTrue(!following.isEmpty());
    assertThat(following.get(0).getName(), is("Rodrigo"));
  }

}