package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class StarInput {

  public static final double FIVE_HUNDRED = 500.0;
  public static final double TWO_FIFTY = 250.0;
  public static final double POINT_FIVE = 0.5;
  public static final int TWO_OH_ONE = 201;

  private Vector<Star> stars;
  private int count;
  private double radius;
  private double x;
  private double y;
  private double z;
  private String name;

  /**
   * Returns the randomized star data.
   * @return Vector<Star> star data
   */
  public Vector<Star> getStars() {
    return stars;
  }

  /**
   * Returns the randomized count of stars.
   * @return int star count
   */
  public int getCount() {
    return count;
  }

  /**
   * Returns the randomized target x coordinate.
   * @return double target x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the randomized target y coordinate.
   * @return double target y coordinate
   */
  public double getY() {
    return y;
  }

  /**
   * Returns the randomized target z coordinate.
   * @return double target z coordinate
   */
  public double getZ() {
    return z;
  }

  /**
   * Returns the randomized target name.
   * @return String target name
   */
  public String getName() {
    return name;
  }

  /**
   * Creates a new, randomized StarInput with all fields necessary for Stars commands.
   * Useful for property based testing.
   */
  public StarInput() {
    int starCount = ThreadLocalRandom.current().nextInt(2, Constants.THIRTY);
    Vector<Star> listBuilder = new Vector<>();
    for (int i = 0; i < starCount; i++) {
      listBuilder.add(new Star(Integer.toString(i), Integer.toString(i),
          (Math.random() * FIVE_HUNDRED - TWO_FIFTY),
          (Math.random() * FIVE_HUNDRED - TWO_FIFTY),
          (Math.random() * FIVE_HUNDRED - TWO_FIFTY)));
    }
    stars = listBuilder;
    count = ThreadLocalRandom.current().nextInt(1, starCount + 1);
    radius = (Math.random() - POINT_FIVE) * FIVE_HUNDRED;
    x = (Math.random() - POINT_FIVE) * TWO_FIFTY;
    y = (Math.random() - POINT_FIVE) * TWO_FIFTY;
    z = (Math.random() - POINT_FIVE) * TWO_FIFTY;
    name = Integer.toString(ThreadLocalRandom.current()
        .nextInt(Constants.FIVE, Math.max(starCount - Constants.FIVE, Constants.SEVEN)));
  }
}
