package hackerrank.plusminus;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        int positive =0, negative =0, zero =0;
        BigDecimal devisor = new BigDecimal(arr.size());
        for (int num :arr) {
            if (num > 0 ) {
                positive++;
            } else if (num < 0) {
                negative++;
            } else {
                zero++;
            }
        }
        BigDecimal posRatio = new BigDecimal(positive).setScale(6, RoundingMode.HALF_UP).divide(devisor, RoundingMode.HALF_UP);
        BigDecimal negRatio = new BigDecimal(negative).setScale(6, RoundingMode.HALF_UP).divide(devisor, RoundingMode.HALF_UP);
        BigDecimal zeroRatio = new BigDecimal(zero).setScale(6, RoundingMode.HALF_UP).divide(devisor, RoundingMode.HALF_UP);

        System.out.println(posRatio);
        System.out.println(negRatio);
        System.out.println(zeroRatio);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        Result.plusMinus(arr);

        bufferedReader.close();
    }
}
