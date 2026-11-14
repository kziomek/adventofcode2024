package advent.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    Node startNode;
    Node endNode;

    Map<String, Node> nodes = new HashMap<>();

    public Graph(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;

        nodes.put(startNode.key, startNode);
        nodes.put(endNode.key, endNode);
    }

    public Node getCreateNode(Graph graph, String poskey) {
        if (!graph.nodes.containsKey(poskey)) {
            Node node = new Node(poskey);
            graph.nodes.put(poskey, node);
        }
        return graph.nodes.get(poskey);
    }
}
