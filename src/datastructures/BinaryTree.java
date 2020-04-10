package datastructures;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    private static class Node<T> {
        T item;
        Node<T> left, right;

        public Node(T item, Node<T> left, Node<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    public void add(T item) {
        add(root, item);
    }

    public void addAll(T... items) {
        for (T item : items)
            add(item);
    }

    private void add(Node<T> node, T item) {
        if (node == null)
            root = new Node<>(item, null, null);

        else if (item.compareTo(node.item) != 1) {
            if (node.left == null)
                node.left = new Node(item, null, null);
            else
                add(node.left, item);
        } else {
            if (node.right == null)
                node.right = new Node(item, null, null);
            else
                add(node.right, item);
        }
    }

    public boolean contains(T item) {
        return contains(root, item);
    }

    private boolean contains(Node<T> node, T item) {
        if (node == null)
            return false;

        if (item.compareTo(node.item) == 0)
            return true;

        if (item.compareTo(node.item) == -1)
            return contains(node.left, item);
        else
            return contains(node.right, item);
    }
}

class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Integer> baum = new BinaryTree<>();
        baum.addAll(2, 5, 6, 1, 4, 8, 5);
        System.out.println(baum.contains(5));
        System.out.println(baum.contains(1));
        System.out.println(baum.contains(19));
    }
}