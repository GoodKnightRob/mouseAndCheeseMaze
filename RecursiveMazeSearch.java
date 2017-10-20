import java.util.*;
// Search a maze for a path from mouse to cheese using recursion.
// CONSTRAINT: You may not use ArrayLists to implement this. Use the
// AStack class instead.  You may import java.util.List to match the
// expected return types.
public class RecursiveMazeSearch extends MazeSearch{

  // Constructor to initialize any state required to search mazes
  public RecursiveMazeSearch(){}

  // Search for cheese in the given maze. Return a path of Directions
  // from mouse to cheese.  If no such path exists, return
  // null. Employ a recursive helper function to assist with the
  // search.
  public List<Direction> searchForCheese(Maze maze){
    if (maze != null) {
      Coord mouse= maze.getMouseLocation();
      AStack <Direction>currentPath = new AStack();
      recursiveSearch(maze,currentPath,mouse);
      //return currentPath.toList();
      return null;
        }
  return null;
  }

  // Suggested recursive helper function. Takes the maze, a current
  // path, and a current position and continues the search. Returns
  // true if a path to cheese is located and false if the present path
  // is a dead end.
  protected boolean recursiveSearch(Maze maze, AStack<Direction> path, Coord curPos){
  if (path == null) { return false;}
        Coord coord = maze.getMouseLocation();
        if (!maze.inBounds(curPos)) {
            return false;
        }
        if (!maze.isMouse(coord)) {
            return false;
        }
        return false;
  }
}