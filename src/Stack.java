/**
 * Created by ryan on 9/18/16.
 */
public interface Stack<E> {
    public void push(E elt);

    public E top();

    public E pop();

    public int size();

    public void showAll();

}
