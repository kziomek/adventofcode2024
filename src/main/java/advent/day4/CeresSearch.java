package advent.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CeresSearch {

    public static void main(String[] args) throws IOException {
        //        char[][] grid = Files.readAllLines(Path.of("src/main/resources/day4/small-example.txt"))
        //        char[][] grid = Files.readAllLines(Path.of("src/main/resources/day4/part2-small-example.txt"))
//        char[][] grid = Files.readAllLines(Path.of("src/main/resources/day4/part2-example.txt"))
            //        char[][] grid = Files.readAllLines(Path.of("src/main/resources/day4/example.txt"))
                    char[][] grid = Files.readAllLines(Path.of("src/main/resources/day4/my-input.txt"))
            .stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);

        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                counter += countXMAS(grid, i, j);
            }
        }
        System.out.println("Part 1 result " + counter);

        int part2Counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                part2Counter += countXMASPart2(grid, i, j);
            }
        }
        System.out.println("Part 2 result " + part2Counter);
    }

    private static int countXMAS(char[][] grid, int i, int j) {
        if (grid[i][j] != 'X') {
            return 0;
        }

        int counter = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                // verify it's within boundries
                if (i + 3 * k < 0 || i + 3 * k >= grid.length || j + 3 * l < 0 || j + 3 * l >= grid[0].length) {
                    continue;
                }
                if (grid[i + k][j + l] == 'M' && grid[i + 2 * k][j + 2 * l] == 'A' && grid[i + 3 * k][j + 3 * l] == 'S') {
                    counter++;
                    System.out.println("found");
                }
            }
        }
        return counter;
    }

    private static int countXMASPart2(char[][] grid, int i, int j) {
        if (grid[i][j] != 'A') {
            return 0;
        }
        System.out.println("pos[" + (i) + "][" + (j) + "]");

        for (int k = -1; k <= 1; k++) {
            if (k == 0) {
                continue;
            }
            for (int l = -1; l <= 1; l++) {
                if (l == 0) {
                    continue;
                }

                // verify it's within boundries
                if (i + k < 0 || i + k >= grid.length || j + l < 0 || j + l >= grid[0].length
                    || i - k < 0 || i - k >= grid.length || j - l < 0 || j - l >= grid[0].length
                    || i - k < 0 || i - k >= grid.length || j + l < 0 || j + l >= grid[0].length
                    || i + k < 0 || i + k >= grid.length || j - l < 0 || j - l >= grid[0].length) {
                    continue;
                }

                if ((grid[i + k][j + l] == 'M' && grid[i - k][j - l] == 'S' || grid[i + k][j + l] == 'S' && grid[i - k][j - l] == 'M')
                    && (grid[i + k][j - l] == 'M' && grid[i - k][j + l] == 'S' || grid[i + k][j - l] == 'S' && grid[i - k][j + l] == 'M')
                ) {
                    System.out.println("found");
                    return 1;
                }
            }
        }
        return 0;
    }
}
