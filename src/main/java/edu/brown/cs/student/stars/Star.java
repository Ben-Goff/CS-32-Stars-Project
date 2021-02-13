package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.Coordinate;
import edu.brown.cs.student.util.KDTree;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;
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
   * Returns the current Vector of parsed Stars.
   * @return Vector<Star>
   */
  public static Vector<Star> getStarData() {
    return starData;
  }

  /**
   * Sets the current Vector of parsed Stars.
   * @param data Vector of stars to be loaded
   */
  public static void setStarData(Vector<Star> data) {
    starData = data;
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
   * Builds the KDTree of Stars based on the loaded StarData.
   */
  public static void setStarTree() {
    starTree = new KDTree(new ArrayList<>(starData));
  }

  /**
   * Returns the current KDTree of parsed Stars.
   * @return KDTree
   */
  public static KDTree getStarTree() {
    return starTree;
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

  public ArrayList<Double> getCoordinate() {
    return new ArrayList<>(Arrays.asList(this.x, this.y, this.z));
  }

  public double getCoordinate(int l) {
    return this.getCoordinate().get(l % 3);
  }

  public int getDimension() {
    return 3;
  };

  @Override
  public String toString() {
    return "Star{"
        + "starID='" + starID + '\''
        + ", properName='" + properName + '\''
        + ", x=" + x
        + ", y=" + y
        + ", z=" + z
        + '}';
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
   * Returns the Star object with the given ProperName. Returns null if none exists.
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
