# README

## Stars

KNOWN BUGS:
- My property based testing sometimes fails. I have not been able to determine if I am 
  not accounting for randomness properly or if I am messing up the comparison between the two sets of results. My code
  is still in the test files and StarInput class, but the tests have been commented out for the time being.

DESIGN DETAILS SPECIFIC TO MY CODE:
- Commands for REPL each get their own class, implementing REPLCommand interface to ensure functionality
- Data parsed from star .csv filed stored in Vector of Star objects
- KDTrees are build based off of the Vector of parsed Stars
- NaiveRadius and NaiveNeighbors return data in a String Array, which is then printed out
- NaiveRadius output found by converting starData to a stream and filtering/mapping as required
- NaiveNeighbors output found by storing starData in a TreeMap. Each key, representing a distance from the input center
  point, corresponds to an ArrayList of Stars at that corresponding distance. Keys are iterated through in order,
  utilizing the sorted nature of TreeMaps. As necessary, randomization is implemented by shuffling the fringe ArrayList.
- My CSVParser stores the header of its related file; throws error if any given line does not match format of header.
- KDTree values are defined as Coordinates, an interface that ensures necessary functionality to be used in KDTrees
- KDTrees are defined recursively, with each KDTree containing a node (Coordinate), a left KDTree, and a right KDTree.
  Each of these three are Optionals to (1) allow the constructor to conclude, and (2) aid in proper handling of the
  bottoms of KDTrees during processing.
- KDTrees also contain a dimension (constant throughout), and a layer (how deep into the tree a node is). These are used
  to determine through which dimension the search area is being sliced.
- Neighbor commands that refer to a star by name are processed by calling the Neighbor command that refers to a
  coordinate with one more star, and then removing the named star
- The two Radius commands are also processed with combined code, but the named star is removed during
  processing instead of after the fast, given the lack of a limit on returned star count

RUNTIME/SPACE OPTIMIZATIONS I MADE BEYOND MINIMUM REQUIREMENTS:
- N/A

HOW TO RUN TESTS WRITTEN BY HAND:
- JUnit tests run with 'mvn package'
- Part 1 system tests run with 'python cs32-test tests/student/stars/stars1/*.test'
- Part 2 system tests run with 'python cs32-test tests/student/stars/stars2/*.test'

HOW TO BUILD/RUN MY PROGRAM:
- Standard 'mvn package' followed by './run'

DESIGN QUESTION (PART 1):
- For each of the 10+ commands, I would create a class that implements the REPLCommand interface.
  Then, in my Main method, I would add corresponding REPL.add() commands that passes in an object of the command classes.
  All the interior logic is generalized.
  
DESIGN QUESTIONS (PART 2):
- The central issue with finding points close to each other on the earth's surface using my k-d tree implementation 
  is earth's spherical nature. As such, it can't easily be projected onto an undistorted plane. This makes it difficult
  to (1) assign points to be stored in the k-d tree to a point on the earth's surface, and (2) calculate the distance
  between two given points. For relative distance measurements, treating the earth as a three-dimensional space and
  assigning point as such may work, but for absolute distance measurements along the surface, more sophisticated
  means would be necessary.
- I would need to add a fair amount of code. add() was already implemented for testing purposes. size(), isEmpty(),
  contains(), and clear() could be implemented quite easily by traversing down the tree and counting as it goes.
  toArray() would be slightly more difficult, needing to process each piece of data in the tree. The most difficult
  would be remove(), as it would require changing the structure of the tree when removing data with children, needing
  to rebuild the trees at those nodes. toArray() would be able to help with this substantially.

KNOWN CHECKSTYLE ERRORS:
- N/A

OUTSIDE RESOURCES USED:
- I got some ideas for different HTML and CSS designs from https://www.w3schools.com/
