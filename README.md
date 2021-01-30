# README

## Stars

KNOWN BUGS:
- N/A

DESIGN DETAILS SPECIFIC TO MY CODE:
- Commands for REPL each get their own class, implementing REPLCommand interface to ensure functionality
- Data parsed from star .csv filed stored in Vector of Star objects
- NaiveRadius and NaiveNeighbors return data in a String Array, which is then printed out
- NaiveRadius output found by converting starData to a stream and filtering/mapping as required
- NaiveNeighbors output found by storing starData in a TreeMap. Each key, representing a distance from the input center
  point, corresponds to an ArrayList of Stars at that corresponding distance. Keys are iterated through in order,
  utilizing the sorted nature of TreeMaps. As necessary, randomization is implemented by shuffling the fringe ArrayList.
- My CSVParser stores the header of its related file; throws error if any given line does not match format of header.

RUNTIME/SPACE OPTIMIZATIONS I MADE BEYOND MINIMUM REQUIREMENTS:
- N/A

HOW TO RUN TESTS WRITTEN BY HAND:
- JUnit tests run with 'mvn package'
- System tests run with 'python cs32-test tests/student/stars/stars1/*.test'

HOW TO BUILD/RUN MY PROGRAM:
- Standard 'mvn package' followed by './run'

DESIGN QUESTION:
- For each of the 10+ commands, I would create a class that implements the REPLCommand interface.
  Then, in my Main method, I would add corresponding REPL.add() commands that passes in an object of the command classes.
  All the interior logic is generalized.

KNOWN CHECKSTYLE ERRORS:
- N/A
