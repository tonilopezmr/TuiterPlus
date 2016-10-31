package com.tonilopezmr.tuiterplus.model.post;

import java.util.Iterator;
import java.util.TreeSet;

public class Timeline extends TreeSet<Post> {

  /**
   * To support any sorted structure
   *
   * @param index - index of the element to return
   * @return the post at the specified position in this list
   */
  public Post get(int index) {
    Iterator<Post> iterator = iterator();

    for (int i = 0, size = size(); i < size-1 && i < index; i++) {
      iterator.next();
    }

    return iterator.next();
  }

}
