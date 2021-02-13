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
import static edu.brown.cs.student.stars.Neighbors.resetCurrentFringe;
import static edu.brown.cs.student.stars.Neighbors.setCurrentNearest;
import static edu.brown.cs.student.stars.NaiveNeighbors.naiveNeighbors;
import static edu.brown.cs.student.stars.NaiveRadius.naiveRadius;
import static edu.brown.cs.student.stars.Radius.radius;
import static org.junit.Assert.assertTrue;

public class RadiusTest {

  /**
   ** Tests the KDTree implementation against the naive implementation.
   */
//  @Test
//  public void testAgainstNaive() {
//    for (int i = 0; i < 10; i++) {
//      StarInput input = new StarInput();
//      Star.setStarData(input.getStars());
//      Star.setStarTree();
//      resetCurrentFringe();
//      setCurrentNearest(new TreeSet<>(Comparator.comparingDouble(
//          s -> s.distance(input.getX(), input.getY(), input.getZ()))));
//      String[] naive = naiveRadius(input.getCount(), input.getX(), input.getY(), input.getZ());
//      Set<Double> naiveStars =
//          Arrays.stream(naive).map(s -> input.getStars().get(Integer.parseInt(s))
//              .distance(input.getX(), input.getY(), input.getZ()))
//              .collect(Collectors.toSet());
//      Set<Double> smart = radius(Optional.of(Star.getStarTree()), input.getCount(),
//          input.getX(), input.getY(), input.getZ(), 0).stream()
//          .map(s -> input.getStars().get(Integer.parseInt(s.getStarID()))
//          .distance(input.getX(), input.getY(), input.getZ()))
//          .collect(Collectors.toSet());
//      assertTrue(naiveStars.equals(smart));
//    }
//  }
}