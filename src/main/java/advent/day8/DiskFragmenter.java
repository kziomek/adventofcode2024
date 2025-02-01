package advent.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiskFragmenter {

    public static void main(String[] args) throws IOException {
        String input = Files.readAllLines(Path.of("src/main/resources/day8/example.txt")).get(0);
        //        String input = Files.readAllLines(Path.of("src/main/resources/day8/my-input.txt")).get(0);
        System.out.println(input);

        char[] arr = decompressInput(input);
        compact(arr);
        System.out.println(arr);
        int result = calculateChecksum(arr);
        System.out.println("Part 1 result " + result);
    }

    private static int calculateChecksum(char[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                break;
            }
            sum += i * Character.getNumericValue(arr[i]);
        }
        return sum;
    }

    private static void compact(char[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] != '.') {
                i++;
                continue;
            }
            if (arr[j] == '.') {
                j--;
                continue;
            }
            arr[i] = arr[j];
            arr[j] = '.';
            i++;
            j--;
        }
    }

    private static char[] decompressInput(String input) {
        int size = calculateSize(input);
        System.out.println(size);
        char[] arr = new char[size];
        int idx = 0;
        int id = 0;
        boolean isFile = true;
        for (char c : input.toCharArray()) {
            int value = Character.getNumericValue(c);
            for (int i = 0; i < value; i++) {
                if (isFile) {
                    arr[idx] = Character.forDigit(id, 10);
                } else {
                    arr[idx] = '.';
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
