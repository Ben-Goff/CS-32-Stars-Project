package edu.brown.cs.student.util;

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
   */
  void run(String argumentString) throws IOException;

}
