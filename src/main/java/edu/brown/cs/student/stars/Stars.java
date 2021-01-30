package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.CSVParser;
import edu.brown.cs.student.util.REPLCommand;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Stars data structure for running stars commands.
 */
public class Stars implements REPLCommand {

  /**
   * Creates an empty Stars object.
   */
  public Stars() {
  };

  /**
   * Returns the name of the Stars fcommand "stars".
   * @return String
   */
  @Override
  public String name() {
    return "stars";
  }

  /**
   * Runs the Stars command with the inputted string containing commands.
   * @param argumentString
   */
  @Override
  public void run(String argumentString) throws IOException {
    boolean isFile = Pattern.matches("(\\s+[A-z0-9/-]+.csv\\s*)", argumentString);
    if (isFile) {
      String filename = argumentString.trim();
      loadData(filename);
    } else {
      // Throw error if argumentString doesn't match Stars command regex
      System.out.println("ERROR: Malformed stars command");
    }
  }

  /**
   * Parses all Stars from the inputted file, loading them into StarData.
   * @param filename
   */
  public static void loadData(String filename) throws IOException {
    try {
      CSVParser stars = new CSVParser(filename);
      if (!stars.getFirstLine().equals("StarID,ProperName,X,Y,Z")) {
        // Throw error if header is incorrect
        throw new RuntimeException("ERROR: Invalid File Header");
      }
      Star.clearStarData();
      Optional<String[]> lineData = stars.parseLine();
      String[] optData;
      String iD;
      String name;
      double x;
      double y;
      double z;
      while (lineData.isPresent()) {
        optData = lineData.get();
        iD = Array.get(optData, 0).toString();
        name = Array.get(optData, 1).toString();
        x = Double.parseDouble((Array.get(optData, 2)).toString());
        y = Double.parseDouble((Array.get(optData, 3)).toString());
        z = Double.parseDouble((Array.get(optData, 4)).toString());
        Star lineStar = new Star(iD, name, x, y, z);
        Star.addStarData(lineStar);
        lineData = stars.parseLine();
      }
      int starCount = Star.getStarData().size();
      System.out.println("Read " + starCount + " stars from " + filename);
    } catch (FileNotFoundException e) {
      // Throw error if input file name does not exist
      System.out.println("ERROR: File not found");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
