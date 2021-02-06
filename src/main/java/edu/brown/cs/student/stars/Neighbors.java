package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.KDTree;
import edu.brown.cs.student.util.REPLCommand;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Neighbors implements REPLCommand {

  private static TreeSet<Star> currentNearest = new TreeSet<>();
  private static ArrayList<Star> currentFringe;
  private static KDTree currentData;

  /**
   * Creates an empty Neighbors object.
   */
  public Neighbors() {
  }

  /**
   * Returns the name of the Radius command "radius".
   * @return String "neighbors"
   */
  @Override
  public String name() {
    return "neighbors";
  }

  /**
   * Runs the Neighbors command with the inputted string containing commands.
   * @param argumentString
   */
  @Override
  public void run(String argumentString) {
    currentFringe = new ArrayList<>();
    currentData = Star.getStarTree();
    try {
      String[] closeStars;
      boolean twoParam;
      boolean fourParam;
      int count;
      twoParam = Pattern.matches("(\\s+[A-z0-9-.]+\\s+\"[A-z0-9-.\\s]+\")", argumentString);
      fourParam = Pattern.matches("(\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+)",
          argumentString);
      if (twoParam) {
        int firstQuote = argumentString.indexOf("\"");
        int secondQuote = argumentString.lastIndexOf("\"");
        count = Integer.parseInt(argumentString.substring(0, firstQuote - 1).trim());
        // Throw error if searching for more Stars than are loaded
        if (count > Star.getStarData().size()) {
          throw new RuntimeException("ERROR: " + count
              + " is more than the loaded number of stars (" + Star.getStarData().size() + ")");
        }
        String name = argumentString.substring(firstQuote + 1, secondQuote);
        Star target = Star.getStar(name);
        //TreeSet cleared and designated to sort by distance to target coordinate
        currentNearest = new TreeSet<>(Comparator.comparingDouble(
            s -> s.distance(target.getX(), target.getY(), target.getZ())));
        currentNearest.clear();
        //Calls with count + 1 and then removes target after; allowing for reuse of code
        neighbors(currentData, count + 1, target.getX(), target.getY(), target.getZ(), 0);
        currentNearest.remove(target);
        currentFringe.remove(target);
        //Combines fringe of farthest, tied values with other near stars to create final list
        int moveFromFringe = count - currentNearest.size();
        Collections.shuffle(currentFringe);
        for (int i = 0; i < moveFromFringe; i++) {
          if (!currentFringe.get(i).equals(target)) {
            currentNearest.add(currentFringe.get(i));
          }
        }
        closeStars = currentNearest.stream().map(s -> s.getStarID()).toArray(String[]::new);
        for (int i = 0; i < closeStars.length; i++) {
          System.out.println(Array.get(closeStars, i));
        }
      } else {
        if (fourParam) {
          String[] arguments = argumentString.trim().split(" +");
          count = Integer.parseInt(Array.get(arguments, 0).toString());
          // Throw error if searching for more Stars than are loaded
          if (count > Star.getStarData().size()) {
            throw new RuntimeException("ERROR: " + count
                + " is more than the loaded number of stars (" + Star.getStarData().size() + ")");
          }
          double x = Double.parseDouble(Array.get(arguments, 1).toString());
          double y = Double.parseDouble(Array.get(arguments, 2).toString());
          double z = Double.parseDouble(Array.get(arguments, 3).toString());
          //TreeSet cleared and designated to sort by distance to target coordinate
          currentNearest = new TreeSet<>(Comparator.comparingDouble(s -> s.distance(x, y, z)));
          neighbors(currentData, count, x, y, z, 0);
          //Combines fringe of farthest, tied values with other near stars to create final list
          int moveFromFringe = count - currentNearest.size();
          Collections.shuffle(currentFringe);
          for (int i = 0; i < moveFromFringe; i++) {
            currentNearest.add(currentFringe.get(i));
          }
          closeStars = currentNearest.stream().map(s -> s.getStarID()).toArray(String[]::new);
          for (int i = 0; i < closeStars.length; i++) {
            System.out.println(Array.get(closeStars, i));
          }
        } else {
          // Throw error if argumentString doesn't match Neighbors command regex
          System.out.println("ERROR: malformed neighbors command");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Returns the iDs of the closest k stars to the input coordinate.
   * Ties chosen randomly.
   * @param data
   * @param k
   * @param ex
   * @param why
   * @param zee
   * @param l
   */
  public static void neighbors(KDTree data, int k, double ex, double why, double zee, int l) {
    int layer = l;
    int dimension = 3;
    int index = layer % dimension;
    if (data.getNode().isPresent() && k != 0) {
      Star current = (Star) data.getNode().get();
      if (currentNearest.size() + currentFringe.size() < k) {
        if (currentFringe.size() == 0
            || current.distance(ex, why, zee) == currentFringe.get(0).distance(ex, why, zee)) {
          currentFringe.add(current);
        } else {
          if (current.distance(ex, why, zee) > currentFringe.get(0).distance(ex, why, zee)) {
            for (Star star : currentFringe) {
              currentNearest.add(star);
            }
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
            if (currentNearest.size() < k - 1) {
              currentNearest.add(current);
            } else {
              currentNearest.add(current);
              Star moveToFringe = currentNearest.pollLast();
              currentFringe.clear();
              currentFringe.add(moveToFringe);
            }
          }
        }
      }
      switch (index) {
        case 0:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getX() - ex)
              || currentFringe.size() + currentNearest.size() < k) {
            if (data.getLeft().isPresent()) {
              neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
            }
            if (data.getRight().isPresent()) {
              neighbors(data.getRight().get(), k, ex, why, zee, layer++);
            }
          } else {
            if (current.getX() <= ex) {
              if (data.getRight().isPresent()) {
                neighbors(data.getRight().get(), k, ex, why, zee, layer++);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
              }
            }
          }
          break;
        case 1:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getY() - why)
              || currentFringe.size() + currentNearest.size() < k) {
            if (data.getLeft().isPresent()) {
              neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
            }
            if (data.getRight().isPresent()) {
              neighbors(data.getRight().get(), k, ex, why, zee, layer++);
            }
          } else {
            if (current.getY() <= why) {
              if (data.getRight().isPresent()) {
                neighbors(data.getRight().get(), k, ex, why, zee, layer++);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
              }
            }
          }
          break;
        case 2:
          if (currentFringe.get(0).distance(ex, why, zee) > Math.abs(current.getZ() - zee)
              || currentFringe.size() + currentNearest.size() < k) {
            if (data.getLeft().isPresent()) {
              neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
            }
            if (data.getRight().isPresent()) {
              neighbors(data.getRight().get(), k, ex, why, zee, layer++);
            }
          } else {
            if (current.getZ() <= zee) {
              if (data.getRight().isPresent()) {
                neighbors(data.getRight().get(), k, ex, why, zee, layer++);
              }
            } else {
              if (data.getLeft().isPresent()) {
                neighbors(data.getLeft().get(), k, ex, why, zee, layer++);
              }
            }
          }
          break;
        default:
      }
    }
  }
}
