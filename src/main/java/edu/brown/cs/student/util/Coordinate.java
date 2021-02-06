package edu.brown.cs.student.util;

import java.util.ArrayList;

public interface Coordinate {

  /**
   * Returns the coordinate ArrayList<Double> corresponding with the Coordinate.
   * @return the coordinate of the object
   */
  ArrayList<Double> getCoordinate();

  /**
   * Returns the coordinate of the given dimension of the Coordinate.
   * Target dimension should be defined as layer % number of dimensions.
   * @param l layer beings searched for
   * @return the designated dimension value of the object
   */
  double getCoordinate(int l);

  /**
   * Returns the number of dimensions of the Coordinate.
   * @return the number of dimensions of the Object
   */
  int getDimension();
}
