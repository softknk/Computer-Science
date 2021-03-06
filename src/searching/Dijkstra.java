package searching;

import datastructures.Graph;
import datastructures.Graph.Node;

import java.util.*;

public class Dijkstra {

    public static void findShortestPath(Graph<Node> graph, Node start) {
        Set<Node> openList = new HashSet<>();
        Set<Node> closedList = new HashSet<>();

        graph.getsNodes().forEach(node -> node.setDistance(Integer.MAX_VALUE - 1));
        start.setDistance(0);

        openList.add(start);

        Node current;

        while (!openList.isEmpty()) {
            current = getNodeWithLowestDistanceOf(openList);

            for (Map.Entry<Node, Integer> adjacent : current.getAdjacents().entrySet()) {
                if (!closedList.contains(adjacent.getKey())) {
                    calculateShortestDistance(adjacent.getKey(), adjacent.getValue(), current);
                    openList.add(adjacent.getKey());
                }
            }

            openList.remove(current);
            closedList.add(current);
        }
    }

    /**
     * return the node with the lowest distance
     * @param set set of nodes
     * @return node with lowest distance
     */
    private static Node getNodeWithLowestDistanceOf(Set<Node> set) {
        int lowest_distance = Integer.MAX_VALUE;
        Node lowest = null;
        for (Node item : set) {
            if (item.getDistance() < lowest_distance) {
                lowest = item;
                lowest_distance = item.getDistance();
            }
        }
        return lowest;
    }

    /**
     * update current distance of destination node
     * @param destination node where we are currently
     * @param edgeWeight edgeWeight to get from source to destination
     * @param source node from where we came
     */
    private static void calculateShortestDistance(Node destination, int edgeWeight, Node source) {
        if (destination.getDistance() > source.getDistance() + edgeWeight) {
            destination.setDistance(source.getDistance() + edgeWeight);
            destination.setPrevious(source);
        }
    }
}
