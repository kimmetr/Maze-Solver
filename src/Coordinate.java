/**
 * Created by ryan on 9/18/16.
 */
public class Coordinate {
    private int col;
    private int row;

    public Coordinate() {
        col = 0;
        row = 0;
    }

    public Coordinate(int x, int y) {
        col = x;
        row = y;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Col: " + col + " " + "Row: " + row;
    }
}
