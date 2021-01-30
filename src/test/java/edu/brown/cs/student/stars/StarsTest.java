package edu.brown.cs.student.stars;

import edu.brown.cs.student.Constants;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class StarsTest {

  private Stars stars;

  /**
   * Resets the NaiveNeighbors object.
   */
  @Before
  public void reset() {
    stars = new Stars();
    Star.clearStarData();
  }

  /**
   ** Tests command name returned properly.
   */
  @Test
  public void testName() {
    reset();
    assertTrue(stars.name().equals("stars"));
  }

  /**
   ** Tests that the correct number of stars are loaded into data.
   */
  @Test
  public void testFileLength() throws IOException {
    reset();
    Stars.loadData("data/stars/one-star.csv");
    assertTrue(Star.getStarData().size() == Constants.ONE);
    Stars.loadData("data/stars/three-star.csv");
    assertTrue(Star.getStarData().size() == Constants.THREE);
    Stars.loadData("data/stars/ten-star.csv");
    assertTrue(Star.getStarData().size() == Constants.TEN);
    Stars.loadData("data/stars/stardata.csv");
    assertTrue(Star.getStarData().size() == Constants.ONEONENINESIXONESEVEN);
  }

  /**
   ** Tests that stars are properly loaded into data.
   */
  @Test
  public void testStarLoading() throws IOException {
    reset();
    Star expected;
    Stars.loadData("data/stars/one-star.csv");
    expected = new Star("1", "Lonely Star", 5, Constants.NEG_TWO_TWOFOUR, Constants.TEN_ZEROFOUR);
    assertTrue(Star.getStarData().get(0).equals(expected));
    Stars.loadData("data/stars/ten-star.csv");
    expected = new Star("70667", "Proxima Centauri", Constants.PROXIMA_CENTAURI_X,
        Constants.PROXIMA_CENTAURI_Y, Constants.PROXIMA_CENTAURI_Z);
    assertTrue(Star.getStarData().get(5).equals(expected));
  }
}
