package edu.brown.cs.student.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * CSVParser data structure for parsing comma separated files.
 */
public class CSVParser {

  private BufferedReader in;
  private String firstLine;
  private String[] firstLineParsed;
  private String delimiter = ",";

  /**
   * Returns first line (header) of CSVParser object.
   * @return String
   */
  public String getFirstLine() {
    return firstLine;
  }

  /**
   * Returns first line (header) of CSVParser object parsed.
   * @return String[]
   */
  public String[] getFirstLineParsed() {
    return firstLineParsed;
  }

  /**
   * Creates a CSVParser object with the inputted filename.
   * @param fileName
   */
  public CSVParser(String fileName) throws IOException {
    in = new BufferedReader(new FileReader(fileName));
    firstLine = in.readLine();
    firstLineParsed = firstLine.split(delimiter);
  }

  /**
   * Returns an Optional string array of the values in the next line of the CSVParser's file.
   * Empty Optional denotes EOF
   * @return Optional<String[]>
   */
  public Optional<String[]> parseLine() throws IOException {
    String nextLine = this.in.readLine();
    if (nextLine == null) {
      return Optional.empty();
    } else {
      String[] currentLine = nextLine.split(delimiter);
      if (currentLine.length != this.firstLineParsed.length) {
        // Throw error if any line in file doesn't match file header
        throw new RuntimeException("ERROR: invalid data; line does not match header");
      } else {
        return Optional.of(currentLine);
      }
    }
  }
}
