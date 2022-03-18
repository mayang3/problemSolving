package leetcode;

public class FirstMissingPositive {


    public int firstMissingPositive(int[] nums) {

        int n = nums.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = 987654321;
            }

            max = Math.max(max, nums[i]);
        }

        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]);

            if ((idx-1) >= n) {
                continue;
            }

            if (nums[idx-1] > 0) {
                nums[idx-1] *= -1;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i+1;
            }
        }

        return max + 1;
    }

    public static void main(String[] args) {
        int [] nums = {-1,-3,-4,0,2,2,2,3,4,5,6,7,8,9,10,11,13};

        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        System.out.println(firstMissingPositive.firstMissingPositive(nums));
    }
}
