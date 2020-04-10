package searching;

import datastructures.Graph;

import static searching.Dijkstra.Vertex;

public class DijkstraTest {

    public static void main(String[] args) {
        Vertex nodeA = new Vertex("A");
        Vertex nodeB = new Vertex("B");
        Vertex nodeC = new Vertex("C");
        Vertex nodeD = new Vertex("D");
        Vertex nodeE = new Vertex("E");
        Vertex nodeF = new Vertex("F");


        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF);

        graph = Dijkstra.findShortestPath(graph, nodeA);

        Vertex v = nodeE;
        while (v != null) {
            System.out.println(v.getName());
            v = v.getPrevious();
        }

        //
        System.out.println("...................");

        Vertex node1 = new Vertex("1");
        Vertex node2 = new Vertex("2");
        Vertex node3 = new Vertex("3");
        Vertex node4 = new Vertex("4");
        Vertex node5 = new Vertex("5");
        Vertex node6 = new Vertex("6");


        node1.addDestination(node2, 7);
        node1.addDestination(node3, 9);
        node1.addDestination(node6, 14);

        node2.addDestination(node3, 10);
        node2.addDestination(node4, 15);

        node3.addDestination(node6, 2);
        node3.addDestination(node4, 11);

        node4.addDestination(node5, 6);

        node6.addDestination(node5, 9);
        node5.addDestination(node6, 9);

        Graph graph2 = new Graph(node1, node2, node3, node4, node5, node6);

        graph2 = Dijkstra.findShortestPath(graph2, node1);

        Vertex ver = node5;
        while (ver != null) {
            System.out.println(ver.getName());
            ver = ver.getPrevious();
        }

        System.out.println("...................");

        Vertex nodea = new Vertex("A");
        Vertex nodeb = new Vertex("B");
        Vertex nodec = new Vertex("C");
        Vertex noded = new Vertex("D");
        Vertex nodee = new Vertex("E");
        Vertex nodeg = new Vertex("G");


        nodea.addDestination(nodeb, 10);
        nodea.addDestination(nodec, 5);

        nodeb.addDestination(nodee, 3);
        nodeb.addDestination(nodea, 10);
        nodeb.addDestination(nodeg, 5);
        nodeb.addDestination(nodec, 1);

        nodec.addDestination(noded, 3);
        nodec.addDestination(nodeg, 2);
        nodec.addDestination(nodea, 5);
        nodec.addDestination(nodeb, 1);

        noded.addDestination(nodee, 2);
        noded.addDestination(nodec, 3);

        nodee.addDestination(noded, 2);
        nodee.addDestination(nodeg, 1);

        nodeg.addDestination(nodee, 1);
        nodeg.addDestination(nodec, 2);
        nodeg.addDestination(nodeb, 3);

        Graph graph3 = new Graph(nodea, nodeb, nodec, noded, nodee, nodeg);

        graph3 = Dijkstra.findShortestPath(graph3, nodea);

        Vertex ver2 = nodee;
        while (ver2 != null) {
            System.out.println(ver2.getName());
            ver2 = ver2.getPrevious();
        }
    }
}
