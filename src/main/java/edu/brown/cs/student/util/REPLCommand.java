package edu.brown.cs.student.util;

import edu.brown.cs.student.stars.Star;
import java.io.IOException;

public interface REPLCommand {

  /**
   * Returns the name of a REPL Command.
   * @return String
   */
  String name();

  /**
   * Runs the REPL command with the inputted string containing commands.
   * @param argumentString
   * @return Star[]
   */
  Star[] run(String argumentString) throws IOException;

}
