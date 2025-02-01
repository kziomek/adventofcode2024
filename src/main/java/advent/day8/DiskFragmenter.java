package advent.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiskFragmenter {

    public static void main(String[] args) throws IOException {
//        String input = Files.readAllLines(Path.of("src/main/resources/day8/example.txt")).get(0);
                String input = Files.readAllLines(Path.of("src/main/resources/day8/my-input.txt")).get(0);
        System.out.println(input);

        int[] arr = decompressInput(input);

        compact(arr);
        System.out.println(arr);
        long result = calculateChecksum(arr);
        System.out.println("Part 1 result " + result);
    }

    private static long calculateChecksum(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                break;
            }
            sum += (long)i * arr[i];
        }
        return sum;
    }

    private static void compact(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] != -1) {
                i++;
                continue;
            }
            if (arr[j] == -1) {
                j--;
                continue;
            }
            arr[i] = arr[j];
            arr[j] = -1;
            i++;
            j--;
        }
    }

    private static int[] decompressInput(String input) {
        int size = calculateSize(input);
        System.out.println(size);
        int[] arr = new int[size];
        int idx = 0;
        int id = 0;
        boolean isFile = true;
        for (char c : input.toCharArray()) {
            int blockSize = Character.getNumericValue(c);
            for (int i = 0; i < blockSize; i++) {
                if (isFile) {
                    arr[idx] = id;
                } else {
                    arr[idx] = -1;
                }
                idx++;
            }
            isFile = !isFile;
            if (isFile) {
                id++;
            }
        }

        System.out.println(arr);
        return arr;
    }

    private static int calculateSize(String input) {
        int size = 0;
        for (char c : input.toCharArray()) {
            size += Character.getNumericValue(c);
        }
        return size;
    }
}
