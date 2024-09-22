package leetcode;

import java.util.List;

/**
 * @author neo82
 */
public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        int startLen = String.valueOf(low).length();
        int endLen = String.valueOf(high).length();


        for (int len = startLen; len < endLen + 1; len++) {
            for (int num = 1; num < 10 ; num++) {

            }
        }


        return null;
    }

    public static void main(String[] args) {

    }
}
