package leetcode;

/**
 * @author neo82
 */
public class SumOfDigitDifferenceOfAllPairs {
    public long sumDigitDifferences(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int num_dgt = String.valueOf(nums[0]).length();
        long[][] digitCount = new long[num_dgt][10];

        for (int num : nums) {
            String num_str = String.valueOf(num);
            for (int i = 0; i < num_dgt; i++) {
                digitCount[i][num_str.charAt(i) - '0']++;
            }
        }

        long total_diff = 0;

        for (int i = 0; i < num_dgt; i++) {
            for (int d = 0; d < 10; d++) {
                long count = digitCount[i][d];
                total_diff += count * (nums.length - count);
            }
        }
        return total_diff / 2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 3, 3};

        SumOfDigitDifferenceOfAllPairs sumOfDigitDifferenceOfAllPairs = new SumOfDigitDifferenceOfAllPairs();
        System.out.println(sumOfDigitDifferenceOfAllPairs.sumDigitDifferences(nums));
    }
}
