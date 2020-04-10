package datastructures;

class MinHeap {

    //definition of a binary tree
    private static class Node {
        int data;
        Node left, right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean contains(Node root, int item) {
        if (root == null)
            return false;

        if (root.data == item)
            return true;
        //because in a MinHeap all children nodes must be less than their root node
        else if (root.data > item)
            return false;

        boolean left = contains(root.left, item);
        boolean right = contains(root.right, item);

        return left || right;
    }

    public static void main(String[] args) {
        Node root = new Node(10, new Node(15, new Node(40, null, null), new Node(50, null, null)), new Node(30, new Node(100, null, null), new Node(40, null, null)));
        System.out.println(contains(root, 100));
    }
}
