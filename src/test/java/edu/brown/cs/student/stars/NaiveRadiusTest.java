package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static edu.brown.cs.student.stars.NaiveRadius.naiveRadius;
import static org.junit.Assert.assertTrue;

public class NaiveRadiusTest {

  private NaiveRadius radius = new NaiveRadius();

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
  public void testZeroRadius() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = naiveRadius(0, 0, 0, 0);
    assertTrue(output.length == 1);
    output = naiveRadius(0, "Sol");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests no stars in radius.
   */
  @Test
  public void testNoneInRadius() throws IOException {
    reset();
    Stars.loadData("data/stars/stardata.csv");
    Star[] output = naiveRadius(Constants.ALMOST_ZERO, Constants.ONEONENINESIXONESEVEN,
        Constants.ONEONENINESIXONESEVEN, Constants.ONEONENINESIXONESEVEN);
    assertTrue(output.length == 0);
    output = naiveRadius(Constants.ALMOST_ZERO, "Cyntha");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    Star[] output = naiveRadius(Constants.THIRTY, 0, 0, 0);
    assertTrue(output.length == 1);
    assertTrue(((Star) Array.get(output, 0)).getStarID().equals("1"));
    output = naiveRadius(Constants.THIRTY, "Lonely Star");
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
    Star[] output = naiveRadius(5, -1, -1, -1);
    assertTrue(output.length == 6);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("118721"::equals));
    output = naiveRadius(5, "Proxima Centauri");
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("118721"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
  }

  /**
   ** Tests calling more stars than number of loaded stars.
   */
  @Test
  public void testOverloading() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    try {
      naiveRadius(Constants.TEN, 0, 0, 0);
    } catch (Exception e) {
      assertTrue(true);
    }
  }

  /**
   ** Tests star on edge of radius to make sure it is included.
   */
  @Test
  public void testFringeStar() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = naiveRadius(Constants.FIVE, 0, 3, 4);
    assertTrue(output[1].getProperName().equals("Sol"));
  }
}

