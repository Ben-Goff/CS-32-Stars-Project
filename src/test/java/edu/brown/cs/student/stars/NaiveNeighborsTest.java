package edu.brown.cs.student.stars;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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
    String[] output = Star.naiveNeighbors(0, 0, 0, 0);
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    String[] output = Star.naiveNeighbors(1, 0, 0, 0);
    assertTrue(output.length == 1);
    assertTrue(Array.get(output, 0).equals("1"));
    output = Star.naiveNeighbors(0, "Lonely Star");
    assertTrue(output.length == 0);
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
  public void testStars() throws IOException {
    reset();
    Stars.loadData("data/stars/ten-star.csv");
    String[] output = Star.naiveNeighbors(5, -1, -1, -1);
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).anyMatch("87666"::equals));
    output = Star.naiveNeighbors(5, "Proxima Centauri");
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).anyMatch("118721"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).anyMatch("87666"::equals));
  }
}
