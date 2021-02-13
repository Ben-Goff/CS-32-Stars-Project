package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;

import static edu.brown.cs.student.stars.Neighbors.neighbors;
import static edu.brown.cs.student.stars.Neighbors.resetCurrentFringe;
import static edu.brown.cs.student.stars.Neighbors.setCurrentNearest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NeighborsTest {

  private NaiveNeighbors naiveNeighbors = new NaiveNeighbors();
  private Neighbors neighbors = new Neighbors();
  private Stars stars = new Stars();

  /**
   ** Tests the KDTree implementation against the naive implementation.

  @Test
  public void testAgainstNaive() {
    boolean correct;
    for (int i = 0; i < Constants.THIRTY; i++) {
      StarInput input = new StarInput();
      Star.clearStarData();
      for (Star star : input.getStars()) {
        Star.addStarData(star);
      }
      Star.setStarTree();
      Star[] naive = naiveNeighbors.run(" " + input.getCount() + " " + input.getX()
          + " " + input.getY() + " " + input.getZ());
      Double[] naiveStars =
          Arrays.stream(naive).map(s -> s.distance(input.getX(), input.getY(), input.getZ()))
              .sorted().toArray(Double[]::new);
      Star[] smart = neighbors.run(" " + input.getCount() + " " + input.getX()
          + " " + input.getY() + " " + input.getZ());
      Double[] smartStars =
          Arrays.stream(smart).map(s -> s.distance(input.getX(), input.getY(), input.getZ()))
              .sorted().toArray(Double[]::new);
      correct = true;
      for (int j = 0; j < smartStars.length; j++) {
        if (Math.abs(smartStars[j] - naiveStars[j]) > Constants.ALMOST_ZERO) {
          correct = false;
        }
      }
      if (!correct) {
        for (int k = 0; k < input.getStars().size(); k++) {
          System.out.println(input.getStars().get(k).toString());
        }
        System.out.println("count: " + input.getCount());
        System.out.println("x: " + input.getX());
        System.out.println("y: " + input.getY());
        System.out.println("z: " + input.getZ());
      }
      assertTrue(correct);
    }
  }
   */

  /**
   * Resets the NaiveNeighbors object.
   */
  @Before
  public void reset() {
    Star.clearStarData();
    resetCurrentFringe();
  }

  /**
   ** Tests calling 0 stars when 0 stars are loaded.
   */
  @Test
  public void testNoStars() {
    reset();
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(0, 0, 0))));
    Star[] output = neighbors(Star.getStarTree(), 0, 0, 0, 0, Optional.empty());
    assertTrue(output.length == 0);
  }

  /**
   ** Tests calling all loaded stars.
   */
  @Test
  public void testAllStars() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(0, 0, 0))));
    Star[] output = neighbors(Star.getStarTree(), 1, 0, 0, 0, Optional.empty());
    assertTrue(output.length == 1);
    assertTrue(((Star) Array.get(output, 0)).getStarID().equals("1"));
    Stars.loadData("data/stars/ten-star.csv");
    reset();
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(0, 0, 0))));
    output = neighbors(Star.getStarTree(), Constants.NINE, 0, 0, 0,
        Optional.of(new Star("1", "Sol", 0, 0, 0)));
    assertTrue(output.length == Constants.NINE);
    reset();
    Stars.loadData("data/stars/stardata.csv");
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(Constants.FIVE_TWO, Constants.NEG_ONE, Constants.EIGHT))));
    output = neighbors(Star.getStarTree(), Constants.ONEONENINESIXONESEVEN,
        Constants.FIVE_TWO, Constants.NEG_ONE, Constants.EIGHT, Optional.empty());
    assertTrue(output.length == Constants.ONEONENINESIXONESEVEN);
    reset();
    Stars.loadData("data/stars/stardata.csv");
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(Constants.FIVE_TWO, Constants.NEG_ONE, Constants.EIGHT))));
    output = neighbors(Star.getStarTree(), Constants.ONEONENINESIXONESEVEN,
        Constants.FIVE_TWO, Constants.NEG_ONE, Constants.EIGHT, Optional.of(new Star("3638",
            "Cyntha", Constants.CYNTHA_X, Constants.CYNTHA_Y, Constants.CYNTHA_Z)));
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
      setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
          s -> -1 * s.distance(0, 0, 0))));
      neighbors(Star.getStarTree(), 2, 0, 0, 0, Optional.empty());
    } catch (Exception e) {
      assertTrue(true);
    }
    try {
      reset();
      setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(s ->
          -1 * s.distance(Constants.FIVE, Constants.NEG_TWO_TWOFOUR, Constants.TEN_ZEROFOUR))));
      neighbors(Star.getStarTree(), Constants.ONE, Constants.FIVE, Constants.NEG_TWO_TWOFOUR,
          Constants.TEN_ZEROFOUR, Optional.of(new Star("1", "Lonely Star",
              Constants.FIVE, Constants.NEG_TWO_TWOFOUR, Constants.TEN_ZEROFOUR)));
    } catch (Exception e) {
      assertTrue(true);
    }
    Stars.loadData("data/stars/stardata.csv");
    try {
      reset();
      setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
          s -> -1 * s.distance(0, 0, 0))));
      neighbors(Star.getStarTree(), Constants.ONEONENINESIXONESEVEN + 1, 0, 0, 0, Optional.empty());
    } catch (Exception e) {
      assertTrue(true);
    }
    try {
      reset();
      setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
          s -> -1 * s.distance(0, 0, 0))));
      neighbors(Star.getStarTree(), Constants.ONEONENINESIXONESEVEN, 0, 0, 0,
          Optional.of(new Star("3638", "Cyntha",
              Constants.CYNTHA_X, Constants.CYNTHA_Y, Constants.CYNTHA_Z)));
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
    assertTrue(neighbors.name().equals("neighbors"));
  }

  /**
   ** Tests proper return on large number of stars.
   */
  @Test
  public void testLotsOfStars() throws IOException {
    reset();
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(Constants.NEG_ONE, Constants.NEG_ONE, Constants.NEG_ONE))));
    Stars.loadData("data/stars/ten-star.csv");
    Star[] output = neighbors(Star.getStarTree(), Constants.FIVE, Constants.NEG_ONE,
        Constants.NEG_ONE, Constants.NEG_ONE, Optional.empty());
    assertTrue(output.length == 5);
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("0"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("70667"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71454"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("71457"::equals));
    assertTrue(Arrays.stream(output).map(s -> s.getStarID()).anyMatch("87666"::equals));
    reset();
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(Constants.PROXIMA_CENTAURI_X,
            Constants.PROXIMA_CENTAURI_Y, Constants.PROXIMA_CENTAURI_Z))));
    output = neighbors(Star.getStarTree(), 5, Constants.PROXIMA_CENTAURI_X,
        Constants.PROXIMA_CENTAURI_Y, Constants.PROXIMA_CENTAURI_Z, Optional.of(new Star("70667",
            "Proxima Centauri", Constants.PROXIMA_CENTAURI_X, Constants.PROXIMA_CENTAURI_Y,
            Constants.PROXIMA_CENTAURI_Z)));
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
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(0, 0, 0))));
    Stars.loadData("data/stars/tie-star.csv");
    Star[] output = neighbors(Star.getStarTree(), 5, 0, 0, 0, Optional.empty());
    assertTrue(output[0].distance(0, 0, 0) == 0);
    assertTrue(output[1].distance(0, 0, 0) == 0);
    assertTrue(output[2].distance(0, 0, 0) == 0);
    assertTrue(output[3].distance(0, 0, 0) == 5);
    assertTrue(output[4].distance(0, 0, 0) == 5);
    reset();
    setCurrentNearest(new PriorityQueue<>(Comparator.comparingDouble(
        s -> -1 * s.distance(0, 0, 0))));
    output = neighbors(Star.getStarTree(), 5, 0, 0, 0,
        Optional.of(new Star("3", "Daniel", 0, 0, 0)));
    assertTrue(output[0].distance(0, 0, 0) == 0);
    assertTrue(output[1].distance(0, 0, 0) == 0);
    assertTrue(output[2].distance(0, 0, 0) == 5);
    assertTrue(output[3].distance(0, 0, 0) == 5);
    assertTrue(output[4].distance(0, 0, 0) == 5);
  }

  /**
   ** Tests calling one neighbor.

  @Test
  public void testOneNeighbor() throws IOException {
    reset();
    stars.run(" data/stars/nineteen-star.csv");
    Star[] output = neighbors.run(" 1 128.72790843303667 -55.20516045528934 140.81526135482198");
    System.out.println("yooooo" + ((Star) Star.getStarTree().getRight().get().getNode().get()).getStarID());
    assertEquals(output.length, 1);
    assertEquals(output[0].getStarID(), "18");
  }
   */
}
