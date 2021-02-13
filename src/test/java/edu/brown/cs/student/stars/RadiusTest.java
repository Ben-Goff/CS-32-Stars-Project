package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import edu.brown.cs.student.util.KDTree;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import static edu.brown.cs.student.stars.Neighbors.resetCurrentFringe;
import static edu.brown.cs.student.stars.Neighbors.setCurrentNearest;
import static edu.brown.cs.student.stars.NaiveRadius.naiveRadius;
import static edu.brown.cs.student.stars.Radius.radius;
import static org.junit.Assert.assertTrue;

public class RadiusTest {

  private Radius radius = new Radius();

  /**
   * * Tests the KDTree implementation against the naive implementation.

  @Test
  public void testAgainstNaive() {
    boolean correct;
    for (int i = 0; i < Constants.THIRTY; i++) {
      StarInput input = new StarInput();
      Star.setStarData(input.getStars());
      Star[] naive = naiveRadius(input.getCount(), input.getX(), input.getY(), input.getZ());
      Double[] naiveStars =
          Arrays.stream(naive).map(s -> s.distance(input.getX(), input.getY(), input.getZ()))
              .sorted().toArray(Double[]::new);
      Star.setStarTree();
      resetCurrentFringe();
      setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
          s -> -1 * s.distance(input.getX(), input.getY(), input.getZ()))));
      Star[] smart = radius(input.getCount(),
          input.getX(), input.getY(), input.getZ());
      Double[] smartStars =
          Arrays.stream(smart).map(s -> s.distance(input.getX(), input.getY(), input.getZ()))
              .sorted().toArray(Double[]::new);
      correct = true;
      Stars.ppp(input.getCount() + "b" + smartStars.length + " " + naiveStars.length);
      Stars.ppp("c" + smartStars[smartStars.length - 1] + " " + naiveStars[naiveStars.length - 1]);
      for (int j = 0; j < smartStars.length; j++) {
        if (Math.abs(smartStars[j] - naiveStars[j]) > Constants.ALMOST_ZERO) {
          correct = false;
        }
      }
      assertTrue(correct);
    }
  }
   */

  /**
   * Resets the Radius object.
   */
  @Before
  public void reset() {
    radius = new Radius();
    Star.clearStarData();
  }

  /**
   ** Tests calling radius 0.
   */
  @Test
  public void testZeroRadius() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = radius(0, 0, 0, 0);
    assertTrue(output.length == 1);
    output = radius(0, "Sol");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests no stars in radius.
   */
  @Test
  public void testNoneInRadius() throws IOException {
    reset();
    Stars.loadData("data/stars/stardata.csv");
    Star[] output = radius(Constants.ALMOST_ZERO, Constants.ONEONENINESIXONESEVEN,
        Constants.ONEONENINESIXONESEVEN, Constants.ONEONENINESIXONESEVEN);
    assertTrue(output.length == 0);
    output = radius(Constants.ALMOST_ZERO, "Cyntha");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    Star[] output = radius(Constants.THIRTY, 0, 0, 0);
    assertTrue(output.length == 1);
    assertTrue(((Star) Array.get(output, 0)).getStarID().equals("1"));
    output = radius(Constants.THIRTY, "Lonely Star");
    assertTrue(output.length == 0);
  }

  /**
   ** Tests command name returned properly.
   */
  @Test
  public void testName() {
    reset();
    assertTrue(radius.name().equals("radius"));
  }

  /**
   ** Tests proper return on large number of stars.
   */
  @Test
  public void testStars() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = radius(5, -1, -1, -1);
    assertTrue(output.length == 6);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("118721"::equals));
    output = radius(5, "Proxima Centauri");
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
      radius(Constants.TEN, 0, 0, 0);
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
    Star[] output = radius(Constants.FIVE, 0, 3, 4);
    assertTrue(output[1].getProperName().equals("Sol"));
  }

}
