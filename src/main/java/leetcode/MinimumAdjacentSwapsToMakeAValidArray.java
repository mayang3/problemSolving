package leetcode;

/**
 * @author neo82
 */
public class MinimumAdjacentSwapsToMakeAValidArray {
    public static void main(String[] args) {
        int [] nums = {3,1,5,1,5,3};

        MinimumAdjacentSwapsToMakeAValidArray minimumAdjacentSwapsToMakeAValidArray = new MinimumAdjacentSwapsToMakeAValidArray();
        System.out.println(minimumAdjacentSwapsToMakeAValidArray.minimumSwaps(nums));
    }

    public int minimumSwaps(int[] nums) {
        int N = nums.length;
        int max = 0;
        int min = N - 1;

        for (int i = 0; i < N; i++) {
            if (nums[i] >= nums[max]) {
                max = i;
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            if (nums[i] <= nums[min]) {
                min = i;
            }
        }

        if (min < max) {
            return min + (N - max - 1);
        }

        return min + (Math.max(N - max - 2, 0));
    }
}
