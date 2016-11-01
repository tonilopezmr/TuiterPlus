package com.tonilopezmr.tuiterplus.usercases;

import com.tonilopezmr.tuiterplus.model.user.User;
import com.tonilopezmr.tuiterplus.model.user.UserRepository;
import com.tonilopezmr.tuiterplus.repository.InMemoryUsers;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class UnfollowUserShould {

  public UserRepository getToniFollowRodrigo() {
    UserRepository users = new InMemoryUsers();
    User toni = new User("Toni");
    User rodrigo = new User("Rodrigo");
    users.create(toni);
    users.create(rodrigo);
    users.follow(toni, rodrigo);
    return users;
  }

  @Test
  public void
  user_unfollow_another_user(){
    UserRepository userRepository = getToniFollowRodrigo();
    UnfollowUser unfollowUser = new UnfollowUser(userRepository);

    unfollowUser.doIt("Toni", "Rodrigo");

    User user = userRepository.get("Toni").get();
    assertTrue(user.getFollowing().isEmpty());
  }

}