package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FizzBuzz {
    public List<String> fizzBuzz(int n) {

        String [] output = new String[n];

        for (int i = 1; i < n+1; i++) {
            if (i % 3 == 0) {
                output[i-1] = "Fizz";
            }

            if (i % 5 == 0) {
                output[i-1] = (output[i-1] == null ? "" : output[i-1]) + "Buzz";
            }

            if (output[i-1] == null) {
                output[i-1] = String.valueOf(i);
            }
        }

        return Arrays.asList(output);
    }

    public static void main(String[] args) {
        int gcd = gcd(3, 5);

        System.out.println(gcd);
    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }

        return gcd(b % a, a);
    }

}
