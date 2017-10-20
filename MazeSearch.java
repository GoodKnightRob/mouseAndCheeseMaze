import java.util.List;

// Search a maze for a path from mouse to cheese. The exact search
// method is left to child classes to implement.
public abstract class MazeSearch {

// Constructor to initialize any state required to search mazes
public MazeSearch(){

}

// Return true if the path given leads the mouse in maze to cheese.
// Return false otherwise (walking through walls, not ending on
// cheese). Throw an exception if the path would lead the mouse out
// of bounds in the maze.
public boolean isPathToCheese(Maze maze, List<Direction> path) throws Exception{
if (path == null) { return false;}
        Coord coord = maze.getMouseLocation();
        if (!maze.inBounds(coord)) {
            return false;
        }
        if (!maze.isMouse(coord)) {
            return false;
        }
        Coord coord2 = coord;
        for (Direction direction : path) {
            if (!maze.inBounds(coord2 = Coord.add((Coord)coord2, (Coord)direction.getChange()))) {
                return false;
            }
            if (!maze.isWall(coord2)) continue;
            return false;
        }
        return maze.isCheese(coord2);
    }
  /*
  Direction dir;
Coord nextCord;
//Coord cord = path.get(0).getChange();
Coord cord = maze.getMouseLocation();
if(path.size()==0){
return false;
}

for(int i=0; i<path.size(); i++){
dir = path.get(i);
nextCord = dir.getChange();

cord = cord.add(cord, nextCord);
System.out.println(cord);
if(maze.isWall(cord)){
return false;
}
if(!maze.inBounds(cord)){
throw new Exception();
}
}
if(maze.isCheese(cord)){
return true;
}
return false;
}
*/
// Overriden by children: Search for cheese in the given maze. If no
// path to any cheese exists, return null.
public abstract List<Direction> searchForCheese(Maze maze);



}