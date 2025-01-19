package advent.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardGallivant {

    public static void main(String[] args) throws IOException {

//        char[][] grid = Files.readAllLines(Path.of("src/main/resources/day6/example.txt"))
                    char[][] grid = Files.readAllLines(Path.of("src/main/resources/day6/my-input.txt"))
            .stream()
            .map(String::toCharArray)
            .toArray(char[][]::new);

        Position startPosition = findStartPosition(grid);

        int obstacleCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == '#' || grid[i][j] == '^') {
                    continue;
                }
                char[][] gridO = copyGrid(grid);
                gridO[i][j] = 'O';

                System.out.println("pos " + i + " " + j);
                try {
                    moveGuard(gridO, startPosition);
                } catch (CycleException ce) {
                    obstacleCount++;
                }
            }
        }
        System.out.println("Result part 2 " + obstacleCount);

        moveGuard(grid, startPosition);

        //        printGrid(grid);

        int countX = countX(grid);
        System.out.println("Result part 1 " + countX);
    }

    private static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    private static int countX(char[][] grid) {
        int count = 0;
        for (char[] chars : grid) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printGrid(char[][] grid) {
        for (char[] chars : grid) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static void moveGuard(char[][] grid, Position startPosition) {
        int i = startPosition.i, j = startPosition.j;

        Position move = new Position(-1, 0);

        boolean isLoop = true;
        int turnCounter = 0;

        do {
            if (grid[i][j] != 'X') {
                isLoop = false;
            }
            grid[i][j] = 'X';
            if (canMoveStraight(i, j, move, grid)) {
                i += move.i;
                j += move.j;
            } else {
                move = turn90(move);
                if (isLoop) {
                    turnCounter++;
                    if (turnCounter == 4) {
                        throw new CycleException();
                    }
                } else {
                    turnCounter = 0;
                    isLoop = true;
                }
            }
        } while (guardInBoundries(i, j, grid));
    }

    private static Position turn90(Position move) {
        if (move.i == -1 && move.j == 0) {
            return new Position(0, 1);
        }
        if (move.i == 0 && move.j == 1) {
            return new Position(1, 0);
        }
        if (move.i == 1 && move.j == 0) {
            return new Position(0, -1);
        }
        if (move.i == 0 && move.j == -1) {
            return new Position(-1, 0);
        }
        throw new IllegalStateException("can't turn");
    }

    private static boolean canMoveStraight(int i, int j, Position move, char[][] grid) {
        if (!guardInBoundries(i + move.i, j + move.j, grid)) {
            return true;
        }
        return grid[i + move.i][j + move.j] != '#' && grid[i + move.i][j + move.j] != 'O';
    }

    private static boolean guardInBoundries(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        return true;
    }

    private static Position findStartPosition(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '^') {
                    return new Position(i, j);
                }
            }
        }
        throw new IllegalStateException("Failed to find start position");
    }
}
