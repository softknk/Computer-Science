package datastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//<T> represents the Node class
public class Graph<T> {

    private HashSet<T> nodes;

    public Graph(T... nodes) {
        this.nodes = new HashSet<>();
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public void addNode(T item) {
        nodes.add(item);
    }

    public HashSet<T> getsNodes() {
        return nodes;
    }

    public static class Node {

        private String name;
        private int distance;
        private int heuristic;
        private Node previous;
        private HashMap<Node, Integer> adjacents;

        public Node(String name) {
            adjacents = new HashMap<>();
            this.name = name;
        }

        public Node(String name, int heuristic) {
            adjacents = new HashMap<>();
            this.name = name;
            this.heuristic = heuristic;
        }

        public void addAdjacent(Node item, int weight) {
            adjacents.put(item, weight);
        }

        public void addBidirectional(Node node, int weight) {
            addAdjacent(node, weight);
            node.addAdjacent(node, weight);
        }

        public Node getFastestAdjacentNode() {
            Node lowest = null;
            int lowest_distance = Integer.MAX_VALUE;
            for (Map.Entry<Node, Integer> entry : adjacents.entrySet()) {
                if (entry.getValue() < lowest_distance) {
                    lowest_distance = entry.getValue();
                    lowest = entry.getKey();
                }
            }
            return lowest;
        }

        public HashMap<Node, Integer> getAdjacents() {
            return adjacents;
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public int getHeuristic() {
            return heuristic;
        }

        public int getCosts() {
            return distance + heuristic;
        }
    }
}
