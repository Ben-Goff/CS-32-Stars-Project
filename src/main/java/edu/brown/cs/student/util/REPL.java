package edu.brown.cs.student.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * REPL data structure for storing commands and parsing their parts.
 */
public class REPL {

  private BufferedReader is;
  private String command;
  private String inputLine;
  private String argumentString;
  private ArrayList<REPLCommand> commands;

  /**
   * Creates an empty REPL object.
   */
  public REPL() {
    is = new BufferedReader(new InputStreamReader(System.in));
    command = null;
    argumentString = null;
    inputLine = null;
    commands = new ArrayList<>();
  }

  /**
   * Adds a new REPLCommand to the REPL.
   * @param c
   */
  public void addCommand(REPLCommand c) {
    this.commands.add(c);
  }

  /**
   * Adds a new REPLCommand to the REPL.
   * @return ArrayList<REPLCommand>
   */
  public ArrayList<REPLCommand> getCommands() {
    return commands; }

  /**
   * Stars up the REPL with the previously added commands.
   */
  public void run() throws IOException {
    inputLine = is.readLine();
    boolean found = false;
    while (!inputLine.equals("")) {
      if (!inputLine.contains(" ")) {
        System.out.println("ERROR: Invalid Command - No Arguments");
      } else {
        int firstSpace = inputLine.indexOf(" ");
        command = inputLine.substring(0, firstSpace);
        argumentString = inputLine.substring(firstSpace);
        found = false;
        for (int i = 0; i < this.commands.size(); i++) {
          if (this.commands.get(i).name().equals(command)) {
            found = true;
            this.commands.get(i).run(argumentString);
          }
        }
        if (!found) {
          System.out.println("ERROR: unrecognized command");
        }
      }
      inputLine = is.readLine();
      if (inputLine == null) {
        inputLine = "";
      }
    }
  }
}
