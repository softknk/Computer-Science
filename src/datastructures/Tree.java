package datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<E> {

    public Node<E> root;

    public Tree(E item) {
        root = new Node<>(item);
    }

    private static class Node<T> {
        T item;
        List<Node<T>> children;

        public Node(T item) {
            this.item = item;
            this.children = new ArrayList<>();
        }

        public void addChildren(Node<T> child) {
            children.add(child);
        }

        public List<Node<T>> getChildren() {
            return children;
        }
    }

    public boolean tiefensuche(Node<E> node, E item) {
        if (node.item == item)
            return true;

        Iterator<Node<E>> iterator = node.children.iterator();
        while (iterator.hasNext()) {
            return tiefensuche(iterator.next(), item);
        }

        return false;
    }

    public boolean breitensuche(E item) {
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<E> tmp = queue.removeFirst();
            if (tmp.item == item)
                return true;
            else
                queue.addAll(tmp.getChildren());
        }

        return false;
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(10);
        tree.root.addChildren(new Node<>(4));
        tree.root.addChildren(new Node<>(8));
        tree.root.addChildren(new Node<>(7));
        tree.root.getChildren().get(0).addChildren(new Node<>(3));
        tree.root.getChildren().get(1).addChildren(new Node<>(2));
        tree.root.getChildren().get(2).addChildren(new Node<>(18));
        tree.root.getChildren().get(2).addChildren(new Node<>(1));

        System.out.println(tree.tiefensuche(tree.root, 20));
        System.out.println(tree.breitensuche(25));
    }
}