package advent.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HoofIt {
    public static void main(String[] args) throws IOException {

        // collect start points
        // traverse recursivelly from 0 and split where more than one path
        // collect coordinates of 9 and add to set for

        //        Integer[][] grid = Files.readAllLines(Path.of("src/main/resources/day10/example-1.txt")).stream()
//        Integer[][] grid = Files.readAllLines(Path.of("src/main/resources/day10/example.txt")).stream()
                    Integer[][] grid = Files.readAllLines(Path.of("src/main/resources/day10/my-input.txt")).stream()
            .map(line -> Arrays.stream(line.split(""))
                .map(Integer::valueOf)
                .toArray(Integer[]::new)
            ).toArray(Integer[][]::new);
        Set<Coordinates> trailheads = collectTrailheads(grid);
                System.out.println(trailheads);
                int result = part1(trailheads, grid);
        int result2 = part2(trailheads, grid);
                System.out.println("Part 1 : " + result);
        System.out.println("Part 2 : " + result2);
    }

    private static int part1(Set<Coordinates> trailheads, Integer[][] grid) {
        int count = 0;
        for (Coordinates trailhead : trailheads) {
            count += countScore(trailhead, grid);
        }

        return count;
    }

    private static int part2(Set<Coordinates> trailheads, Integer[][] grid) {
        int count = 0;
        for (Coordinates trailhead : trailheads) {
            int rating = countRating(trailhead, grid);
            count += rating;
        }

        return count;
    }

    private static int countRating(Coordinates trailhead, Integer[][] grid) {
        Set<Coordinates> peaks = new HashSet<>();
        return 1 + traverseGrid(trailhead.i, trailhead.j, grid, peaks);
    }

    private static int countScore(Coordinates trailhead, Integer[][] grid) {
        Set<Coordinates> peaks = new HashSet<>();
        traverseGrid(trailhead.i, trailhead.j, grid, peaks);
        return peaks.size();
    }

    private static int traverseGrid(int i, int j, Integer[][] grid, Set<Coordinates> peaks) {
        int rating = 0;
        if (grid[i][j] == 9) {
            peaks.add(new Coordinates(i, j));
            return 0;
        }
        int height = grid[i][j];
        int nextHeight = height + 1;
        int split = -1;
        if (canMove(grid, nextHeight, i + 1, j)) {
            rating += traverseGrid(i + 1, j, grid, peaks);
            split += 1;
        }
        if (canMove(grid, nextHeight, i - 1, j)) {
            rating += traverseGrid(i - 1, j, grid, peaks);
            split += 1;
        }
        if (canMove(grid, nextHeight, i, j + 1)) {
            rating += traverseGrid(i, j + 1, grid, peaks);
            split += 1;
        }
        if (canMove(grid, nextHeight, i, j - 1)) {
            rating += traverseGrid(i, j - 1, grid, peaks);
            split += 1;
        }
        return rating + split;
    }

    private static boolean canMove(Integer[][] grid, int nextHeight, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == nextHeight;
    }

    private static Set<Coordinates> collectTrailheads(Integer[][] grid) {
        Set<Coordinates> trailheads = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    trailheads.add(new Coordinates(i, j));
                }
            }
        }

        return trailheads;
    }
}
