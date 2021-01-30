package edu.brown.cs.student.util;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CSVParserTest {

  private CSVParser parser;

  /**
   * Resets the CSVParser object.
   */
  @Before
  public void reset() {
    try {
      parser = new CSVParser("data/stars/ten-star.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks that the first line of a CSV file is read properly.
   */
  @Test
  public void testFirstLine() {
    reset();
    assertEquals(parser.getFirstLine(), "StarID,ProperName,X,Y,Z");
  }

  /**
   * Checks that the first line of a CSV file is parsed properly.
   */
  @Test
  public void testFirstLineParsed() {
    reset();
    String[] flp = parser.getFirstLineParsed();
    assertTrue(flp.length == 5);
    assertTrue(Arrays.stream(flp).anyMatch("StarID"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("ProperName"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("X"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("Y"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("Z"::equals));
  }

  /**
   * Checks that the lines of data are properly parsed.
   */
  @Test
  public void testCorrectParsing() throws IOException {
    reset();
    String[] flp = parser.parseLine().get();
    assertTrue(flp.length == 5);
    assertTrue(Arrays.stream(flp).anyMatch("0"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("Sol"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("0"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("0"::equals));
    assertTrue(Arrays.stream(flp).anyMatch("0"::equals));
  }
}
