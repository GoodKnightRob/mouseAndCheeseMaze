import java.util.*;
import java.io.*;
// Represent a 2D maze with a single mouse and multipel cheese
// locations. Positions on the maze can be flagged or unflagged to
// help keep track of search paths.
public class Maze {
 protected int rows, columns;// how big the maze is
 protected String [][] maze;
 private boolean [][] flaggedMaze;
  protected List <Coord> CheeseLocationsList= new ArrayList();//locations of the cheese
  protected Coord [] cheeseLocations;
  protected Coord [] MouseLocation;//mouse location
  protected  List <Coord>SpacesList =new ArrayList ();//location of spaces
  protected List <Coord> FlaggedList =new ArrayList ();
  protected Coord [] spaceLocations;
  protected Coord [] wallLocations;
  protected Coord [] flagLocations;
 //protected char mazze[][];
  // enum Spot{ 
  //   Mouse('M'), Wall('|'), Space(' '), 
  //   Dot('.'), Cheese('C');
  //   char c;   // character representation
     // Make a spot
  //   Spot(char c){
  //     this.c=c;
    // }
   //}

  // Construct a Maze from the contents of a file.  On construction,
  // all flags are cleared.  The file format should be similar to the
  // following.
  // 
  // ||||||||
  // |     M|
  // | | ||||
  // | |    |
  // | ||||||
  // |      |
  // |||||| |
  // | ||   |
  // |C|  |C|
  // ||||||||
  public Maze(File f) throws Exception{
     int numofCheese=0;
     int numofSpaces=0;
     int numofWalls=0;
     Scanner file = new Scanner(f);
     while(file.hasNext()){// goes through each line
     String line = file.nextLine();
       this.columns=line.length();     
       for(int i=0;i<line.length();i++){// itterate to check for special characters such as M C or | as well as space
         if (line.charAt(i)=='C'){
           numofCheese++;// save the number of cheese
         }
         if (line.charAt(i)=='M'){
         this.MouseLocation=new Coord[1];
          Coord newMouseLocation=new Coord(rows,i);
          this.MouseLocation[0]=newMouseLocation;//create a location for the mouse
         }
         if (line.charAt(i)==' '){
          numofSpaces++;
         }
         if (line.charAt(i)=='|'){
          numofWalls++;
         }
       } 
       this.rows++;
     }
    // file.close();
     this.cheeseLocations=new Coord[numofCheese];/// create arrays that will contain the location of the values
     this.wallLocations=new Coord[numofWalls];
     this.spaceLocations=new Coord[numofSpaces];
     int rows2=0;
     int cheeseCount=0;
     int spaceCount=0;
     int wallCount=0;
     Scanner file2= new Scanner(f);
     while(file2.hasNext()){
       String line=file2.nextLine();
       for(int i=0;i<line.length();i++){// itterate through string one more time to save the values
         if (line.charAt(i)=='C'){
           int r=rows2;
         //  int c=i;
           Coord newCheeseLocation=new Coord(r,i);
           this.cheeseLocations[cheeseCount]=newCheeseLocation;//adds new cheese
           cheeseCount++;
         }
         if (line.charAt(i)==' '){
            int r=rows2;
            Coord newSpaceLocation=new Coord(r,i);
            this.spaceLocations[spaceCount]=newSpaceLocation;// adds new space
            spaceCount++;
          }
         if (line.charAt(i)=='|'){
            int r=rows2;
            Coord newWallLocation=new Coord(r,i);
            this.wallLocations[wallCount]=newWallLocation;//adds a new wall
            wallCount++;
          }
       }
       rows2++;
     }
    // file2.close();
     
    maze= new String [this.rows][this.columns];
    flaggedMaze= new boolean[this.rows][this.columns];
  }

  
  
  // Construct a maze form the given string. The format of the string
  // identical to the format of the file above.
  public Maze(String s) throws Exception{ 
     int numofCheese=0;
     int numofSpaces=0;
     int numofWalls=0;
     String [] lines=s.split("\n");// splits the string into an array of strings
     for(int i=0;i<lines.length;i++){//itterates through each line
      String line =lines[i];
        this.columns=(line.length());
        
        for(int i2=0;i2<line.length();i2++){//itterates through each character in the line
          if (line.charAt(i2)=='C'){// keeps track on the number of cheese
            numofCheese++;
          }
          if (line.charAt(i2)=='M'){
           this.MouseLocation=new Coord[1];
           Coord newMouseLocation=new Coord(rows,i2);
           this.MouseLocation[0]=newMouseLocation;// creates a new mouse
          }
          if (line.charAt(i2)==' '){
           numofSpaces++;// keeps track on the number of spaces
          }
          if (line.charAt(i2)=='|'){
           numofWalls++;
          }
        }
        this.rows++;
     }
     this.cheeseLocations=new Coord[numofCheese];//creates new arrays for each of the values
     this.wallLocations=new Coord[numofWalls];
     this.spaceLocations=new Coord[numofSpaces];
     int rows2=0;
     int cheeseCount=0;
     int spaceCount=0;
     int wallCount=0;
     for(int i=0;i<lines.length;i++){//reitterates to now fill in the location of the values
     String line =lines[i];
       for(int i2=0;i2<line.length();i2++){
         if (line.charAt(i2)=='C'){//adds a new location of cheese
           int r=rows2;
           int c=i2;
           Coord newCheeseLocation=new Coord(r,c);
           this.cheeseLocations[cheeseCount]=newCheeseLocation;
           cheeseCount++;
         }
         if (line.charAt(i2)==' '){//adds new location of space
            int r=rows2;
            Coord newSpaceLocation=new Coord(r,i2);
            this.spaceLocations[spaceCount]=newSpaceLocation;
            spaceCount++;
          }
         if (line.charAt(i2)=='|'){
            int r=rows2;
            Coord newWallLocation=new Coord(r,i2);
            this.wallLocations[wallCount]=newWallLocation;
            wallCount++;
          }
       }
       rows2++;
     }
      maze= new String [this.rows][this.columns];
      flaggedMaze= new boolean[this.rows][this.columns];
  }

  // Return how many rows are in the maze.
  public int getRows(){
   return rows;
  }

  // Return how many columns are in the maze.
  public int getCols(){
   return columns;
  }

  // Return the initial location of the mouse in the maze as a
  // coordinate (row,col).  Assume there is exactly one mouse. Return
  // null if no mouse is present.
  public Coord getMouseLocation(){
   Coord m=MouseLocation[0];
   return m;
  }

  // Return a list of coordinates of all the chesse locations in the
  // maze. If no cheese is present, return an empty list.
  public List<Coord> getCheeseLocations(){
   for (int i=0; i<cheeseLocations.length;i++){
    Coord c=cheeseLocations[i];
    CheeseLocationsList.add(c);
   }
   return CheeseLocationsList;
  }

  // Return true if the given coordinate is in bounds for the maze and
  // false otherwise.
  public boolean inBounds(Coord c){
   boolean checker=false;
   if(c.row<=this.rows && c.row>=0){
    if(c.col<=this.columns && c.col>=0)
     checker=true;
   }
   return checker;
  }

  // Return true if the given coordinate is open space for the mouse
  // and false otherwise. The status of the flag (set or clear) at the
  // given location should not affect whether true/false is returned.
  public boolean isSpace(Coord c){
   boolean checker=false;
   for (int i=0; i<spaceLocations.length;i++){
    Coord s=spaceLocations[i];
    if(c.row==s.row &&c.col==s.col)
    checker=true;
   }
   return checker;
  }

  // Return true if the given location refers to a wall and false
  // otherwise.
  public boolean isWall(Coord c){
   boolean checker=false;
   for (int i=0; i<wallLocations.length;i++){
    Coord w=wallLocations[i];
    if(c.row==w.row &&c.col==w.col)
    checker=true;
   }
   return checker;
  }

  // Return true if the given location refers to the starting location
  // of the mouse and false otherwise.
  public boolean isMouse(Coord c){
   boolean checker=false;
   for (int i=0; i<MouseLocation.length;i++){
    Coord m=MouseLocation[i];
    if(c.row==m.row &&c.col==m.col)
    checker=true;
   }
   return checker;
  }

  // Return true if the given location refers to cheese and false
  // otherwise.
  public boolean isCheese(Coord c){
   boolean checker=false;
   for (int i=0; i<cheeseLocations.length;i++){
    Coord che=cheeseLocations[i];
    if(c.row==che.row &&c.col==che.col)
    checker=true;
   }
   return checker;
  }

  // Return true if the given location has its flag set and false
  // otherwise.
  public boolean isFlagged(Coord c){
    if(inBounds(c)){
   if(flaggedMaze[c.row][c.col]){ 
    return true;
   }
  }
  return false;
  }

  // Set the flag at the given location to true.  
  public void setFlag(Coord c){
   if(inBounds(c)){
   flaggedMaze[c.row][c.col]=true;
  }
  }

  // Set the flag at the given location to false.  
  public void clearFlag(Coord c){
  if(inBounds(c)){
   flaggedMaze[c.row][c.col]=false;
  }
  }

  // Clear all flags in the maze
  public void clearFlags(){
  Coord c;
  for(int i=0; i< rows; i++){
   for(int j=0; j< columns; j++){
    if(flaggedMaze[i][j]){
     maze[i][j]=" ";
    }
    flaggedMaze[i][j]=false;
   }
  }
  }

  // Starting from the parameter start, flag all positions along the
  // path given by the directions list.
  public void flagPath(List<Direction> path, Coord start){
    Direction dir;
  Coord cod;
  for(int i=0; i<path.size(); i++){
   dir = path.get(i);
   cod = dir.getChange();
   start = start.add(start, cod);
   setFlag(start);
  }
  }

  // Create a display string for the maze. This is identical to the
  // input format of the maze except that any open spaces which are
  // flagged should display as periods (.) rather than spaces ( ). For
  // example, the following maze has some open spaces in a path
  // flagged which guide the mouse to the cheese.
  // 
  // ||||||||
  // |.....M|
  // |.| ||||
  // |.|    |
  // |.||||||
  // |......|
  // ||||||.|
  // | ||  .|
  // |C|  |C|
  // ||||||||
  public String toString(){
   for (int i=0; i<rows; i++){
    for (int j=0; j<columns; j++){
     maze[i][j]="|";
    }
   }
   for (int i=0; i<cheeseLocations.length;i++){
    Coord c=cheeseLocations[i];
    maze[c.row][c.col]="C";
   }
   for (int i=0; i<MouseLocation.length;i++){
    Coord m=MouseLocation[i];
    maze[m.row][m.col]="M";
   }
   for (int i=0; i<spaceLocations.length;i++){
    Coord s=spaceLocations[i];
    if(flaggedMaze[s.row][s.col]){// if the location is a space and flagged its replaced with a "."
      maze[s.row][s.col]=".";
    }
    else
      maze[s.row][s.col]=" ";
   }
   StringBuilder sb = new StringBuilder();
     for(int i=0; i<rows; i++){
       for(int j=0; j<columns; j++){
  sb.append(maze[i][j]);
       }
       sb.append("\n");
     }
     return sb.toString();
  }

}