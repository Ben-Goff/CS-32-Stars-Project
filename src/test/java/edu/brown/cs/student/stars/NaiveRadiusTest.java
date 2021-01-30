package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class NaiveRadiusTest {

  private NaiveRadius radius;

  /**
   * Resets the NaiveRadius object.
   */
  @Before
  public void reset() {
    radius = new NaiveRadius();
    Star.clearStarData();
  }

  /**
   ** Tests calling radius 0.
   */
  @Test
  public void testZeroRadius() {
    reset();
    String[] output = Star.naiveRadius(0, 0, 0, 0);
    assertTrue(output.length == 0);
    output = Star.naiveRadius(0, "S");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    String[] output = Star.naiveRadius(Constants.THIRTY, 0, 0, 0);
    assertTrue(output.length == 1);
    assertTrue(Array.get(output, 0).equals("1"));
    output = Star.naiveRadius(Constants.THIRTY, "Lonely Star");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests command name returned properly.
   */
  @Test
  public void testName() {
    reset();
    assertTrue(radius.name().equals("naive_radius"));
  }

  /**
   ** Tests proper return on large number of stars.
   */
  @Test
  public void testStars() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    String[] output = Star.naiveRadius(5, -1, -1, -1);
    assertTrue(output.length == 6);
    assertTrue(Arrays.stream(output).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).anyMatch("87666"::equals));
    assertTrue(Arrays.stream(output).anyMatch("118721"::equals));
    output = Star.naiveRadius(5, "Proxima Centauri");
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).anyMatch("118721"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).anyMatch("87666"::equals));
  }
}
