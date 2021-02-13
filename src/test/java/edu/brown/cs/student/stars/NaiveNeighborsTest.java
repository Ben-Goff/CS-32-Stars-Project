package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static edu.brown.cs.student.stars.NaiveNeighbors.naiveNeighbors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NaiveNeighborsTest {

  private NaiveNeighbors neighbors;

  /**
   * Resets the NaiveNeighbors object.
   */
  @Before
  public void reset() {
    neighbors = new NaiveNeighbors();
    Star.clearStarData();
  }

  /**
   ** Tests calling 0 stars when 0 stars are loaded.
   */
  @Test
  public void testNoStars() {
    reset();
    Star[] output = naiveNeighbors(0, 0, 0, 0);
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    Star[] output = naiveNeighbors(1, 0, 0, 0);
    assertTrue(output.length == 1);
    assertTrue(((Star) Array.get(output, 0)).getStarID().equals("1"));
    output = naiveNeighbors(1, "Sol");
    assertTrue(output.length == 0);
    Stars.loadData("data/stars/stardata.csv");
    output = naiveNeighbors(Constants.ONEONENINESIXONESEVEN,
        Constants.FIVE_TWO, Constants.NEG_ONE, Constants.EIGHT);
    assertTrue(output.length == Constants.ONEONENINESIXONESEVEN);
    output = naiveNeighbors(Constants.ONEONENINESIXONESEVEN - 1, "Cyntha");
    assertEquals(output.length, Constants.ONEONENINESIXONESEVEN - 1);
  }

  /**
   ** Tests calling more than the number of loaded stars.
   */
  @Test
  public void testOverloadStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    try {
      naiveNeighbors(2, 0, 0, 0);
    } catch (Exception e) {
      assertTrue(true);
    }
    try {
      naiveNeighbors(1, "Lonely Star");
    } catch (Exception e) {
      assertTrue(true);
    }
    Stars.loadData("data/stars/stardata.csv");
    try {
      naiveNeighbors(Constants.ONEONENINESIXONESEVEN + 1, 0, 0, 0);
    } catch (Exception e) {
      assertTrue(true);
    }
    try {
      naiveNeighbors(Constants.ONEONENINESIXONESEVEN, "Cyntha");
    } catch (Exception e) {
      assertTrue(true);
    }
  }

  /**
   ** Tests command name returned properly.
   */
  @Test
  public void testName() {
    reset();
    assertTrue(neighbors.name().equals("naive_neighbors"));
  }

  /**
   ** Tests proper return on large number of stars.
   */
  @Test
  public void testLotsOfStars() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = naiveNeighbors(5, -1, -1, -1);
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
    output = naiveNeighbors(5, "Proxima Centauri");
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("118721"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
  }

  /**
   ** Tests stars that tie for distance.
   */
  @Test
  public void testTieStars() throws IOException {
    reset();
    Stars.loadData("data/stars/tie-star.csv");
    Star[] output = naiveNeighbors(5, 0, 0, 0);
    assertTrue(output[0].distance(0, 0, 0) == 0);
    assertTrue(output[1].distance(0, 0, 0) == 0);
    assertTrue(output[2].distance(0, 0, 0) == 0);
    assertTrue(output[3].distance(0, 0, 0) == 5);
    assertTrue(output[4].distance(0, 0, 0) == 5);
    output = naiveNeighbors(5, "Daniel");
    assertTrue(output[0].distance(0, 0, 0) == 0);
    assertTrue(output[1].distance(0, 0, 0) == 0);
    assertTrue(output[2].distance(0, 0, 0) == 5);
    assertTrue(output[3].distance(0, 0, 0) == 5);
    assertTrue(output[4].distance(0, 0, 0) == 5);
  }

  /**
   ** Tests calling one neighbor.
   */
  @Test
  public void testOneNeighbor() throws IOException {
    reset();
    Stars.loadData("data/stars/nineteen-star.csv");
    Star[] output = naiveNeighbors(1, 128.72790843303667, -55.20516045528934, 140.81526135482198);
    assertEquals(output.length, 1);
    assertEquals(output[0].getStarID(), "18");
  }
}
