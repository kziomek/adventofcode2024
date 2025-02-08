package advent.day11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class PlutionianPebbles {

    public static void main(String[] args) throws IOException {
//        String line = Files.readAllLines(Path.of("src/main/resources/day11/example.txt")).getFirst();
                String line = Files.readAllLines(Path.of("src/main/resources/day11/my-input.txt")).getFirst();
        List<String> stones = Arrays.stream(line.split(" ")).toList();

        Map<String, Long> stoneMap = toMap(stones);
        System.out.println(stoneMap);

        Map<String, Long> blinkedStoneMap = blinkStoneMap(stoneMap, 75);
        long count = count(blinkedStoneMap);
        System.out.println("result " + count);
    }

    private static long count(Map<String, Long> blinkedStoneMap) {
        return blinkedStoneMap.values().stream().reduce(Long::sum).get();
    }

    private static Map<String, Long> blinkStoneMap(Map<String, Long> stoneMap, int blinks) {
        Map<String, Long> currStoneMap = stoneMap;
        for (int j = 0; j < blinks; j++) {
            Map<String, Long> newStage = new HashMap<>();
            for (Map.Entry<String, Long> entry : currStoneMap.entrySet()) {
                long count = entry.getValue();
                List<String> blink = blink(entry.getKey());
                for (String stone : blink) {
                    if (!newStage.containsKey(stone)) {
                        newStage.put(stone, 0L);
                    }
                    newStage.put(stone, newStage.get(stone) + count);
                }
            }
            currStoneMap = newStage;
            System.out.println(newStage);
        }
        return currStoneMap;
    }

    private static Map<String, Long> toMap(List<String> stones) {
        Map<String, Long> map = new HashMap<>();
        for (String stone : stones) {
            if (!map.containsKey(stone)) {
                map.put(stone, 0L);
            }
            map.put(stone, map.get(stone) + 1);
        }
        return map;
    }

    private static List<String> blink(String stone) {
        List<String> newStones = new ArrayList<>();
        if (stone.equals("0")) {
            newStones.add("1");
            return newStones;
        }
        if (stone.length() % 2 == 0) {
            String left = stone.substring(0, stone.length() / 2);
            String right = Long.toString(Long.parseLong(stone.substring(stone.length() / 2)));
            newStones.add(left);
            newStones.add(right);
        } else {
            newStones.add("" + (2024 * Long.parseLong(stone)));
        }

        return newStones;
    }
}
