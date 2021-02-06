package edu.brown.cs.student.util;

import edu.brown.cs.student.stars.Star;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

  private KDTree building;
  /**
   * Resets the KDTree object.
   */
  @Before
  public void reset() {
    building = new KDTree(new Star("0", "root", 0, 0, 0));
  }

  /**
   * Tests inserting one value.
   */
  @Test
  public void singleInsert() {
    reset();
    Star star = new Star("1", "new", 1, -1, 2);
    building.insert(star);
    assertTrue(new ArrayList<>(Arrays.asList(1.0, -1.0, 2.0))
        .equals(building.getRight().get().getNode().get().getCoordinate()));
  }
}
