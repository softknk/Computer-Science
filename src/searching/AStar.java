package searching;

import datastructures.Graph;
import datastructures.Graph.Node;

import java.util.*;

public class AStar {

    public static void findShortestPath(Graph<Node> graph, Node start) {
        Set<Node> openList = new HashSet<>();
        Set<Node> closedList = new HashSet<>();

        graph.getsNodes().forEach(node -> node.setDistance(Integer.MAX_VALUE - 1));
        start.setDistance(0);

        openList.add(start);

        Node current;

        while (!openList.isEmpty()) {
            current = getNodeWithLowestCosts(openList);

            for (Map.Entry<Node, Integer> adjacent : current.getAdjacents().entrySet()) {
                if (!closedList.contains(adjacent.getKey())) {
                    calculateShortestDistance(adjacent.getKey(), adjacent.getValue(), current);
                    openList.add(adjacent.getKey());
                }
            }

            closedList.add(current);
        }
    }

    /**
     * return the node with the lowest costs (distance + heuristic)
     * @param set set of nodes
     * @return node with lowest distance
     */
    private static Node getNodeWithLowestCosts(Set<Node> set) {
        int lowest_costs = Integer.MAX_VALUE;
        Node lowest = null;
        for (Node item : set) {
            if (item.getCosts() < lowest_costs) {
                lowest = item;
                lowest_costs = item.getDistance();
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
    private static void calculateShortestDistance(Graph.Node destination, int edgeWeight, Graph.Node source) {
        if (destination.getDistance() > source.getDistance() + edgeWeight) {
            destination.setDistance(source.getDistance() + edgeWeight);
            destination.setPrevious(source);
        }
    }
}