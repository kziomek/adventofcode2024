package advent.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorianHysteria {
    public static void main(String[] args) throws IOException {
        //        List<String[]> list = Files.readAllLines(Path.of("src/main/resources/day1/example.txt"))
        List<String[]> list = Files.readAllLines(Path.of("src/main/resources/day1/my-input.txt"))
            .stream()
            .map(el -> el.split("   "))
            .toList();

        int[] arrA = new int[list.size()];
        int[] arrB = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arrA[i] = Integer.parseInt(list.get(i)[0]);
            arrB[i] = Integer.parseInt(list.get(i)[1]);
        }

        // part1
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        int resultPart1 = 0;
        for (int i = 0; i < arrA.length; i++) {
            resultPart1 += Math.abs(arrA[i] - arrB[i]);
        }
        System.out.println("result Part 1 " + resultPart1);

        // part 2
        Map<Integer, Integer> counterMap = toNumberCount(arrB);
        int resultPart2 = 0;
        for (int i = 0; i < arrA.length; i++) {
            int num = arrA[i];
            int multiplier = counterMap.getOrDefault(num, 0);
            resultPart2 += num * multiplier;
        }
        System.out.println("result Part 2 " + resultPart2);
    }

    private static Map<Integer, Integer> toNumberCount(int[] arrB) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int num : arrB) {
            if (!counterMap.containsKey(num)) {
                counterMap.put(num, 1);
            } else {
                counterMap.put(num, counterMap.get(num) + 1);
            }
        }
        return counterMap;
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
