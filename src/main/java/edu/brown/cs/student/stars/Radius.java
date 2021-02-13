package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.KDTree;
import edu.brown.cs.student.util.REPLCommand;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Radius data structure for running radius commands.
 */
public class Radius implements REPLCommand {

  /**
   * Creates an empty Radius object.
   */
  public Radius() {
  }

  /**
   * Returns the name of the Radius command "radius".
   * @return String "radius"
   */
  @Override
  public String name() {
    return "radius";
  }

  /**
   * Runs the Radius command with the inputted string containing commands.
   * @param argumentString String containing all arguments of Radius command
   * @return
   */
  @Override
  public Star[] run(String argumentString) {
    String[] closeStars;
    boolean twoParam;
    boolean fourParam;
    double radius;
    //Checks that the command is valid and what form it takes (coordinates/star name)
    twoParam = Pattern.matches("(\\s*[A-z0-9.]+\\s+\".+\")", argumentString);
    fourParam = Pattern.matches("(\\s*[A-z0-9.]+\\s+[A-z0-9.-]+\\s+[A-z0-9.-]+\\s+[A-z0-9.-]+)",
        argumentString);
    if (twoParam) {
      int firstQuote = argumentString.indexOf("\"");
      int secondQuote = argumentString.lastIndexOf("\"");
      radius = Double.parseDouble(argumentString.substring(0, firstQuote - 1).trim());
      String name = argumentString.substring(firstQuote + 1, secondQuote);
      return radius(radius, name);
    } else {
      if (fourParam) {
        String[] arguments = argumentString.trim().split(" +");
        radius = Double.parseDouble(Array.get(arguments, 0).toString());
        double x = Double.parseDouble(Array.get(arguments, 1).toString());
        double y = Double.parseDouble(Array.get(arguments, 2).toString());
        double z = Double.parseDouble(Array.get(arguments, 3).toString());
        return radius(radius, x, y, z);
      } else {
        // Throw error if argumentString doesn't match Radius command regex
        System.out.println("ERROR: malformed radius command");
      }
    }
    return new Star[0];
  }

  /**
   * Returns the iDs of all Stars whose distance from the input coordinate is less than r.
   * @param r
   * @param ex
   * @param why
   * @param zee
   * @return ArrayList<Star>
   */
  public static Star[] radius(double r, double ex, double why, double zee) {
    KDTree starTree = Star.getStarTree();
    ArrayList<Star> withinRadiusLeft = new ArrayList<>();
    ArrayList<Star> withinRadiusRight = new ArrayList<>();
    if (starTree.getNode().isEmpty()) {
      //if theres nothing to search through, return nothing
      return new Star[0];
    } else {
      Star node = (Star) starTree.getNode().get();
      //move down the branches that could contain the targets sphere of interest
      if (ex - node.getX() < r) {
        withinRadiusLeft = radius(starTree.getLeft(), r, ex, why, zee, 1);
      }
      if (node.getX() - ex <= r) {
        withinRadiusRight = radius(starTree.getRight(), r, ex, why, zee, 1);
      }
      //elements are added one at a time, as the branches are iterated down
      if ((node.distance(ex, why, zee) <= r)) {
        withinRadiusRight.add(node);
      }
      //Streams used to filter data
      return Stream.of(withinRadiusLeft, withinRadiusRight).flatMap(los -> los.stream())
          .sorted(Comparator.comparingDouble(s -> s.distance(ex, why, zee))).toArray(Star[]::new);
    }
  }

  /**
   * Returns the iDs of all Stars whose distance from star with the inputted name is less than r.
   * @param r
   * @param name
   * @return ArrayList<Star>
   */
  public static Star[] radius(double r, String name) {
    KDTree starTree = Star.getStarTree();
    try {
      Star target = Star.getStar(name);
      if (target == null) {
        //throw error is target start not in list of loaded stars
        throw new RuntimeException("ERROR: Star not found");
      }
      ArrayList<Star> withinRadiusLeft = new ArrayList<>();
      ArrayList<Star> withinRadiusRight = new ArrayList<>();
      if (starTree.getNode().isEmpty()) {
        return new Star[0];
      } else {
        //same process as above, but adapted for a star Object
        Star node = (Star) starTree.getNode().get();
        if (target.getX() - node.getX() < r) {
          withinRadiusLeft = radius(starTree.getLeft(), r,
              target.getX(), target.getY(), target.getZ(), 1);
        }
        if (node.getX() - target.getX() <= r) {
          withinRadiusRight = radius(starTree.getRight(), r,
              target.getX(), target.getY(), target.getZ(), 1);
        }
        if ((node.distance(target.getX(), target.getY(), target.getZ()) <= r)) {
          withinRadiusRight.add(node);
        }
        //Streams used to filter data
        return Stream.of(withinRadiusLeft, withinRadiusRight).flatMap(los -> los.stream())
            .filter(star -> !star.getProperName().equals(name)).toArray(Star[]::new);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new Star[0];
    }
  }

  /**
   * Returns the iDs of all Stars whose distance from the input coordinate
   * is less than r in the given KDTree, slicing on coordinate l.
   * @param tree
   * @param r
   * @param ex
   * @param why
   * @param zee
   * @param l
   * @return ArrayList<Star>
   */
  public static ArrayList<Star> radius(Optional<KDTree> tree, double r,
                                       double ex, double why, double zee, int l) {
    //this function introduces a layer parameter, so that the radius function two above
    //can work without one using 0 as a standard value
    int layer = l;
    int dimension = 3;
    int index = layer % dimension;
    ArrayList<Star> withinRadiusLeft = new ArrayList<>();
    ArrayList<Star> withinRadiusRight = new ArrayList<>();
    if (tree.isEmpty()) {
      return new ArrayList<>();
    } else {
      Star node = (Star) tree.get().getNode().get();
      switch (index) {
        case 0:
          if (ex - node.getX() < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.getX() - ex <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        case 1:
          if (why - node.getY() < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.getY() - why <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        case 2:
          if (zee - node.getZ() < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.getZ() - zee <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        default:
      }
      //Streams used to filter data
      return new ArrayList<Star>(Stream.of(withinRadiusLeft, withinRadiusRight)
          .flatMap(los -> los.stream()).collect(Collectors.toList()));
    }
  }
}
