/**
 * Created by ryan on 9/21/16.
 */
public enum Direction {
    NORTH(0),
    SOUTH(1),
    EAST(2),
    WEST(3);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    int value() {
        return this.value;
    }
}
