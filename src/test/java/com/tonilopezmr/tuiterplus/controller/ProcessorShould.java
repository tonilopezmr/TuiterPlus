package com.tonilopezmr.tuiterplus.controller;

import com.tonilopezmr.tuiterplus.model.Post;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProcessorShould {

  @Test
  public void
  return_posts_when_the_command_is_a_user(){
    Processor processor = new Processor();
    List<Post> posts = processor.process("Toni");

    assertTrue(posts.size() > 0);
    assertThat(posts.get(0).getPost(), is("Hello Codurance!"));
  }

}