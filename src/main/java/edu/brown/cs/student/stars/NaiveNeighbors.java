package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.REPLCommand;
import java.lang.reflect.Array;
import java.util.regex.Pattern;

/**
 * NaiveNeighbors data structure for running naive_neighbors commands.
 */
public class NaiveNeighbors implements REPLCommand {

  /**
   * Creates an empty NaiveNeighbors object.
   */
  public NaiveNeighbors() {
  };

  /**
   * Returns the name of the NaiveNeighbors command "stars".
   * @return String
   */
  @Override
  public String name() {
    return "naive_neighbors";
  }

  /**
   * Runs the NaiveNeighbors command with the inputted string containing commands.
   * @param argumentString
   */
  @Override
  public void run(String argumentString) {
    try {
      String[] closeStars;
      boolean twoParam;
      boolean fourParam;
      int count;
      twoParam = Pattern.matches("(\\s+[A-z0-9-.]+\\s+\"[A-z0-9-.\\s]+\")", argumentString);
      fourParam = Pattern.matches("(\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+)",
          argumentString);
      if (twoParam) {
        int firstQuote = argumentString.indexOf("\"");
        int secondQuote = argumentString.lastIndexOf("\"");
        count = Integer.parseInt(argumentString.substring(0, firstQuote - 1).trim());
        String name = argumentString.substring(firstQuote + 1, secondQuote);
        closeStars = Star.naiveNeighbors(count, name);
        for (int i = 0; i < closeStars.length; i++) {
          System.out.println(Array.get(closeStars, i));
        }
      } else {
        if (fourParam) {
          String[] arguments = argumentString.trim().split(" +");
          count = Integer.parseInt(Array.get(arguments, 0).toString());
          double x = Double.parseDouble(Array.get(arguments, 1).toString());
          double y = Double.parseDouble(Array.get(arguments, 2).toString());
          double z = Double.parseDouble(Array.get(arguments, 3).toString());
          closeStars = Star.naiveNeighbors(count, x, y, z);
          for (int i = 0; i < closeStars.length; i++) {
            System.out.println(Array.get(closeStars, i));
          }
        } else {
          // Throw error if argumentString doesn't match NaiveNeighbors command regex
          System.out.println("ERROR: malformed naive_neighbors command");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
