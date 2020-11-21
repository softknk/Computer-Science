package datastructures;

import datastructures.list.Node;

import java.lang.reflect.Array;

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

class ArrayStack<T> implements StackOperation<T> {

    private T[] nodes;
    private final Class<T> _class;

    public ArrayStack(Class<T> _class) {
        this._class = _class;
        nodes = createArray(_class, 0);
    }

    @Override
    public void push(T item) {
        increaseArrayByOne();
        nodes[nodes.length - 1] = item;
    }

    @Override
    public T pop() {
        T tmp = nodes[nodes.length - 1];
        decreaseArrayByOne();
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return nodes.length == 0;
    }

    private void increaseArrayByOne() {
        T[] tmp = createArray(_class, nodes.length + 1);
        for (int i = 0; i < nodes.length; i++)
            tmp[i] = nodes[i];
        nodes = tmp;
    }

    private void decreaseArrayByOne() {
        T[] tmp = createArray(_class, nodes.length - 1);
        for (int i = 0; i < nodes.length - 1; i++)
            tmp[i] = nodes[i];
        nodes = tmp;
    }

    private T[] createArray(Class<T> t, int length) {
        return (T[]) Array.newInstance(t, length);
    }
}
