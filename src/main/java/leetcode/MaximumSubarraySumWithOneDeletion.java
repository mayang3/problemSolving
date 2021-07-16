package leetcode;

public class MaximumSubarraySumWithOneDeletion {
    public int maximumSum(int[] arr) {
        // 앞선 최대값중 값 하나를 지운 최대값
        int oneDeletionBefore = Integer.MIN_VALUE;
        // 앞선 최대값중 값을 지우지 않은 최대값
        int nonDeletionBefore = Integer.MIN_VALUE;
        // 결과 최대값
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {

            // 1. i 이전의 값들중 지운 값이 있는 최대값이다.
            // 1-1. 이미 앞에서 한개가 지워진 경우는 현재 위치의 값을 더해줘야만 현재 위치에서의 최대값이 된다.
            // 1-2. 앞에서 한개도 지우지 않은 최대값은 현재 위치의 값을 더하지 않아야만 현재 위치의 최대값이 된다.
            // 위의 둘중 더 큰 값이, 현재 위치에서 하나의 값을 지웠을때의 최대값이 된다.
            oneDeletionBefore = Math.max((oneDeletionBefore == Integer.MIN_VALUE ? 0 : oneDeletionBefore) + arr[i], nonDeletionBefore);

            // 2. i 이전의 값들중 지운 값이 없는 최대값이다.
            // 해당 최대값이 arr[i] 를 더한값과 arr[i] 자신과 비교해서 더 큰 녀석이 현재 위치에서의 새로운 최대값이 된다.
            nonDeletionBefore = Math.max((nonDeletionBefore == Integer.MIN_VALUE ? 0 : nonDeletionBefore) + arr[i], arr[i]);

            max = Math.max(max, Math.max(oneDeletionBefore, nonDeletionBefore));
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 0, 3};

        MaximumSubarraySumWithOneDeletion maximumSubarraySumWithOneDeletion = new MaximumSubarraySumWithOneDeletion();
        System.out.println(maximumSubarraySumWithOneDeletion.maximumSum(arr));
    }
}
