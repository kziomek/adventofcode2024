package hackerrank.diagonaldifference;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     Given a square matrix, calculate the absolute difference between the sums of its diagonals.

    For example, the square matrix  is shown below:

    1 2 3
    4 5 6
    9 8 9
    The left-to-right diagonal = .
    The right-to-left diagonal = .
    Their absolute difference is .

    Function description

    Complete the  function with the following parameter:

    : a 2-D array of integers
    Return

    : the absolute difference in sums along the diagonals
    Input Format

    The first line contains a single integer, , the number of rows and columns in the square matrix .
    Each of the next  lines describes a row, , and consists of  space-separated integers .


    Sample Input

    STDIN      Function
    -----      --------
    3           arr[][] sizes n = 3, m = 3
    11 2 4     arr = [[11, 2, 4], [4, 5, 6], [10, 8, -12]]
    4 5 6
    10 8 -12

    Sample Output
    15
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int size = arr.size();
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;

        for (int i = 0; i < size; i++) {
            primaryDiagonal += arr.get(i).get(i);
            secondaryDiagonal += arr.get(i).get(size - 1 - i);
        }
        return Math.abs(primaryDiagonal - secondaryDiagonal);
    }
}

public class DiagonalDifference {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/hackerrank/output.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.diagonalDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

