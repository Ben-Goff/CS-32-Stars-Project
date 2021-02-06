package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.REPLCommand;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

/**
 * Radius data structure for running radius commands.
 */
public class Radius implements REPLCommand {

  /**
   * Creates an empty Radius object.
   */
  public Radius() {
  }

  /**
   * Returns the name of the Radius command "radius".
   * @return String "radius"
   */
  @Override
  public String name() {
    return "radius";
  }

  /**
   * Runs the Radius command with the inputted string containing commands.
   * @param argumentString String containing all arguments of Radius command
   */
  @Override
  public void run(String argumentString) {
    String[] closeStars;
    boolean twoParam;
    boolean fourParam;
    double radius;
    twoParam = Pattern.matches("(\\s+[A-z0-9.-]+\\s+\"[A-z0-9\\s]+\")", argumentString);
    fourParam = Pattern.matches("(\\s+[A-z0-9.-]+\\s+[A-z0-9.-]+\\s+[A-z0-9.-]+\\s+[A-z0-9.-]+)",
        argumentString);
    if (twoParam) {
      int firstQuote = argumentString.indexOf("\"");
      int secondQuote = argumentString.lastIndexOf("\"");
      radius = Double.parseDouble(argumentString.substring(0, firstQuote - 1).trim());
      String name = argumentString.substring(firstQuote + 1, secondQuote);
      closeStars = Star.radius(radius, name);
      for (int i = 0; i < closeStars.length; i++) {
        System.out.println(Array.get(closeStars, i));
      }
    } else {
      if (fourParam) {
        String[] arguments = argumentString.trim().split(" +");
        radius = Double.parseDouble(Array.get(arguments, 0).toString());
        double x = Double.parseDouble(Array.get(arguments, 1).toString());
        double y = Double.parseDouble(Array.get(arguments, 2).toString());
        double z = Double.parseDouble(Array.get(arguments, 3).toString());
        closeStars = Star.radius(radius, x, y, z);
        for (int i = 0; i < closeStars.length; i++) {
          System.out.println(Array.get(closeStars, i));
        }
      } else {
        // Throw error if argumentString doesn't match NaiveRadius command regex
        System.out.println("ERROR: malformed radius command");
      }
    }
  }

}
