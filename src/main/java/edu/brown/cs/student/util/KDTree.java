package edu.brown.cs.student.util;
import edu.brown.cs.student.stars.Star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class KDTree {

  private int dimension;
  private int layer;
  private Optional<Coordinate> node;
  private Optional<KDTree> left;
  private Optional<KDTree> right;

  /**
   * Returns the node of a KDTree.
   * @return Optional<Coordinate>
   */
  public Optional<Coordinate> getNode() {
    return node;
  }

  /**
   * Returns the left side of a KDTree.
   * @return Optional<KDTree>
   */
  public Optional<KDTree> getLeft() {
    return left;
  }

  /**
   * Returns the right side of a KDTree.
   * @return Optional<KDTree>
   */
  public Optional<KDTree> getRight() {
    return right;
  }

  /**
   * Creates a new, empty KDTree of the given dimension.
   * @param d dimension
   */
  public KDTree(int d) {
    layer = 0;
    dimension = d;
    node = Optional.empty();
    left = Optional.empty();
    right = Optional.empty();
  }

  /**
   * Creates a new KDTree with the given one coordinate.
   * @param coordinate point at the KDTree root node
   */
  public KDTree(Coordinate coordinate) {
    layer = 0;
    dimension = coordinate.getCoordinate().size();
    node = Optional.of(coordinate);
    left = Optional.empty();
    right = Optional.empty();
  }

  /**
   * Creates a new KDTree with the given one coordinate at the specified root layer.
   * @param coordinate point at the KDTree root node
   * @param l layer at which to start construction
   */
  public KDTree(Coordinate coordinate, int l) {
    layer = l;
    dimension = coordinate.getCoordinate().size();
    node = Optional.of(coordinate);
    left = Optional.empty();
    right = Optional.empty();
  }

  /**
   * Creates a new KDTree of the given Coordinates.
   * @param coordinates ArrayList of Coordinates
   */
  public KDTree(ArrayList<Coordinate> coordinates) {
    layer = 0;
    if (coordinates.isEmpty()) {
      dimension = 0;
      node = Optional.empty();
      left = Optional.empty();
      right = Optional.empty();
    } else {
      ArrayList<Coordinate> coords = coordinates;
      coords.sort(Comparator.comparingDouble(c -> c.getCoordinate(layer)));
      int center = coords.size() / 2;
      Coordinate median = coords.get(center);
      node = Optional.of(median);
      dimension = median.getDimension();
      Stream<Coordinate> leftStream = coords.stream()
          .filter(s -> s.getCoordinate(layer) < median.getCoordinate(layer));
      ArrayList<Coordinate> leftList =
          new ArrayList<>(Arrays.asList(leftStream.toArray(Coordinate[]::new)));
      Stream<Coordinate> rightStream = coords.stream().filter(s -> !((Star) s).equals(median))
          .filter(s -> s.getCoordinate(layer) >= median.getCoordinate(layer));
      ArrayList<Coordinate> rightList =
          new ArrayList<>(Arrays.asList(rightStream.toArray(Coordinate[]::new)));
      if (leftList.isEmpty()) {
        left = Optional.empty();
      } else {
        left = Optional.of(new KDTree(leftList, layer++));
      }
      if (rightList.isEmpty()) {
        right = Optional.empty();
      } else {
        right = Optional.of(new KDTree(rightList, layer++));
      }
    }
  }

  /**
   * Creates a new KDTree of the given Coordinates with a specified root layer.
   * Used primarily to recur from the above constructor.
   * @param coordinates ArrayList of Coordinates
   * @param l layer at which to start construction of KDTree
   */
  public KDTree(ArrayList<Coordinate> coordinates, int l) {
    layer = l;
    dimension = coordinates.get(0).getDimension();
    int index = layer % dimension;
    ArrayList<Coordinate> coords = coordinates;
    coords.sort(Comparator.comparingDouble(c -> c.getCoordinate(index)));
    int center = coords.size() / 2;
    Coordinate median = coords.get(center);
    node = Optional.of(median);
    ArrayList<Coordinate> leftList = new ArrayList<>(Arrays.asList(
        coords.stream().filter(s -> s.getCoordinate(index) < median.getCoordinate(index))
            .toArray(Coordinate[]::new)));
    ArrayList<Coordinate> rightList = new ArrayList<>(Arrays.asList(
        coords.stream().filter(s -> s.getCoordinate(index) >= median.getCoordinate(index))
            .filter(s -> !s.equals(median)).toArray(Coordinate[]::new)));
    if (leftList.isEmpty()) {
      left = Optional.empty();
    } else {
      left = Optional.of(new KDTree(leftList, layer++));
    }
    if (rightList.isEmpty()) {
      right = Optional.empty();
    } else {
      right = Optional.of(new KDTree(rightList, layer++));
    }
  }

  /**
   * Inserts the given Coordinate into the KDTree.
   * @param coordinate point to be inserted
   */
  public void insert(Coordinate coordinate) {
    if (this.node.isEmpty()) {
      this.node = Optional.of(coordinate);
    } else {
      if ((double) coordinate.getCoordinate(0)
          < (double) this.node.get().getCoordinate(0)) {
        if (this.left.isEmpty()) {
          this.left = Optional.of(new KDTree(coordinate, 1));
        } else {
          this.left.get().insert(coordinate, 1);
        }
      } else {
        if (this.right.isEmpty()) {
          this.right = Optional.of(new KDTree(coordinate, 1));
        } else {
          this.right.get().insert(coordinate, 1);
        }
      }
    }
  }

  /**
   * Inserts the given Coordinate into the KDTree at the given layer.
   * Used primarily to recur from the above insert method.
   * @param coordinate point to be inserted
   * @param l layer at which to insert
   */
  public void insert(Coordinate coordinate, int l) {
    int index = this.layer % this.dimension;
    if (this.node.isEmpty()) {
      this.node = Optional.of(coordinate);
    } else {
      if (coordinate.getCoordinate(index) < this.node.get().getCoordinate(index)) {
        if (this.left.isEmpty()) {
          this.left = Optional.of(new KDTree(coordinate, l++));
        } else {
          this.left.get().insert(coordinate, l++);
        }
      } else {
        if (this.right.isEmpty()) {
          this.right = Optional.of(new KDTree(coordinate, l++));
        } else {
          this.right.get().insert(coordinate, l++);
        }
      }
    }
  }
}
