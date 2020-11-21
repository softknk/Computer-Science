package searching;

import datastructures.Graph;
import datastructures.Graph.Node;

public class AStarTest {

    public static void main(String[] args) {
        Node nodeA = new Node("A", 7);
        Node nodeB = new Node("B", 1);
        Node nodeC = new Node("C", 4);
        Node nodeD = new Node("D", 3);
        Node nodeE = new Node("E", 5);
        Node nodeF = new Node("F", 6);
        Node nodeH = new Node("H", 3);
        Node nodeI = new Node("I", 2);
        Node start = new Node("Start", 0);
        Node end = new Node("Ende", 0);

        start.addAdjacent(nodeA, 2);
        start.addAdjacent(nodeB, 7);

        nodeA.addAdjacent(nodeE, 2);
        nodeA.addAdjacent(nodeF, 3);

        nodeB.addAdjacent(nodeA, 1);
        nodeB.addAdjacent(nodeC, 4);
        nodeB.addAdjacent(nodeH, 3);

        nodeC.addAdjacent(nodeD, 1);

        nodeD.addAdjacent(nodeI, 2);

        nodeF.addAdjacent(nodeB, 1);
        nodeF.addAdjacent(nodeE, 2);

        nodeH.addAdjacent(nodeI, 5);
        nodeH.addAdjacent(end, 12);

        nodeI.addAdjacent(end, 7);

        //----------------------

        Graph graph = new Graph(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeH, nodeI, start, end);

        AStar.findShortestPath(graph, nodeA);

        Node v = end;
        System.out.println(v.getDistance());
        while (v != null) {
            System.out.println(v.getName());
            v = v.getPrevious();
        }
    }
}
