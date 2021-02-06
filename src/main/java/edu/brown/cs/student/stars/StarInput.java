package edu.brown.cs.student.stars;

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
   * Creates a new StarInput with all fields necessary for Stars commands.
   */
  public StarInput() {
    int starCount = ThreadLocalRandom.current().nextInt(0, TWO_OH_ONE);
    Vector<Star> listBuilder = new Vector<>();
    for (int i = 0; i < starCount; i++) {
      listBuilder.add(new Star(Integer.toString(i), Integer.toString(i),
          Math.random() * (Math.random() * FIVE_HUNDRED - TWO_FIFTY),
          Math.random() * (Math.random() * FIVE_HUNDRED - TWO_FIFTY),
          Math.random() * (Math.random() * FIVE_HUNDRED - TWO_FIFTY)));
    }
    stars = listBuilder;
    count = ThreadLocalRandom.current().nextInt(0, starCount + 1);
    radius = (Math.random() - POINT_FIVE) * FIVE_HUNDRED;
    x = (Math.random() - POINT_FIVE) * FIVE_HUNDRED;
    y = (Math.random() - POINT_FIVE) * FIVE_HUNDRED;
    z = (Math.random() - POINT_FIVE) * FIVE_HUNDRED;
    name = Integer.toString(ThreadLocalRandom.current().nextInt(0, starCount + 1));
  }

}
