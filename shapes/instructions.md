# Shapes Task

Your task is to extend this shapes library with a class for a square pyramid, 
i.e. a pyramid with the base of a square. The new class should implement the interface `Shape3D` and have the following constructor: 
`SquarePyramid(double baseSideLength, double height)`.

In addition to the new class you should also extend the interface `Shape3D` with the 
method signature `double totalEdgeLength()` and provide an implementation for all implementing classes.
The method should return the total length of all edges making up the pyramid. 
For example: The `TriangularPyramid` has 6 edges, 
3 in the base and 3 leading to the top from the base (1 from each corner). 
The method should return the sum of the lengths of those 6 edges.

Any added classes should also have none default implementations of `hashCode`, `equals` and `toString`.

Feel free to modify the existing codebase in any way you want as long as you maintain existing functionality.

## Task checklist
* Added a `SquarePyramid` class.
* Added `double totalEdgeLength()`  to `Shape3D`.
* All classes have a none default implementations of `hashCode`, `equals` and `toString`.
* Program compiles.