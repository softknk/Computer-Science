package datastructures.list;

public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public void add(T item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node<T> tmp = head;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node(item, null);
        }
        size++;
    }

    public T get(int index) {
        if (head != null && index < size) {
            Node<T> tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.data;
        }
        return null;
    }

    public boolean remove(T item) {
        if (head != null) {
            Node<T> tmp = head;
            if (head.data.equals(item)) {
                head = head.next;
                size--;
                return true;
            }
            while (tmp.next != null) {
                if (tmp.next.data.equals(item)) {
                    tmp.next = tmp.next.next;
                    size--;
                    return true;
                }
                tmp = tmp.next;
            }
        }
        return false;
    }

    public boolean removeAt(int index) {
        if (head != null && index < size) {
            Node<T> tmp = head;
            if (index == 0) {
                head = head.next;
                size--;
                return true;
            }
            for (int i = 0; i < index - 1; i++)
                tmp = tmp.next;
            tmp.next = tmp.next.next;
            size--;
            return true;
        }
        return false;
    }

    public boolean contains(T item) {
        boolean contains = false;
        if (head != null) {
            Node<T> tmp = head;
            while (tmp != null) {
                if (tmp.data.equals(item)) {
                    contains = true;
                    break;
                }
                tmp = tmp.next;
            }
        }
        return contains;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<>(head);
    }
}
