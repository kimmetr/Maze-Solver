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
        throw new RuntimeException("Unimplmemented.");
    }

    public boolean getWall(Direction dir) {
        throw new RuntimeException("Unimplemented.");
    }

    public boolean isVisited() {
        throw new RuntimeException("Unimplmemented.");
    }

    public void visit() {
        throw new RuntimeException("Unimplmemented.");
    }

    public boolean isAbandoned() {
        throw new RuntimeException("Unimplmemented.");
    }

    public void abandon() {
        throw new RuntimeException("Unimplmemented.");
    }

    public void clear() {
        throw new RuntimeException("Unimplmemented.");
    }

    public Coordinate getPosition() {
        throw new RuntimeException("Unimplmemented.");
    }

    public boolean equals(MazeSquare other) {
        throw new RuntimeException("Unimplmemented.");
    }
}