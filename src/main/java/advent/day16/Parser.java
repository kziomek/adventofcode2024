package advent.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Parser {

    private static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static Graph parse(String filePath) throws IOException {
        char[][] array = Files.readAllLines(Path.of(filePath))
            .stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);

        printMaze(array);

        return buildGraph(array);
    }

    private static Graph buildGraph(char[][] array) {
        Node startNode = findNode(array, 'S');
        Node endNode = findNode(array, 'E');

        Map<String, Node> allNodes = new HashMap<>();
        allNodes.put(startNode.key, startNode);
        allNodes.put(endNode.key, endNode);

        Graph graph = new Graph(startNode, endNode);

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visited.contains(node)) {
                continue;
            }

            Set<String> visitedPos = new HashSet<>();
            String[] pos = node.key.split("_");
            int i = Integer.parseInt(pos[0]);
            int j = Integer.parseInt(pos[1]);
            visitedPos.add(poskey(i, j));

            // for each direction try to traverse

            for (int[] direction : DIRECTIONS) {
                if (canMove(array, direction, i, j)) {
                    Edge edge = continueTraverse(array, graph, visitedPos, i + direction[0], j + direction[1]);
                    if (edge != null) {
                        node.addEdge(edge);
                        queue.add(edge.node);
                    }
                }
            }
        }
        // Keep List of visited nodes
        // Create queue of nodes
        // for node in the queue (if not visited)
        // travel to nearest intersections and add edge to it with distance (get existing node or create new one)
        // ignore dead end

        // 1 direction - dead end
        // >2 directions intersection

        return null;
    }

    private static Edge continueTraverse(char[][] array, Graph graph, Set<String> visitedPos, int startI, int startJ) {
        int i = startI;
        int j = startJ;
        int distance = 1;

        while (countRoutes(array, i, j) == 2) {
            visitedPos.add(poskey(i, j));
            for (int[] direction : DIRECTIONS) {
                if (!visitedPos.contains(poskey(i + direction[0], j + direction[1])) && canMove(array, direction, i, j)) {
                    i += direction[0];
                    j += direction[1];
                    distance++;
                    break;
                }
            }

            // continue move
        }

        int routeCount = countRoutes(array, i, j);

        String poskey = poskey(i, j);
        if (routeCount == 1) {
            System.out.println("Dead end distance " + distance + " " + poskey);
            return null;
        }
        if (routeCount > 2) {
            System.out.println("Intersection distance " + distance + " " + poskey);
            Node node = graph.getCreateNode(graph, poskey);
            return new Edge(distance, node);
        }
        System.out.println("Unexpected state");
        return null;
    }

    private static boolean canMove(char[][] array, int[] direction, int i, int j) {
        return array[i + direction[0]][j + direction[1]] == '.' || array[i + direction[0]][j + direction[1]] == 'S' || array[i + direction[0]][j + direction[1]] == 'E';
    }

    private static String poskey(int i, int j) {
        return i + "_" + j;
    }

    private static int countRoutes(char[][] array, int i, int j) {
        int count = 0;
        for (int[] direction : DIRECTIONS) {
            if (canMove(array, direction, i, j)) {
                count++;
            }
        }
        return count;
    }

    private static Node findNode(char[][] array, char value) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == value) {
                    return new Node(i + "_" + j);
                }
            }
        }
        throw new IllegalStateException("unexpected value");
    }

    private static void printMaze(char[][] array) {
        for (char[] chars : array) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }
}
