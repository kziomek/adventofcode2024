package advent.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Node {

    String key;
    List<Edge> edges = new ArrayList<>();
//    Map<Node, Edge> routes;

    public Node(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

    public void addEdge(Edge edge) {
        if (edge != null) {
            edges.add(edge);
        }
    }
}
