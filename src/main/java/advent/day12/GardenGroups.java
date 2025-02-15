package advent.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class GardenGroups {

    public static void main(String[] args) throws IOException {

//        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example1.txt"))
        char[][] gardenGrid = Files.readAllLines(Path.of("src/main/resources/day12/example2.txt"))
            .stream().map(String::toCharArray).toArray(char[][]::new);

        char[][] expandedGrid = expand(gardenGrid);

        print(gardenGrid);
        print(expandedGrid);

        Map<Character, Integer> areas = collectAreas(gardenGrid);
        System.out.println(areas);

        int part1Result = part1(areas, gardenGrid);
        System.out.println("Part 1 result " + part1Result);
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

    private static int calculatePerimeter(char plant, char[][] gardenGrid) {
        System.out.println("calculate perimeter " + plant);
        char[][] expanded = expand(gardenGrid);

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
