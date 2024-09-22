package leetcode;

/**
 * @author neo82
 */
public class FindTheMaximumLengthOfValidSubsequence {
    public int maximumLength(int[] nums) {
        int parity = nums[0] % 2;
        int even = 0, odd = 0, both = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }

            if (num % 2 == parity) {
                both++;
                parity = 1 - parity;
            }
        }

        return Math.max(both, Math.max(even, odd));
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4};
        // [1,0,1,0]

        FindTheMaximumLengthOfValidSubsequence findTheMaximumLengthOfValidSubsequence = new FindTheMaximumLengthOfValidSubsequence();
        System.out.println(findTheMaximumLengthOfValidSubsequence.maximumLength(nums));
    }
}
