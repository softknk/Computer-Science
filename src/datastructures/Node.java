package datastructures;

/**
 * defines a Node for a simple Linked List
 * @param <T>
 */
public class Node<T> {

    public T data;
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
