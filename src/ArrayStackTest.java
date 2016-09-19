/**
 * Created by ryan on 9/18/16.
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        Stack<Coordinate> stack = new ArrayStack<>();
        stack.push(new Coordinate(5, 6));
        stack.push(new Coordinate(3, 7));
        stack.push(new Coordinate(1, 4));
        stack.push(new Coordinate(2, 10));
        stack.showAll();
        System.out.println("----------");
        System.out.println("Current Pop: " + stack.pop().toString());
        System.out.println("----------");
        stack.showAll();
        System.out.println("----------");
        stack.push(new Coordinate(6, 9));
        stack.push(new Coordinate(5, 5));
        stack.showAll();
        System.out.println("----------");
        System.out.println("Current Pop: " + stack.pop().toString());
        System.out.println("----------");
        stack.showAll();
        System.out.println("----------");
    }
}
