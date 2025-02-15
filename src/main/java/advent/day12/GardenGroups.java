package advent.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GardenGroups {

    public static void main(String[] args) throws IOException {

//        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example1.txt"))
            //        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example2.txt"))
            //        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example3.txt"))

//                    char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example-part2-1.txt"))
//                    char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example-part2-2.txt"))
                    char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example-part2-3.txt"))
            //        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/my-example.txt"))
            .stream().map(String::toCharArray).toArray(char[][]::new);

        List<Map<Character, Set<Coordinates>>> groups = buildGroups(gardenGrid);
        int total = 0;
        for (Map<Character, Set<Coordinates>> group : groups) {
            for (Map.Entry<Character, Set<Coordinates>> entry : group.entrySet()) {
                char plant = entry.getKey();
                Set<Coordinates> plants = entry.getValue();
                int area = plants.size();

                //one element expected only
                //                int perimeter = calculatePerimeter(plants, plant, gardenGrid.length, gardenGrid[0].length);

                int sides = calculateSides(plants, plant, gardenGrid.length, gardenGrid[0].length);

                //                System.out.println("group " + plant + " " + area + " " + perimeter);
                System.out.println("group " + plant + " " + area + " " + sides);
                total += area * sides;
            }

            System.out.println();
        }

        System.out.println(groups);

        //        char[][] expandedGrid = expand(gardenGrid);

        //        print(gardenGrid);
        //        print(expandedGrid);
        //
        //        Map<Character, Integer> areas = collectAreas(gardenGrid);
        //        System.out.println(areas);
        //
        //        int part1Result = part1(areas, gardenGrid);
        System.out.println("Part 1 result " + total);
    }

    private static int calculateSides(Set<Coordinates> plants, char plant, int i, int j) {
        char[][] grid = new char[i][j];
        for (Coordinates p : plants) {
            grid[p.i][p.j] = plant;
        }

        print(grid);
        char[][] expandedGrid = expand(grid);
        print(expandedGrid);
        return calculateSides(plant, expandedGrid);
    }

    private static int calculateSides(char plant, char[][] expanded) {
        System.out.println("calculate sides " + plant);

        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[0].length; j++) {
                if (shouldBePerimeter(expanded, plant, i, j)) {
                    expanded[i][j] = '.';
                }
            }
        }
        print(expanded);

        int sidesCount = countSides(plant, expanded);
        //        for (char[] chars : expanded) {
        //            for (char aChar : chars) {
        //                if (aChar == '.') {
        //                    perimeterCount++;
        //                }
        //            }
        //        }
        //        System.out.println("Perimeter " + plant + " " + perimeterCount);
        return sidesCount;
    }

    public static int countSides(char plant, char[][] expanded) {
        Set<Coordinates> visited = new HashSet<>();

        int sides = 0;
        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[0].length; j++) {

                if (expanded[i][j] == '.') {
                    Coordinates sideCoordinates = new Coordinates(i, j);
                    if (!visited.contains(sideCoordinates)) {
                        sides++;
                        burnSide(sideCoordinates, expanded, plant, visited);
                    }
                }
            }
        }
        return sides;
    }

    // TODO consider on which side is plant when burning side
    private static void burnSide(Coordinates sideCoordinates, char[][] expanded, char plant, Set<Coordinates> visited) {
        visited.add(sideCoordinates);
        if (isVertical(sideCoordinates, expanded)) {
            burnVerticalSide(sideCoordinates, expanded, visited, plant);
        }
        if (isHorizontal(sideCoordinates, expanded)) {
            burnHorizontalSide(sideCoordinates, expanded, visited, plant);
        }
        //it is dot size;
        return;
    }

    private static void burnVerticalSide(Coordinates sideCoordinates, char[][] expanded, Set<Coordinates> visited, char plant) {
        // burn up
        int startI = sideCoordinates.i;
        int startJ = sideCoordinates.j;

        int i = startI - 2;
        //burn up
        Coordinates coordinates = new Coordinates(i, startJ);
        while (inGrid(coordinates, expanded) && isDot(coordinates, expanded)) {
            if (expanded[coordinates.i + 1][coordinates.j] == plant) {
                break;
            }
            visited.add(coordinates);
            coordinates = new Coordinates(coordinates.i - 2, coordinates.j);
        }
        // burn down
        i = startI + 2;
        coordinates = new Coordinates(i, startJ);
        while (inGrid(coordinates, expanded) && isDot(coordinates, expanded)) {
            if (expanded[coordinates.i - 1][coordinates.j] == plant) {
                break;
            }
            visited.add(coordinates);
            coordinates = new Coordinates(coordinates.i + 2, coordinates.j);
        }
    }

    private static void burnHorizontalSide(Coordinates sideCoordinates, char[][] expanded, Set<Coordinates> visited, char plant) {
        // burn up
        int startI = sideCoordinates.i;
        int startJ = sideCoordinates.j;

        int j = startJ - 2;
        //burn left
        Coordinates coordinates = new Coordinates(startI, j);
        while (inGrid(coordinates, expanded) && isDot(coordinates, expanded)) {
            if (expanded[coordinates.i][coordinates.j + 1] == plant) {
                break;
            }
            visited.add(coordinates);
            coordinates = new Coordinates(coordinates.i, coordinates.j - 2);
        }
        // burn right
        j = startJ + 2;
        coordinates = new Coordinates(startI, j);
        while (inGrid(coordinates, expanded) && isDot(coordinates, expanded)) {
            if (expanded[coordinates.i][coordinates.j - 1] == plant) {
                break;
            }
            visited.add(coordinates);
            coordinates = new Coordinates(coordinates.i, coordinates.j + 2);
        }
    }

    private static boolean isVertical(Coordinates sideCoordinates, char[][] expanded) {
        Coordinates up = new Coordinates(sideCoordinates.i - 2, sideCoordinates.j);
        if (inGrid(up, expanded) && isDot(up, expanded)) {
            return true;
        }
        Coordinates down = new Coordinates(sideCoordinates.i + 2, sideCoordinates.j);
        if (inGrid(down, expanded) && expanded[down.i][down.j] == '.') {
            return true;
        }
        return false;
    }

    private static boolean isDot(Coordinates up, char[][] expanded) {
        return expanded[up.i][up.j] == '.';
    }

    private static boolean isHorizontal(Coordinates sideCoordinates, char[][] expanded) {
        Coordinates left = new Coordinates(sideCoordinates.i, sideCoordinates.j - 2);
        if (inGrid(left, expanded) && expanded[left.i][left.j] == '.') {
            return true;
        }
        Coordinates right = new Coordinates(sideCoordinates.i, sideCoordinates.j + 2);
        if (inGrid(right, expanded) && expanded[right.i][right.j] == '.') {
            return true;
        }
        return false;
    }

    private static boolean inGrid(Coordinates coordinates, char[][] expanded) {
        return coordinates.i >= 0
            && coordinates.j >= 0
            && coordinates.i < expanded.length
            && coordinates.j < expanded[0].length;
    }

    private static int calculatePerimeter(Set<Coordinates> plants, char plant, int i, int j) {
        char[][] grid = new char[i][j];
        for (Coordinates p : plants) {
            grid[p.i][p.j] = plant;
        }

        print(grid);
        char[][] expandedGrid = expand(grid);
        print(expandedGrid);
        return calculatePerimeter(plant, expandedGrid);
    }

    private static List<Map<Character, Set<Coordinates>>> buildGroups(char[][] gardenGrid) {
        Set<Coordinates> visited = new HashSet<>();
        List<Map<Character, Set<Coordinates>>> groups = new ArrayList<>();
        for (int i = 0; i < gardenGrid.length; i++) {
            for (int j = 0; j < gardenGrid[0].length; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (!visited.contains(coordinates)) {
                    char plant = gardenGrid[i][j];

                    Map<Character, Set<Coordinates>> group = buildGroup(gardenGrid, plant, i, j, visited);
                    groups.add(group);
                }
            }
        }
        return groups;
    }

    private static Map<Character, Set<Coordinates>> buildGroup(char[][] gardenGrid, char plant, int i, int j, Set<Coordinates> visited) {
        Set<Coordinates> group = new HashSet<>();
        buildGroup(gardenGrid, plant, i, j, group, visited);
        return Map.of(plant, group);
    }

    private static void buildGroup(char[][] gardenGrid, char plant, int i, int j, Set<Coordinates> group, Set<Coordinates> visited) {
        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(new Coordinates(i, j));

        while (!queue.isEmpty()) {
            Coordinates poll = queue.poll();
            // test boundries here
            if (poll.i < 0 || poll.j < 0 || poll.i >= gardenGrid.length || poll.j >= gardenGrid[0].length) {
                continue;
            }
            if (visited.contains(poll)) {
                continue;
            }

            if (gardenGrid[poll.i][poll.j] == plant) {
                group.add(poll);
                visited.add(poll);
                // add neighbours here
                int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                for (int[] direction : directions) {
                    queue.add(new Coordinates(poll.i + direction[0], poll.j + direction[1]));
                }
            }
        }

        // if out of boundry return
        if (gardenGrid[i][j] == plant) {
            Coordinates coordinates = new Coordinates(i, j);
            group.add(coordinates);
            visited.add(coordinates);
        }
    }

    private static int part1(Map<Character, Integer> areas, char[][] gardenGrid) {
        int total = 0;
        for (Map.Entry<Character, Integer> entry : areas.entrySet()) {
            char plant = entry.getKey();
            int area = entry.getValue();

            int perimeter = calculatePerimeter(plant, gardenGrid);
            System.out.println("Plant " + plant + " area * perimeter " + area + " " + perimeter + " = " + area * perimeter);
            total += area * perimeter;
        }

        return total;
    }

    private static int calculatePerimeter(char plant, char[][] expanded) {
        System.out.println("calculate perimeter " + plant);
        //        char[][] expanded = expand(gardenGrid);

        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[0].length; j++) {
                if (shouldBePerimeter(expanded, plant, i, j)) {
                    expanded[i][j] = '.';
                }
            }
        }
        print(expanded);
        int perimeterCount = 0;
        for (char[] chars : expanded) {
            for (char aChar : chars) {
                if (aChar == '.') {
                    perimeterCount++;
                }
            }
        }
        System.out.println("Perimeter " + plant + " " + perimeterCount);
        return perimeterCount;
    }

    private static boolean shouldBePerimeter(char[][] expanded, char plant, int i, int j) {
        return countNeighbours(expanded, plant, i, j) == 1;
    }

    private static int countNeighbours(char[][] expanded, char plant, int i, int j) {
        int count = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            if (i + direction[0] >= 0 && (i + direction[0]) < expanded.length
                && (j + direction[1]) >= 0 && (j + direction[1]) < expanded[0].length
                && expanded[i + direction[0]][j + direction[1]] == plant) {
                count++;
            }
        }
        return count;
    }

    private static Map<Character, Integer> collectAreas(char[][] gardenGrid) {
        Map<Character, Integer> areas = new HashMap<>();
        for (char[] chars : gardenGrid) {
            for (char aChar : chars) {
                if (!areas.containsKey(aChar)) {
                    areas.put(aChar, 0);
                }
                areas.put(aChar, 1 + areas.get(aChar));
            }
        }
        return areas;
    }

    private static char[][] expand(char[][] gardenGrid) {
        char[][] expanded = new char[2 * gardenGrid.length + 1][2 * gardenGrid[0].length + 1];
        for (int i = 0; i < expanded.length; i++) {
            for (int j = 0; j < expanded[0].length; j++) {
                expanded[i][j] = ' ';
            }
        }
        for (int i = 0; i < gardenGrid.length; i++) {
            for (int j = 0; j < gardenGrid[0].length; j++) {
                expanded[2 * i + 1][2 * j + 1] = gardenGrid[i][j];
            }
        }
        return expanded;
    }

    private static void print(char[][] array) {
        for (char[] chars : array) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }
}
