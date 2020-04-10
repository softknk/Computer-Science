package searching;

import datastructures.Graph;

import java.util.*;

public class AStern {

    public static void main(String[] args) {
        Vertex node1 = new Vertex("Saarbrücken", 222);
        Vertex node2 = new Vertex("Karlsruhe", 140);
        Vertex node3 = new Vertex("Kaiserslautern", 158);
        Vertex node4 = new Vertex("Frankfurt", 96);
        Vertex node5 = new Vertex("Ludwigshafen", 108);
        Vertex node6 = new Vertex("Heilbronn", 87);
        Vertex node7 = new Vertex("Würzburg", 0);

        node1.addDestination(node2, 145);
        node1.addDestination(node3, 70);

        node2.addDestination(node6, 84);

        node3.addDestination(node4, 103);
        node3.addDestination(node5, 53);

        node4.addDestination(node7, 116);

        node5.addDestination(node7, 183);

        node6.addDestination(node7, 102);


        Graph graph = new Graph(node1, node2, node3, node4, node5, node6, node7);

        graph = findShortestPath(graph, node1);

        Vertex v = node7;
        while (v != null) {
            System.out.println(v.name);
            v = v.previous;
        }
    }

    public static Graph findShortestPath(Graph graph, Vertex start) {
        PriorityQueue<Vertex> openList = new PriorityQueue<>();
        Set<Vertex> closedList = new HashSet<>();

        start.distance = 0;
        openList.offer(start);
        Vertex current;

        while (!openList.isEmpty()) {
            current = openList.poll();

            for (Map.Entry<Vertex, Integer> adjacent : current.adjacents.entrySet()) {
                if (!closedList.contains(adjacent.getKey())) {
                    calculateShortestDistance(adjacent.getKey(), adjacent.getValue(), current);
                    openList.offer(adjacent.getKey());
                }
            }

            closedList.add(current);
        }

        return graph;
    }

    private static void calculateShortestDistance(Vertex destination, int edgeWidht, Vertex source) {
        if (destination.distance > source.distance + edgeWidht) {
            destination.distance = source.distance + edgeWidht;
            destination.previous = source;
        }
    }

    public static class Vertex implements Comparable<Vertex> {

        private String name;
        private int distance, heuristic;
        private Vertex previous;
        private Map<Vertex, Integer> adjacents;

        public Vertex(String name, int heuristic) {
            this.name = name;
            this.heuristic = heuristic;
            adjacents = new HashMap<>();
            distance = Integer.MAX_VALUE / 2;
        }

        public void addDestination(Vertex vertex, int distance) {
            adjacents.put(vertex, distance);
        }

        @Override
        public int compareTo(Vertex vertex) {
            int costs = distance + heuristic;
            int _costs = vertex.distance + vertex.heuristic;
            return (costs > _costs) ? 1 : (costs == _costs) ? 0 : -1;
        }

        public String getName() {
            return name;
        }

        public Vertex getPrevious() {
            return previous;
        }
    }
}

class Test {
    public static void main(String[] args) {
        AStern.Vertex nodeA = new AStern.Vertex("A", 7);
        AStern.Vertex nodeB = new AStern.Vertex("B", 1);
        AStern.Vertex nodeC = new AStern.Vertex("C", 4);
        AStern.Vertex nodeD = new AStern.Vertex("D", 3);
        AStern.Vertex nodeE = new AStern.Vertex("E", 5);
        AStern.Vertex nodeF = new AStern.Vertex("F", 6);
        AStern.Vertex nodeH = new AStern.Vertex("H", 3);
        AStern.Vertex nodeI = new AStern.Vertex("I", 2);
        AStern.Vertex start = new AStern.Vertex("Start", 0);
        AStern.Vertex end = new AStern.Vertex("Ende", 0);

        start.addDestination(nodeA, 2);
        start.addDestination(nodeB, 7);

        nodeA.addDestination(nodeE, 2);
        nodeA.addDestination(nodeF, 3);

        nodeB.addDestination(nodeA, 1);
        nodeB.addDestination(nodeC, 4);
        nodeB.addDestination(nodeH, 3);

        nodeC.addDestination(nodeD, 1);

        nodeD.addDestination(nodeI, 2);

        nodeF.addDestination(nodeB, 1);
        nodeF.addDestination(nodeE, 2);

        nodeH.addDestination(nodeI, 5);
        nodeH.addDestination(end, 12);

        nodeI.addDestination(end, 7);

        //----------------------

        Graph graph = new Graph(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeH, nodeI, start, end);

        graph = AStern.findShortestPath(graph, nodeA);

        AStern.Vertex v = end;
        while (v != null) {
            System.out.println(v.getName());
            v = v.getPrevious();
        }
    }
}