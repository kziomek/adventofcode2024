package advent.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintQueue {

    public static void main(String[] args) throws IOException {

//        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5/example.txt"));
                List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5/my-input.txt"));
        //        List<String> lines = Files.readAllLines(Path.of("src/main/resources/day5/my-debug-input.txt"));

        Set<Integer> distinct = dist(lines);
        Map<Integer, Set<Integer>> graph = buildGraph(lines);
        List<List<Integer>> pages = getPages(lines);

        List<List<Integer>> correctlyOrderedManuals = filterCorrectlyOrderedManuals2(pages, graph);


        int sum = 0;
        for (List<Integer> correctlyOrderedManual : correctlyOrderedManuals) {
            if (correctlyOrderedManual.size() % 2 == 0) {
                throw new UnexpectedException("not expected even number of elements");
            }
            sum += correctlyOrderedManual.get(correctlyOrderedManual.size() / 2);
        }

        System.out.println("result " + sum);
    }

    private static List<List<Integer>> filterCorrectlyOrderedManuals2(List<List<Integer>> pages, Map<Integer, Set<Integer>> graph) {
        List<List<Integer>> correctlyOrderedManuals = new ArrayList<>();

        for (List<Integer> page : pages) {
            boolean ordered = true;
            for (int i = 0; i < page.size(); i++) {
                if (!ordered) {
                    break;
                }
                for (int j = i + 1; j < page.size(); j++) {
                    if (graph.get(page.get(j)) != null && graph.get(page.get(j)).contains(page.get(i))){
                        ordered = false;
                        break;
                    }
                }
            }
            if (ordered) {
                correctlyOrderedManuals.add(page);
            }

        }
        return correctlyOrderedManuals;
    }

    private static List<List<Integer>> filterCorrectlyOrderedManuals(List<List<Integer>> pages, Map<Integer, Set<Integer>> graph) {
        List<List<Integer>> correctlyOrderedManuals = new ArrayList<>();
        for (List<Integer> page : pages) {
            if (correctlyOrdered(page, graph)) {
                correctlyOrderedManuals.add(page);
            }
        }
        return correctlyOrderedManuals;
    }

    private static boolean correctlyOrdered(List<Integer> page, Map<Integer, Set<Integer>> graph) {
        System.out.println("--------------------------------");
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < page.size() - 1; i++) {
            System.out.println("--------------");
            if (!correctlyOrdered(page.get(i), page.get(i + 1), graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private static boolean correctlyOrdered(Integer a, Integer b, Map<Integer, Set<Integer>> graph, Set<Integer> visited) {
        if (visited.contains(a) || visited.contains(b)) {
            return false;
        }
        visited.add(a);
        System.out.println(a + " -> " + b);
        if (a.equals(b)) {
            return true;
        }

        Set<Integer> pages = graph.get(a);
        if (pages == null) {
            return false;
        }

        if (pages.contains(b)) {
            return true;
        }

        for (Integer page : pages) {
            if (correctlyOrdered(page, b, graph, visited)) {
                return true;
            }
        }

        return false;
    }

    private static List<List<Integer>> getPages(List<String> lines) {
        List<List<Integer>> pages = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(",")) {
                pages.add(Arrays.stream(line.split(",")).map(Integer::valueOf).toList());
            }
        }
        return pages;
    }

    private static Set<Integer> dist(List<String> lines) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> setL = new HashSet<>();
        Set<Integer> setP = new HashSet<>();

        for (String line : lines) {
            if (line.isBlank()) {
                break;
            }
            String[] split = line.split("\\|");
            Integer a = Integer.valueOf(split[0]);
            Integer b = Integer.valueOf(split[1]);

            set.add(a);
            set.add(b);
            setL.add(a);
            setP.add(b);
        }

        return set;
    }

    private static Map<Integer, Set<Integer>> buildGraph(List<String> lines) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (String line : lines) {
            if (line.isBlank()) {
                break;
            }
            String[] split = line.split("\\|");
            Integer a = Integer.valueOf(split[0]);
            Integer b = Integer.valueOf(split[1]);

            if (!graph.containsKey(a)) {
                graph.put(a, new HashSet<>());
            }
            graph.get(a).add(b);
        }

        return graph;
    }
}
