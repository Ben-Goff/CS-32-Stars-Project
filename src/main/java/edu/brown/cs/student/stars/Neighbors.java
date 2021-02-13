package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.KDTree;
import edu.brown.cs.student.util.REPLCommand;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

public class Neighbors implements REPLCommand {

  private static PriorityQueue<Star> currentNearest = new PriorityQueue<>();
  private static ArrayList<Star> currentFringe;
  private static KDTree currentData;

  /**
   * Creates an empty Neighbors object.
   */
  public Neighbors() {
  }

  public static void setCurrentNearest(PriorityQueue<Star> cn) {
    currentNearest = cn;
  }

  public static void resetCurrentFringe() {
    currentFringe = new ArrayList<>();
  }

  /**
   * Returns the name of the Radius command "radius".
   *
   * @return String "neighbors"
   */
  @Override
  public String name() {
    return "neighbors";
  }

  /**
   * Runs the Neighbors command with the inputted string containing commands.
   *
   * @param argumentString
   * @return
   */
  @Override
  public Star[] run(String argumentString) {
    currentFringe = new ArrayList<>();
    currentData = Star.getStarTree();
    try {
      boolean twoParam;
      boolean fourParam;
      int count;
      //Checks that the command is valid and what form it takes (coordinates/star name)
      twoParam = Pattern.matches("(\\s*[A-z0-9]+\\s+\".+\")", argumentString);
      fourParam = Pattern.matches("(\\s*[A-z0-9]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+)",
          argumentString);
      if (twoParam) {
        int firstQuote = argumentString.indexOf("\"");
        int secondQuote = argumentString.lastIndexOf("\"");
        count = Integer.parseInt(argumentString.substring(0, firstQuote - 1).trim());
        String name = argumentString.substring(firstQuote + 1, secondQuote);
        Star target = Star.getStar(name);
        //Queue cleared and designated to sort by distance to target coordinate
        //Negative so that the farthest stars (those to be kicked off) are first
        currentNearest = new PriorityQueue<>(Comparator.comparingDouble(
            s -> -1 * s.distance(target.getX(), target.getY(), target.getZ())));
        currentNearest.clear();
        return neighbors(currentData, count, target.getX(), target.getY(), target.getZ(),
            Optional.of(target));
      } else {
        if (fourParam) {
          String[] arguments = argumentString.trim().split(" +");
          count = Integer.parseInt(Array.get(arguments, 0).toString());
          double x = Double.parseDouble(Array.get(arguments, 1).toString());
          double y = Double.parseDouble(Array.get(arguments, 2).toString());
          double z = Double.parseDouble(Array.get(arguments, 3).toString());
          //Queue cleared and designated to sort by distance to target coordinate
          //Negative so that the farthest stars (those to be kicked off) are first
          currentNearest = new PriorityQueue<>(Comparator
              .comparingDouble(s -> -1 * s.distance(x, y, z)));
          currentNearest.clear();
          return neighbors(currentData, count, x, y, z, Optional.empty());
        } else {
          // Throw error if argumentString doesn't match Neighbors command regex
          System.out.println("ERROR: malformed neighbors command");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new Star[0];
  }

  /**
   * Sets the local variables of close neighbors to the inputted coordinates.
   * Ties chosen randomly.
   *
   * @param data
   * @param k
   * @param ex
   * @param why
   * @param zee
   * @param target
   * @return Star[]
   */
  public static Star[] neighbors(KDTree data, int k, double ex, double why, double zee,
                                 Optional<Star> target) {
    if (k == 1 && Star.getStarData().size() == 1 && target.isPresent()) {
      return new Star[0];
    }
    if (k == Star.getStarData().size() && target.isPresent()) {
      return Star.getStarData().stream()
          .filter(s -> !s.getStarID().equals(target.get().getStarID())).toArray(Star[]::new);
    }
    int count;
    if (target.isPresent()) {
      count = k + 1;
    } else {
      count = k;
    }
    neighborSearcher(data, ex, why, zee, 0, count);
    if (target.isPresent()) {
      currentNearest.remove(target.get());
      currentFringe.remove(target.get());
    }
    int moveFromFringe = Math.min(k - currentNearest.size(), currentFringe.size());
    Collections.shuffle(currentFringe);
    for (int i = 0; i < moveFromFringe; i++) {
      currentNearest.add(currentFringe.get(i));
    }
    return currentNearest.stream().sorted(Comparator.comparingDouble(
        s -> s.distance(ex, why, zee))).toArray(Star[]::new);
  }

  private static void neighborSearcher(KDTree data, double ex, double why, double zee,
                                       int layer, int count) {
    int dimension = 3;
    int index = layer % dimension;
    if (data.getNode().isPresent() && count != 0) {
      Star current = (Star) data.getNode().get();
      if (currentNearest.size() + currentFringe.size() < count) {
        /*
        The fringe is always loaded first, as the nearest is limited to count - 1
        Fringe contains the stars of the farthest distance that may still contain a near neighbor
        is always maintained at size > 0, but acts as only one slot in the queue it is the real
        head of the priority queue, ties are decided randomly from within it after the fact
        */
        if (currentFringe.size() == 0
            || current.distance(ex, why, zee) == currentFringe.get(0).distance(ex, why, zee)) {
          currentFringe.add(current);
        } else {
          if (current.distance(ex, why, zee) > currentFringe.get(0).distance(ex, why, zee)) {
            for (Star star : currentFringe) {
              currentNearest.add(star);
            }
            //When something is kicked off of the fringe, a new item must take its place
            currentFringe.clear();
            currentFringe.add(current);
          } else {
            currentNearest.add(current);
          }
        }
      } else {
        if (current.distance(ex, why, zee) == currentFringe.get(0).distance(ex, why, zee)) {
          currentFringe.add(current);
        } else {
          if (current.distance(ex, why, zee) < currentFringe.get(0).distance(ex, why, zee)) {
            if (currentNearest.size() < count - 1) {
              currentNearest.add(current);
            } else {
              currentNearest.add(current);
              Star moveToFringe = currentNearest.poll();
              currentFringe.clear();
              currentFringe.add(moveToFringe);
            }
          }
        }
      }
      //switch branches are mostly identical
      //there is one for each dimension that may be split on, denoted by 0, 1, 2 for x, y, z
      switch (index) {
        case 0:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getX() - ex)
              || currentFringe.size() + currentNearest.size() < count) {
            //goes down both sides if we haven't hit the quota yet
            if (data.getLeft().isPresent()) {
              neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
            }
            if (data.getRight().isPresent()) {
              neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
            }
          } else {
            //if we have, it goes to the side closer to the current side
            if (current.getX() <= ex) {
              if (data.getRight().isPresent()) {
                neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
              }
            }
          }
          break;
        case 1:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getY() - why)
              || currentFringe.size() + currentNearest.size() < count) {
            if (data.getLeft().isPresent()) {
              neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
            }
            if (data.getRight().isPresent()) {
              neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
            }
          } else {
            if (current.getY() <= why) {
              if (data.getRight().isPresent()) {
                neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
              }
            }
          }
          break;
        case 2:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getZ() - zee)
              || currentFringe.size() + currentNearest.size() < count) {
            if (data.getLeft().isPresent()) {
              neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
            }
            if (data.getRight().isPresent()) {
              neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
            }
          } else {
            if (current.getZ() <= zee) {
              if (data.getRight().isPresent()) {
                neighborSearcher(data.getRight().get(), ex, why, zee, layer++, count);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighborSearcher(data.getLeft().get(), ex, why, zee, layer++, count);
              }
            }
          }
          break;
        default:
      }
    }
  }
}
