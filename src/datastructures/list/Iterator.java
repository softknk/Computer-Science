package datastructures.list;

public class Iterator<T> {

    private Node<T> current;

    public Iterator(Node<T> start) {
        current = start;
    }

    public boolean hasNext() {
        return current != null;
    }

    public T next() {
        T tmp = current.data;
        current = current.next;
        return tmp;
    }
}
