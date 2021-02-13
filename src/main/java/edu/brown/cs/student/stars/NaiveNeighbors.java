package edu.brown.cs.student.stars;

import edu.brown.cs.student.util.REPLCommand;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;
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
   * @return
   */
  @Override
  public Star[] run(String argumentString) {
    try {
      boolean twoParam;
      boolean fourParam;
      int count;
      //Checks that the command is valid and what form it takes (coordinates/star name)
      twoParam = Pattern.matches("(\\s*[A-z0-9]+\\s+\".+\")", argumentString);
      fourParam = Pattern.matches("(\\s*[A-z0-9]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+\\s+[A-z0-9-.]+)",
          argumentString);
      if (twoParam) {
        int firstQuote = argumentString.indexOf("\"");
        int secondQuote = argumentString.lastIndexOf("\"");
        count = Integer.parseInt(argumentString.substring(0, firstQuote - 1).trim());
        String name = argumentString.substring(firstQuote + 1, secondQuote);
        return naiveNeighbors(count, name);
      } else {
        if (fourParam) {
          String[] arguments = argumentString.trim().split(" +");
          count = Integer.parseInt(Array.get(arguments, 0).toString());
          double x = Double.parseDouble(Array.get(arguments, 1).toString());
          double y = Double.parseDouble(Array.get(arguments, 2).toString());
          double z = Double.parseDouble(Array.get(arguments, 3).toString());
          return naiveNeighbors(count, x, y, z);
        } else {
          // Throw error if argumentString doesn't match NaiveNeighbors command regex
          System.out.println("ERROR: malformed naive_neighbors command");
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new Star[0];
  }

  /**
   * Returns the iDs of the closest k stars to the input coordinate.
   * Ties chosen randomly.
   * @param k
   * @param ex
   * @param why
   * @param zee
   * @return String[]
   */
  public static Star[] naiveNeighbors(int k, double ex, double why, double zee) {
    Vector<Star> data = Star.getStarData();
    TreeMap<Double, ArrayList<Star>> starDistance = new TreeMap();
    Star currentStar;
    double currentDistance;
    for (int i = 0; i < data.size(); i++) {
      currentStar = data.get(i);
      currentDistance = currentStar.distance(ex, why, zee);
      if (!starDistance.containsKey(currentDistance)) {
        starDistance.put(currentDistance, new ArrayList<Star>());
      }
      starDistance.get(currentDistance).add(currentStar);
    }
    return getCloseStars(Math.min(k, data.size()), (TreeMap<Double, ArrayList<Star>>) starDistance);
  }

  /**
   * Returns the iDs of the closest k stars to the Star with properName name.
   * Ties chosen randomly.
   * @param k
   * @param name
   * @return String[]
   */
  public static Star[] naiveNeighbors(int k, String name) {
    try {
      Vector<Star> data = Star.getStarData();
      Star coordinate = null;
      for (Star starDatum : data) {
        if (starDatum.getProperName().equals(name)) {
          coordinate = starDatum;
          break;
        }
      }
      if (coordinate == null) {
        //Throw error if inputted star is not in the list of loaded stars
        throw new RuntimeException("ERROR: Star not found");
      } else {
        TreeMap<Double, ArrayList<Star>> starDistance = new TreeMap();
        Star currentStar;
        double currentDistance;
        for (int i = 0; i < data.size(); i++) {
          currentStar = data.get(i);
          if (!currentStar.getStarID().equals(coordinate.getStarID())) {
            currentDistance = currentStar.distance(coordinate);
            if (!starDistance.containsKey(currentDistance)) {
              starDistance.put(currentDistance, new ArrayList<Star>());
            }
            starDistance.get(currentDistance).add(currentStar);
          }
        }
        return getCloseStars(Math.min(k, data.size() - 1),
            (TreeMap<Double, ArrayList<Star>>) starDistance);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new Star[0];
    }
  }

  /**
   * Returns the k stars with the smallest distance,
   * defined by the corresponding double in the TreeMap.
   * Ties chosen randomly by shuffling before choosing.
   * @param k
   * @param starDistance
   * @return String[]
   */
  public static Star[] getCloseStars(int k, TreeMap<Double, ArrayList<Star>> starDistance) {
    Star[] closeStars = new Star[k];
    int counted = 0;
    int mapStarCount;
    ArrayList<Star> starsAtMap;
    while ((counted < k) && (k != 0)) {
      starsAtMap = starDistance.pollFirstEntry().getValue();
      mapStarCount = starsAtMap.size();
      if (mapStarCount > k - counted) {
        Collections.shuffle(starsAtMap);
        for (int i = 0; i < k - counted; i++) {
          Array.set(closeStars, counted + i, starsAtMap.get(i));
        }
        counted = k;
      } else {
        for (int i = 0; i < mapStarCount; i++) {
          Array.set(closeStars, counted + i, starsAtMap.get(i));
        }
        counted += mapStarCount;
      }
    }
    return closeStars;
  }
}
