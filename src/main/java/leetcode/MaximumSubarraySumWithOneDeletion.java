package leetcode;

public class MaximumSubarraySumWithOneDeletion {
    public int maximumSum(int[] arr) {
        int oneDeletionBefore = Integer.MIN_VALUE;
        int nonDeletionBefore = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (oneDeletionBefore == Integer.MIN_VALUE) {
                oneDeletionBefore = Math.max(arr[i], nonDeletionBefore);
            } else {
                oneDeletionBefore = Math.max(Math.max(oneDeletionBefore + arr[i], nonDeletionBefore), arr[i]);
            }

            nonDeletionBefore = Math.max((nonDeletionBefore == Integer.MIN_VALUE ? 0 : nonDeletionBefore) + arr[i], arr[i]);
            max = Math.max(max, Math.max(oneDeletionBefore, nonDeletionBefore));
        }

        return max;
    }

    public static void main(String[] args) {
        int [] arr = {2,1,-2,-5,-2};

        MaximumSubarraySumWithOneDeletion maximumSubarraySumWithOneDeletion = new MaximumSubarraySumWithOneDeletion();
        System.out.println(maximumSubarraySumWithOneDeletion.maximumSum(arr));
    }
}
