/**
 * Created by ryan on 9/21/16.
 */
public class MazeSquare {

    // set the default wall state to no walls.
    private boolean[] wall = {false, false, false, false};
    private boolean visited;
    private boolean abandoned;
    private Coordinate myPosition;

    // new squares are built without walls
    public MazeSquare(Coordinate p) {
        myPosition = p;
        this.clear();
    }

    // this might be nice to have....
    public MazeSquare(Coordinate p, boolean[] wallSet) {
        this(p);
        for (int i = 0; i < wallSet.length && i < wall.length; i++) {
            wall[i] = wallSet[i];
        }
    }

    public void toggleWall(Direction dir) {
        if (this.wall[dir.value()] == false) {
            this.wall[dir.value()] = true;
        } else {
            this.wall[dir.value()] = false;
        }
    }

    public boolean getWall(Direction dir) {
        return this.wall[dir.value()];
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isAbandoned() {
        return this.abandoned;
    }

    public void abandon() {
        this.abandoned = true;
    }

    public void clear() {
        this.visited = false;
        this.abandoned = false;
    }

    public Coordinate getPosition() {
        return myPosition;
    }

    public boolean equals(MazeSquare other) {
        if (this.myPosition == other.myPosition) {
            return true;
        }
        return false;
    }
}