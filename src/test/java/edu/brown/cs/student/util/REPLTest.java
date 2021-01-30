package edu.brown.cs.student.util;

import edu.brown.cs.student.stars.Stars;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class REPLTest {

  private REPL r = new REPL();


  /**
   * Resets the REPL object.
   */
  @Before
  public void reset() {
    r = new REPL();
  }

  /**
   * Checks that commands are added properly to the REPL.
   */
  @Test
  public void testAddCommand() {
    reset();
    r.addCommand(new Stars());
    assertTrue(r.getCommands().size() == 1);
    assertTrue(r.getCommands().get(0).name().equals("stars"));
  }
}
