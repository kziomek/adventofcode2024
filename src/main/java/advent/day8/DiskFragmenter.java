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
        print(arr, "Decompressed");

        //        compactPart1(arr);
        compactPart2(arr);
        System.out.println(arr);
        print(arr, "Compacted");
        long result = calculateChecksum(arr);
        System.out.println("Part 1 result " + result);
    }

    private static void print(int[] arr, String title) {
        System.out.println(title);
        for (int i : arr) {
            if (i == -1) {
                System.out.print('.');
            } else {
                System.out.print(i);
            }

            System.out.print(" ");
        }
        System.out.println();
    }

    private static long calculateChecksum(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                continue;
            }
            sum += (long) i * arr[i];
        }
        return sum;
    }

    private static void compactPart2(int[] arr) {
        int j = arr.length - 1;
        while (j > 0) {
            if (arr[j] != -1) {
                //try to fit
                int fileBlockSize = countSizeOfFileBlock(arr, j);
                tryMovingBlock(arr, j, fileBlockSize);

                j -= fileBlockSize;
            } else {
                j--;
            }
        }
    }

    private static void tryMovingBlock(int[] arr, int j, int fileBlockSize) {
        int i = 0;
        while (i < j) {
            if (arr[i] != -1) {
                i++;
                continue;
            }
            if (arr[i] == -1) {
                int emptySpaceSize = countSizeOfEmptySpace(arr, i);
                if (emptySpaceSize >= fileBlockSize) {
                    moveFile(arr, i, j, fileBlockSize);
                    break;
                } else {
                    i+=emptySpaceSize;
                }
            }
        }
    }

    private static void moveFile(int[] arr, int i, int j, int fileBlockSize) {
        for (int k = 0; k < fileBlockSize; k++) {
            arr[i + k] = arr[j-k];
            arr[j - k] = -1;
        }
    }

    private static int countSizeOfEmptySpace(int[] arr, int i) {
        int size = 0;
        while (i + size < arr.length && arr[i + size] == arr[i]) {
            size++;
        }
        return size;
    }

    private static int countSizeOfFileBlock(int[] arr, int j) {
        int size = 0;
        while (j - size >= 0 && arr[j - size] == arr[j]) {
            size++;
        }
        return size;
    }

    private static void compactPart1(int[] arr) {
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
