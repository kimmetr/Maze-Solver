/**
 * Created by ryan on 9/21/16.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private MazeSquare[][] maze;
    private Coordinate startPos;
    private Coordinate finishPos;
    private int numRows;
    private int numCols;
    private boolean[] fullWallSet = {true, true, true, true};

    public Maze(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        maze = new MazeSquare[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                maze[i][j] = new MazeSquare(new Coordinate(i, j), fullWallSet);
            }
        }
        this.genMaze();
        this.clear();
    }

    public static void main(String[] args) {
        Scanner fromUser = new Scanner(System.in);
        System.out.print("rows? ");
        int r = fromUser.nextInt();
        System.out.print("cols? ");
        int c = fromUser.nextInt();

        Maze aMaze = new Maze(r, c);
        System.out.println(aMaze);
    }

    public MazeSquare squareAt(Coordinate p) {
        return maze[p.getRow()][p.getCol()];
    }


    public void visitPos(Coordinate p) {
        squareAt(p).visit();
    }

    public void abandonPos(Coordinate p) {
        squareAt(p).abandon();
    }

    public Coordinate getStart() {
        return startPos;
    }

    public Coordinate getFinish() {
        return finishPos;
    }

    public Coordinate northOf(Coordinate p) {
        return new Coordinate(p.getRow() + -1, p.getCol());
    }

    public Coordinate eastOf(Coordinate p) {
        return new Coordinate(p.getRow(), p.getCol() + 1);
    }

    public Coordinate southOf(Coordinate p) {
        return new Coordinate(p.getRow() + 1, p.getCol());
    }

    public Coordinate westOf(Coordinate p) {
        return new Coordinate(p.getRow(), p.getCol() - 1);
    }

    public boolean movePossible(Coordinate from, Coordinate to) {
        if (from.getCol() >= numCols || from.getCol() < 0 || from.getRow() >= numRows || from.getRow() < 0 || to.getCol() >= numCols || to.getCol() < 0 || to.getRow() >= numRows || to.getRow() < 0) {
            return false; //returns false if either coordinate is not on maze
        }
        if (squareAt(to).isVisited() || squareAt(to).isAbandoned()) {
            return false; //return false if the to coordinate has already been visited or abandoned
        }
        if (squareAt(from).getWall(directionOfWall(from, to)) == true || squareAt(to).getWall(directionOfWall(to, from)) == true) {
            return false; //return false if there is a wall in between the two coordinates
        }
        return true; //if none of the negative conditions have been met the move is possible, return true
    }

    public Direction directionOfWall(Coordinate from, Coordinate to) {
        Direction adjacentWall;
        if (westOf(from).equals(to)) { //if from is to the right of to
            adjacentWall = Direction.WEST;
        } else if (eastOf(from).equals(to)) { //if from is to the left of to
            adjacentWall = Direction.EAST;
        } else if (northOf(from).equals(to)) { //if from is below to
            adjacentWall = Direction.NORTH;
        } else { //if from is above to
            adjacentWall = Direction.SOUTH;
        }
        return adjacentWall;
    }

    private void genMaze() {
        Random rnd = new Random();
        startPos = new Coordinate(rnd.nextInt(numRows), 0); //generate start position
        finishPos = new Coordinate(rnd.nextInt(numRows), numCols - 1); //generate finish position
        squareAt(startPos).toggleWall(Direction.WEST); //removes start wall
        squareAt(finishPos).toggleWall(Direction.EAST); //removes finish wall
        Stack<Coordinate> current = new ArrayStack<>();
        current.push(null);
        current.push(startPos);
        while (current.top() != null) {
            ArrayList<Coordinate> unvisitedNeighbors = unvisitedNeighbors(current.top()); //initiates array list of unvisited neighbors
            if (unvisitedNeighbors.isEmpty()) {
                current.pop(); //pops off the top of the stack if the there is no unvisited neighbors
            } else {
                Coordinate next = unvisitedNeighbors.get(rnd.nextInt(unvisitedNeighbors.size())); //picks a random unvisited neighbor
                squareAt(current.top()).toggleWall(directionOfWall(current.top(), next)); //removes wall between current and next
                squareAt(next).toggleWall(directionOfWall(next, current.top())); //removes wall between next and current
                visitPos(next);
                current.push(next);
            }
        }
    }

    private void clear() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                maze[i][j].clear();
            }
        }
    }

    // I'm going to leave this in here since we haven't
    // discussed the use of the ArrayList collection from the java library
    private ArrayList<Coordinate> unvisitedNeighbors(Coordinate p) {
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();

        int r = p.getRow();
        int c = p.getCol();

        if (r > 0 && !maze[r - 1][c].isVisited())
            list.add(new Coordinate(r - 1, c));
        if (r < numRows - 1 && !maze[r + 1][c].isVisited())
            list.add(new Coordinate(r + 1, c));
        if (c > 0 && !maze[r][c - 1].isVisited())
            list.add(new Coordinate(r, c - 1));
        if (c < numCols - 1 && !maze[r][c + 1].isVisited())
            list.add(new Coordinate(r, c + 1));

        return list;
    }

    // this will be made use of in the maze solver but is not useful here.
    // Just checks if p is within the bounds of the maze.
    private boolean validPos(Coordinate p) {
        return ((p.getRow() < numRows) && (p.getRow() >= 0) && (p.getCol() < numCols) && (p.getCol() >= 0));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        // output the top
        for (int i = 0; i < numCols; i++)
            buf.append("__");
        buf.append("_\n");

        // output the rows
        for (int i = 0; i < numRows; i++) {
            if (i != startPos.getRow()) {
                buf.append("|");
            } else {
                buf.append(" ");
            }

            for (int j = 0; j < numCols; j++) {
                if (maze[i][j].getWall(Direction.SOUTH)) {
                    buf.append("_");
                } else {
                    buf.append(" ");
                }

                if (maze[i][j].getWall(Direction.EAST)) {
                    buf.append("|");
                } else {
                    if (j + 1 < numCols) {
                        if (maze[i][j + 1].getWall(Direction.SOUTH)) {
                            buf.append("_");
                        } else {
                            buf.append(".");
                        }
                    }
                }
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
