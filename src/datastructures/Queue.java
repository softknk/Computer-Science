package datastructures;

import datastructures.list.Node;

public class Queue<T> implements QueueOperations<T> {

    private Node<T> head, tail;

    @Override
    public void enqueue(T item) {
        if (head == null) {
            head = new Node<>(item, null);
            tail = head;
        } else {
            tail.next = new Node<>(item, null);
            tail = tail.next;
        }
    }

    @Override
    public T dequeue() {
        if (head != null) {
            T tmp = head.data;
            head = head.next;
            return tmp;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}

interface QueueOperations<T> {
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
}
