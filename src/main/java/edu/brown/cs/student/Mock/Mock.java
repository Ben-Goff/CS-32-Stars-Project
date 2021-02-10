package edu.brown.cs.student.Mock;

import edu.brown.cs.student.Constants;
import edu.brown.cs.student.stars.Star;
import edu.brown.cs.student.util.CSVParser;
import edu.brown.cs.student.util.REPLCommand;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Optional;

public class Mock implements REPLCommand {

  @Override
  public String name() {
    return "mock";
  }

  @Override
  public Star[] run(String argumentString) throws IOException {
    try {
      CSVParser mock = new CSVParser(argumentString.trim());
      if (!mock.getFirstLine()
          .equals("First Name,Last Name,Datetime,email,gender,address,Family Name (Chinese)")) {
        // Throw error if header is incorrect
        throw new RuntimeException("ERROR: Invalid File Header");
      }
      MockPerson.resetMockPeople();
      Optional<String[]> lineData = mock.parseLine();
      String[] person;
      while (lineData.isPresent()) {
        person = lineData.get();
        if (person.length != Constants.SEVEN) {
          throw new RuntimeException("Malformed line of data");
        }
        MockPerson.addMockPerson(new MockPerson((String) Array.get(person, 0),
            (String) Array.get(person, 1), (String) Array.get(person, 2),
            (String) Array.get(person, 3), (String) Array.get(person, 4),
            (String) Array.get(person, 5), (String) Array.get(person, 6)));
        lineData = mock.parseLine();
      }
      for (int i = 0; i < MockPerson.mockPeopleCount(); i++) {
        System.out.println(MockPerson.getMockPerson(i).toString());
      }
    } catch (FileNotFoundException e) {
      // Throw error if input file name does not exist
      System.out.println("ERROR: File not found");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new Star[0];
  }
}
