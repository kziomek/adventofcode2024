package advent.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static Warehouse parse(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        boolean readingMoves = false;
        List<String> warehouseLines = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) {
                readingMoves = true;
                continue;
            }
            if (readingMoves) {
                moves.add(line);
            } else {
                warehouseLines.add(line);
            }
        }

        char[][] grid = convertToGrid(warehouseLines);
        char[] movesList = mergeMoves(moves);

        return new Warehouse(grid, movesList);
    }

    private static char[] mergeMoves(List<String> moves) {
        String join = String.join("", moves);
        return join.toCharArray();
    }

    private static char[][] convertToGrid(List<String> warehouseLines) {
        return warehouseLines.stream().map(String::toCharArray).toArray(char[][]::new);
    }
}
