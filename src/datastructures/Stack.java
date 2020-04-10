package datastructures;

public class Stack<T> implements StackOperation<T> {

    private Node<T> start;

    @Override
    public void push(T item) {
        if (start == null) {
            start = new Node(item, null);
        } else {
            Node<T> tmp = new Node(item, start);
            start = tmp;
        }
    }

    @Override
    public T pop() {
        T tmp = start.data;
        start = start.next;
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }
}

interface StackOperation<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
}
