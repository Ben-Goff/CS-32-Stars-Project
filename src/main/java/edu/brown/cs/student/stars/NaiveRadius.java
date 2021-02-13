package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.REPLCommand;
import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * NaiveRadius data structure for running naive_radius commands.
 */
public class NaiveRadius implements REPLCommand {

  /**
   * Creates an empty NaiveRadius object.
   */
  public NaiveRadius() {
  }

  /**
   * Returns the name of the NaiveRadius command "naive_radius".
   * @return String "naive_radius"
   */
  @Override
  public String name() {
    return "naive_radius";
  }

  /**
   * Runs the NaiveRadius command with the inputted string containing commands.
   * @param argumentString String containing all arguments of NaiveRadius command
   * @return
   */
  @Override
  public Star[] run(String argumentString) {
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
      return naiveRadius(radius, name);
    } else {
      if (fourParam) {
        String[] arguments = argumentString.trim().split(" +");
        radius = Double.parseDouble(Array.get(arguments, 0).toString().trim());
        double x = Double.parseDouble(Array.get(arguments, 1).toString());
        double y = Double.parseDouble(Array.get(arguments, 2).toString());
        double z = Double.parseDouble(Array.get(arguments, 3).toString());
        return naiveRadius(radius, x, y, z);
      } else {
        // Throw error if argumentString doesn't match NaiveRadius command regex
        System.out.println("ERROR: malformed naive_radius command");
      }
    }
    return new Star[0];
  }

  /**
   * Returns the iDs of all Stars whose distance from the input coordinate is less than r.
   * @param r
   * @param ex
   * @param why
   * @param zee
   * @return Star[]
   */
  public static Star[] naiveRadius(double r, double ex, double why, double zee) {
    Vector<Star> data = Star.getStarData();
    Stream<Star> starStream = data.stream()
        .sorted(Comparator.comparingDouble(s -> s.distance(ex, why, zee)));
    return starStream.filter(s -> s.distance(ex, why, zee) <= r).toArray(Star[]::new);
  }

  /**
   * Returns the iDs of all Stars whose distance from the Star with properName name is less than r.
   * @param r
   * @param name
   * @return Star[]
   */
  public static Star[] naiveRadius(double r, String name) {
    try {
      Star coordinate = Star.getStar(name);
      if (coordinate == null) {
        throw new RuntimeException("ERROR: Star not found");
      } else {
        Star finalCoordinate = coordinate;
        Vector<Star> data = Star.getStarData();
        Stream<Star> starStream = data.stream()
            .sorted(Comparator.comparingDouble(s -> s.distance(finalCoordinate)));
        return starStream.filter(s -> s.distance(finalCoordinate) <= r && !s.getStarID()
            .equals(finalCoordinate.getStarID())).toArray(Star[]::new);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new Star[0];
    }
  }
}
