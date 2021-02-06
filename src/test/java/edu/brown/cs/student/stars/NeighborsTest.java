package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.KDTree;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static edu.brown.cs.student.stars.Neighbors.neighborsWrapper;
import static edu.brown.cs.student.stars.Neighbors.resetCurrentFringe;
import static edu.brown.cs.student.stars.Neighbors.setCurrentNearest;
import static edu.brown.cs.student.stars.Star.naiveNeighbors;
import static org.junit.Assert.assertTrue;

public class NeighborsTest {

  /**
   ** Tests the KDTree implementation against the naive implementation.
   */
//  @Test
//  public void testAgainstNaive() {
//   for (int i = 0; i < 10; i++) {
//    StarInput input = new StarInput();
//      Star.setStarData(input.getStars());
//      Star.setStarTree();
//      resetCurrentFringe();
//      setCurrentNearest(new TreeSet<>(Comparator.comparingDouble(
//          s -> s.distance(input.getX(), input.getY(), input.getZ()))));
//      String[] naive = naiveNeighbors(input.getCount(), input.getX(), input.getY(), input.getZ());
//      Set<Double> naiveStars =
//          Arrays.stream(naive).map(s -> input.getStars().get(Integer.parseInt(s))
//              .distance(input.getX(), input.getY(), input.getZ()))
//              .collect(Collectors.toSet());
//      String[] smart = neighborsWrapper(Star.getStarTree(), input.getCount(),
//          input.getX(), input.getY(), input.getZ(), 0, Optional.empty());
//      Set<Double> smartStars =
//          Arrays.stream(smart).map(s -> input.getStars().get(Integer.parseInt(s))
//              .distance(input.getX(), input.getY(), input.getZ()))
//              .collect(Collectors.toSet());
//      assertTrue(naiveStars.equals(smartStars));
//    }
//  }
}
