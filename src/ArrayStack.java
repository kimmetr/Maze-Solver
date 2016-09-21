/**
 * Created by ryan on 9/18/16.
 */
import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {
    private E[] data;
    private int top;
    private int size;

    public ArrayStack() {
        top = -1;
        size = 100;
        data = (E[]) new Object[size];
    }

    public ArrayStack(int n) {
        top = -1;
        size = n;
        data = (E[]) new Object[size];
    }

    @Override
    public E top() {
        return data[top];
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public void push(E elt) {
        if (top == size - 1) {
            size = size * 2;
            data = Arrays.copyOf(data, size);
        } else {
            top = top + 1;
            data[top] = elt;
        }
    }

    @Override
    public E pop() {
        int topLocation;
        if (top == -1) {
            return null; // underflow error
        } else {
            topLocation = top;
            top = top - 1;
            return data[topLocation];
        }
    }

    @Override
    public void showAll() {
        for (int i = top; i >= 0; i--) {
            System.out.println(data[i].toString());
        }
    }
}
