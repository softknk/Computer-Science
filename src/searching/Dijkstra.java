package searching;

import datastructures.Graph;

import java.util.*;

public class Dijkstra {

    public static Graph<Vertex> findShortestPath(Graph<Vertex> graph, Vertex start) {
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
        private int distance;
        private Vertex previous;
        private Map<Vertex, Integer> adjacents;

        public Vertex(String name) {
            this.name = name;
            adjacents = new HashMap<>();
            distance = Integer.MAX_VALUE;
        }

        public void addDestination(Vertex vertex, int distance) {
            adjacents.put(vertex, distance);
        }

        @Override
        public int compareTo(Vertex vertex) {
            return (distance > vertex.distance) ? 1 : (distance == vertex.distance) ? 0 : -1;
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

        public Vertex getPrevious() {
            return previous;
        }
    }
}
