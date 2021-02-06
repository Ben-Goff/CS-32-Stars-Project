package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.Coordinate;
import edu.brown.cs.student.util.KDTree;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Star data structure for storing information of all loaded stars.
 */
public class Star implements Coordinate {

  private static Vector<Star> starData = new Vector<>();
  private static KDTree starTree = new KDTree(3);

  private String starID;
  private String properName;
  private double x;
  private double y;
  private double z;

  /**
   * Returns the current Vector of parsed Stars.
   * @return Vector<Star>
   */
  public static Vector<Star> getStarData() {
    return starData;
  }

  /**
   * Builds the KDTree of Stars based on the loaded StarData.
   */
  public static void setStarTree() {
    starTree = new KDTree(new ArrayList<>(starData));
  }

  /**
   * Returns the X coordinate of the given Star.
   * @return double
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns the Y coordinate of the given Star.
   * @return double
   */
  public double getY() {
    return this.y;
  }

  /**
   * Returns the Z coordinate of the given Star.
   * @return double
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Clears the current Vector of parsed Stars.
   */
  public static void clearStarData() {
    starData = new Vector<>();
  }

  /**
   * Adds a new star to the current Vector of parsed Stars.
   * @param s
   */
  public static void addStarData(Star s) {
    starData.add(s);
  }

  public ArrayList<Double> getCoordinate() {
    return new ArrayList<>(Arrays.asList(this.x, this.y, this.z));
  }

  public double getCoordinate(int l) {
    return this.getCoordinate().get(l % 3);
  }

  public int getDimension() {
    return 3;
  };

  /**
   * Creates a Star object with the inputted iD, name, and coordinate.
   * @param iD
   * @param name
   * @param ex
   * @param why
   * @param zee
   */
  public Star(String iD, String name, double ex, double why, double zee) {
    starID = iD;
    properName = name;
    x = ex;
    y = why;
    z = zee;
  }

  /**
   * Returns if two Stars are equal.
   * @param s
   * @return boolean
   */
  public boolean equals(Star s) {
    return s.starID.equals(this.starID) && s.properName.equals(this.properName)
        && (s.x == this.x) && (s.y == this.y) && (s.z == this.z);
  }

  /**
   * Returns the iDs of all Stars whose distance from the input coordinate is less than r.
   * @param r
   * @param ex
   * @param why
   * @param zee
   * @return ArrayList<Star>
   */
  public static String[] radius(double r, double ex, double why, double zee) {
    int layer = 0;
    int dimension = 3;
    int index = layer % dimension;
    ArrayList<Star> withinRadiusLeft = new ArrayList<>();
    ArrayList<Star> withinRadiusRight = new ArrayList<>();
    if (starTree.getNode().isEmpty()) {
      return new String[0];
    } else {
      Star node = (Star) starTree.getNode().get();
      if (ex - node.x < r) {
        withinRadiusLeft = radius(starTree.getLeft(), r, ex, why, zee, 1);
      }
      if (node.x - ex <= r) {
        withinRadiusRight = radius(starTree.getRight(), r, ex, why, zee, 1);
      }
      if ((node.distance(ex, why, zee) <= r)) {
        withinRadiusRight.add(node);
      }
      return Stream.of(withinRadiusLeft, withinRadiusRight)
          .flatMap(los -> los.stream()).map(star -> star.starID).toArray(String[]::new);
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
    int layer = l;
    int dimension = 3;
    int index = layer % dimension;
    ArrayList<Star> withinRadiusLeft = new ArrayList<>();
    ArrayList<Star> withinRadiusRight = new ArrayList<>();
    if (tree.isEmpty()) {
      return new ArrayList<>();
    } else {
      Star node = (Star) starTree.getNode().get();
      switch (index) {
        case 1:
          if (ex - node.x < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.x - ex <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        case 2:
          if (why - node.y < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.y - why <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        case 3:
          if (zee - node.z < r) {
            withinRadiusLeft = radius(tree.get().getLeft(), r, ex, why, zee, layer++);
          }
          if (node.z - zee <= r) {
            withinRadiusRight = radius(tree.get().getRight(), r, ex, why, zee, layer++);
          }
          if ((node.distance(ex, why, zee) <= r)) {
            withinRadiusRight.add(node);
          }
          break;
        default:
      }
      return new ArrayList<Star>(Stream.of(withinRadiusLeft, withinRadiusRight)
          .flatMap(los -> los.stream()).collect(Collectors.toList()));
    }
  }

  /**
   * Returns the iDs of all Stars whose distance from star with the inputted name is less than r.
   * @param r
   * @param name
   * @return ArrayList<Star>
   */
  public static String[] radius(double r, String name) {
    try {
      Star target = getStar(name);
      if (target == null) {
        throw new RuntimeException("Star not found");
      }
      int layer = 0;
      int dimension = 3;
      int index = layer % dimension;
      ArrayList<Star> withinRadiusLeft = new ArrayList<>();
      ArrayList<Star> withinRadiusRight = new ArrayList<>();
      if (starTree.getNode().isEmpty()) {
        return new String[0];
      } else {
        Star node = (Star) starTree.getNode().get();
        if (target.x - node.x < r) {
          withinRadiusLeft = radius(starTree.getLeft(), r, target.x, target.y, target.z, 1);
        }
        if (node.x - target.x <= r) {
          withinRadiusRight = radius(starTree.getRight(), r, target.x, target.y, target.z, 1);
        }
        if ((node.distance(target.x, target.y, target.z) <= r)) {
          withinRadiusRight.add(node);
        }
        return Stream.of(withinRadiusLeft, withinRadiusRight)
            .flatMap(los -> los.stream()).filter(star -> !star.properName.equals(name))
            .map(star -> star.starID).toArray(String[]::new);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new String[0];
    }
  }

  /**
   * Returns the iDs of all Stars whose distance from the input coordinate is less than r.
   * @param r
   * @param ex
   * @param why
   * @param zee
   * @return String[]
   */
  public static String[] naiveRadius(double r, double ex, double why, double zee) {
    Stream<Star> starStream = starData.stream()
        .sorted(Comparator.comparingDouble(s -> s.distance(ex, why, zee)));
    return starStream.filter(s -> s.distance(ex, why, zee) <= r)
            .map(star -> star.starID).toArray(String[]::new);
  }

  /**
   * Returns the iD of the star.
   * @return String
   */
  public String getStarID() {
    return starID;
  }

  /**
   * Returns the name of the star.
   * @return String
   */
  public String getProperName() {
    return properName;
  }

  /**
   * Returns the Star object with the given ProperName.
   * @param pn
   * @return Star
   */
  public static Star getStar(String pn) {
    Star coordinate = null;
    for (Star starDatum : starData) {
      if (starDatum.properName.equals(pn)) {
        coordinate = starDatum;
        break;
      }
    }
    return coordinate;
  }

  /**
   * Returns the iDs of all Stars whose distance from the Star with properName name is less than r.
   * @param r
   * @param name
   * @return String[]
   */
  public static String[] naiveRadius(double r, String name) {
    try {
      Star coordinate = getStar(name);
      if (coordinate == null) {
        throw new RuntimeException("ERROR: Star not found");
      } else {
        Star finalCoordinate = coordinate;
        Stream<Star> starStream = starData.stream()
            .sorted(Comparator.comparingDouble(s -> s.distance(finalCoordinate)));
        return starStream.filter(s -> s.distance(finalCoordinate) <= r && !s.starID
                .equals(finalCoordinate.starID))
                            .map(star -> star.starID).toArray(String[]::new);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new String[0];
    }
  }

  /**
   * Returns the iDs of the closest k stars to the input coordinate.
   * Ties chosen randomly.
   * @param k
   * @param ex
   * @param why
   * @param zee
   * @return String[]
   */
  public static String[] naiveNeighbors(int k, double ex, double why, double zee) {
    if (k > starData.size()) {
      throw new RuntimeException("ERROR: " + k + " is more than the loaded number of stars ("
          + starData.size() + ")");
    }
    TreeMap<Double, ArrayList<Star>> starDistance = new TreeMap();
    ArrayList<Star> currentValue;
    Star currentStar;
    double currentDistance;
    for (int i = 0; i < starData.size(); i++) {
      currentStar = starData.get(i);
      currentDistance = currentStar.distance(ex, why, zee);
      if (!starDistance.containsKey(currentDistance)) {
        starDistance.put(currentDistance, new ArrayList<Star>());
      }
      starDistance.get(currentDistance).add(currentStar);
    }
    return getCloseStars(k, (TreeMap<Double, ArrayList<Star>>) starDistance);
  }

  /**
   * Returns the iDs of the closest k stars to the Star with properName name.
   * Ties chosen randomly.
   * @param k
   * @param name
   * @return String[]
   */
  public static String[] naiveNeighbors(int k, String name) {
    try {
      Star coordinate = null;
      for (Star starDatum : starData) {
        if (starDatum.getProperName().equals(name)) {
          coordinate = starDatum;
          break;
        }
      }
      if (coordinate == null) {
        throw new RuntimeException("ERROR: Star not found");
      } else {
        if (k + 1 > starData.size()) {
          return new String[0];
        }
        TreeMap<Double, ArrayList<Star>> starDistance = new TreeMap();
        ArrayList<Star> currentValue;
        Star currentStar;
        double currentDistance;
        for (int i = 0; i < starData.size(); i++) {
          currentStar = starData.get(i);
          if (!currentStar.getStarID().equals(coordinate.getStarID())) {
            currentDistance = currentStar.distance(coordinate);
            if (!starDistance.containsKey(currentDistance)) {
              starDistance.put(currentDistance, new ArrayList<Star>());
            }
            starDistance.get(currentDistance).add(currentStar);
          }
        }
        return getCloseStars(k, (TreeMap<Double, ArrayList<Star>>) starDistance);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new String[0];
    }
  }

  /**
   * Returns the k stars with the smallest distance,
   * defined by the corresponding double in the TreeMap.
   * Ties chosen randomly by shuffling before choosing.
   * @param k
   * @param starDistance
   * @return String[]
   */
  public static String[] getCloseStars(int k, TreeMap<Double, ArrayList<Star>> starDistance) {
    String[] closeStars = new String[k];
    int counted = 0;
    int mapStarCount;
    ArrayList<Star> starsAtMap;
    while ((counted < k) && (k != 0)) {
      starsAtMap = starDistance.pollFirstEntry().getValue();
      mapStarCount = starsAtMap.size();
      if (mapStarCount > k - counted) {
        Collections.shuffle(starsAtMap);
        for (int i = 0; i < k - counted; i++) {
          Array.set(closeStars, counted + i, starsAtMap.get(i).getStarID());
        }
        counted = k;
      } else {
        for (int i = 0; i < mapStarCount; i++) {
          Array.set(closeStars, counted + i, starsAtMap.get(i).getStarID());
        }
        counted += mapStarCount;
      }
    }
    return closeStars;
  }

  /**
   * Returns the distance between two Stars.
   * @param s
   * @return double
   */
  public double distance(Star s) {
    double deltax = s.x - this.x;
    double deltay = s.y - this.y;
    double deltaz = s.z - this.z;
    return Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2) + Math.pow(deltaz, 2));
  }

  /**
   * Returns the distance between a Star and a coordinate.
   * @param ex
   * @param why
   * @param zee
   * @return double
   */
  public double distance(double ex, double why, double zee) {
    double deltax = this.x - ex;
    double deltay = this.y - why;
    double deltaz = this.z - zee;
    return Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2) + Math.pow(deltaz, 2));
  }
}
