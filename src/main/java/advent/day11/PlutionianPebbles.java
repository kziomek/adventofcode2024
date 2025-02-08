package advent.day11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlutionianPebbles {

    public static void main(String[] args) throws IOException {
        String line = Files.readAllLines(Path.of("src/main/resources/day11/example.txt")).getFirst();
        //        String line = Files.readAllLines(Path.of("src/main/resources/day11/my-input.txt")).getFirst();
        List<Long> stones = Arrays.stream(line.split(" ")).map(Long::valueOf).toList();

        for (int i = 0; i < 25; i++) {
            System.out.println(i);
            stones = blink(stones);
            //            System.out.println(stones);
        }
        System.out.println("result " + stones.size());
    }

    private static List<Long> blink(List<Long> stones) {
        List<Long> newStage = new ArrayList<>();
        for (Long stone : stones) {
            if (stone == 0) {
                newStage.add(1L);
                continue;
            }
            String s = stone.toString();
            if (s.length() % 2 == 0) {
                Long left = Long.valueOf(s.substring(0, s.length() / 2));
                Long right = Long.valueOf(s.substring(s.length() / 2));
                newStage.add(left);
                newStage.add(right);
            } else {
                newStage.add(2024 * stone);
            }
        }

        return newStage;
    }

    public static long blink(long num, int times) {
        return 0;
    }
}
