package leetcode;

import java.util.Arrays;
import java.util.Random;

public class ShuffleInArray {
    static class Solution {

        int [] nums;
        int [] shuffle;
        Random r = new Random();

        public Solution(int[] nums) {
            this.nums = nums;
            this.shuffle = Arrays.copyOf(nums, nums.length);
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            for (int i = this.shuffle.length; i > 0; i--) {
                int j = r.nextInt(i);

                int temp = this.shuffle[i-1];
                this.shuffle[i-1] = this.shuffle[j];
                this.shuffle[j] = temp;
            }

            return this.shuffle;
        }
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3};

        Solution solution = new Solution(nums);

        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));

        System.out.println(new Random().nextInt(100));
    }
}
