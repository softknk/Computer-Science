package datastructures;

import java.util.HashSet;
import java.util.Set;

//<T> represents the Node class
public class Graph<T> {

    private Set<T> vertices;

    public Graph(T... vertices) {
        this.vertices = new HashSet();
        for (T vertex : vertices)
            this.vertices.add(vertex);
    }

    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    public Set<T> getVertices() {
        return vertices;
    }
}
