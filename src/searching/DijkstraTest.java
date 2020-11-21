package searching;

import datastructures.Graph;
import datastructures.Graph.Node;

public class DijkstraTest {

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addAdjacent(nodeB, 10);
        nodeA.addAdjacent(nodeC, 15);

        nodeB.addAdjacent(nodeD, 12);
        nodeB.addAdjacent(nodeF, 15);

        nodeC.addAdjacent(nodeE, 10);

        nodeD.addAdjacent(nodeE, 2);
        nodeD.addAdjacent(nodeF, 1);

        nodeF.addAdjacent(nodeE, 5);

        Graph graph = new Graph(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);

        Dijkstra.findShortestPath(graph, nodeA);

        Node v = nodeE;
        System.out.println(nodeE.getDistance());
        while (v != null) {
            System.out.println(v.getName());
            v = v.getPrevious();
        }
    }
}
