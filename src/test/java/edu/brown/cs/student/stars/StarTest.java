package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.TreeMap;
import static edu.brown.cs.student.stars.Star.getCloseStars;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StarTest {

  private Star star;
  private TreeMap<Double, ArrayList<Star>> tmap;
  private ArrayList<Star> one;
  private ArrayList<Star> two;
  private ArrayList<Star> three;

  /**
   * Resets the Star object.
   */
  @Before
  public void reset() {
    star = new Star("0", "0", Constants.ONE, Constants.ONE, Constants.ONE);
    Star.clearStarData();
    tmap = new TreeMap<>();
    tmap.clear();
    one = new ArrayList<>();
    one.add(new Star("1", "1", Constants.ONE, Constants.ONE, Constants.ONE));
    one.add(new Star("2", "2", Constants.TWO, Constants.TWO, Constants.TWO));
    one.add(new Star("3", "3", Constants.THREE, Constants.THREE, Constants.THREE));
    two = new ArrayList<>();
    two.add(new Star("4", "4", Constants.FOUR, Constants.FOUR, Constants.FOUR));
    two.add(new Star("5", "5", Constants.FIVE, Constants.FIVE, Constants.FIVE));
    two.add(new Star("6", "6", Constants.SIX, Constants.SIX, Constants.SIX));
    three = new ArrayList<>();
    three.add(new Star("7", "7", Constants.SEVEN, Constants.SEVEN, Constants.SEVEN));
    three.add(new Star("8", "8", Constants.EIGHT, Constants.EIGHT, Constants.EIGHT));
    three.add(new Star("9", "9", Constants.NINE, Constants.NINE, Constants.NINE));
    tmap.put(Constants.FIVE_TWO, one);
    tmap.put(Constants.SEVEN_FOUR, two);
    tmap.put(Constants.TWENTY_TWO, three);
  }

  /**
   * Tests getCloseStars for zero stars.
   */
  @Test
  public void getZeroStars() {
    reset();
    Star[] output = getCloseStars(Constants.ZERO, tmap);
    assertTrue(output.length == Constants.ZERO);
  }

  /**
   * Tests getCloseStars for all available stars.
   */
  @Test
  public void getAllStars() {
    reset();
    Star[] output = getCloseStars(Constants.NINE, tmap);
    assertTrue(output.length == Constants.NINE);
  }

  /**
   * Tests the equals function for stars.
   */
  @Test
  public void testEquals() {
    reset();
    assertTrue(star.equals(new Star("0", "0", Constants.ONE, Constants.ONE, Constants.ONE)));
    assertFalse(star.equals(new Star("1", "0", Constants.ONE, Constants.ONE, Constants.ONE)));
    assertFalse(star.equals(new Star("0", "1", Constants.ONE, Constants.ONE, Constants.ONE)));
    assertFalse(star.equals(new Star("0", "0", Constants.ZERO, Constants.ONE, Constants.ONE)));
    assertFalse(star.equals(new Star("0", "0", Constants.ONE, Constants.NEG_ONE, Constants.ONE)));
    assertFalse(star.equals(new Star("0", "0", Constants.ONE, Constants.ONE, Constants.NEG_ONE)));
  }

  /**
   * Tests the distance function for stars.
   */
  @Test
  public void testDistance() {
    reset();
    assertEquals(star.distance(new Star("0", "0", Constants.ONE, Constants.ONE, Constants.ONE)),
        Constants.ZERO, Constants.ALMOST_ZERO);
    assertEquals(star.distance(new Star("0", "0", Constants.ONE, Constants.FOUR, Constants.FIVE)),
        Constants.FIVE, Constants.ALMOST_ZERO);
    assertEquals(star.distance(new Star("0", "0", Constants.NEG_THREE, Constants.FIVE,
            Constants.EIGHT)), Constants.NINE, Constants.ALMOST_ZERO);
    assertEquals(star.distance(Constants.ONE, Constants.ONE, Constants.ONE),
        Constants.ZERO, Constants.ALMOST_ZERO);
    assertEquals(star.distance(Constants.ONE, Constants.FOUR, Constants.FIVE),
        Constants.FIVE, Constants.ALMOST_ZERO);
    assertEquals(star.distance(Constants.NEG_THREE, Constants.FIVE, Constants.EIGHT),
        Constants.NINE, Constants.ALMOST_ZERO);
  }
}
