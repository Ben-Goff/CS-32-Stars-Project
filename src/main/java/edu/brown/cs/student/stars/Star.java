package edu.brown.cs.student.stars;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * Star data structure for storing information of all loaded stars.
 */
public class Star {

  private static Vector<Star> starData = new Vector<>();

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
   * Returns the iDs of all Stars whose distance from the Star with properName name is less than r.
   * @param r
   * @param name
   * @return String[]
   */
  public static String[] naiveRadius(double r, String name) {
    try {
      Star coordinate = null;
      for (Star starDatum : starData) {
        if (starDatum.properName.equals(name)) {
          coordinate = starDatum;
          break;
        }
      }
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
