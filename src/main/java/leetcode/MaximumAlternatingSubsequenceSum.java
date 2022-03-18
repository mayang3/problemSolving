package leetcode;

public class MaximumAlternatingSubsequenceSum {

    public long maxAlternatingSum(int[] A) {
        long odd = 0, even = 0;
        for (int a: A) {
            even = Math.max(even, odd + a);
            odd = even - a;
        }
        return even;
    }

    public static void main(String[] args) {
        int [] nums = {6,2,1,2,4,5};

        MaximumAlternatingSubsequenceSum maximumAlternatingSubsequenceSum = new MaximumAlternatingSubsequenceSum();
        System.out.println(maximumAlternatingSubsequenceSum.maxAlternatingSum(nums));

        double a = 0.1;

        System.out.println(a);
    }
}
