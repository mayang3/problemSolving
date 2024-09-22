package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neo82
 */
public class MinimumLengthOfAnagramConcatenation {
    // Function to calculate the greatest common divisor (GCD)
    private int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int minAnagramLength(String s) {
        // Create a frequency count of characters in the given string
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Find the greatest common divisor (GCD) of all character counts
        int commonDivisor = charCount.get(s.charAt(0)); // Initialize with the first character's count
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            commonDivisor = calculateGCD(commonDivisor, entry.getValue());
        }

        // Return the length of the string divided by the GCD
        return s.length() / commonDivisor;
    }


    public static void main(String[] args) {
        MinimumLengthOfAnagramConcatenation minimumLengthOfAnagramConcatenation = new MinimumLengthOfAnagramConcatenation();
        System.out.println(minimumLengthOfAnagramConcatenation.minAnagramLength("aabb"));
    }
}
