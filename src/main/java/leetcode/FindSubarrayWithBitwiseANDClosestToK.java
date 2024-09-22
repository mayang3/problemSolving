package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class FindSubarrayWithBitwiseANDClosestToK {
    public static void main(String[] args) {
        int [] nums = {1,2,4,5};
        int k = 3;

        FindSubarrayWithBitwiseANDClosestToK findSubarrayWithBitwiseANDClosestToK = new FindSubarrayWithBitwiseANDClosestToK();
        System.out.println(findSubarrayWithBitwiseANDClosestToK.minimumDifference(nums, k));
    }


    private long cal(int[] fr) {
        long ans = 0;

        for (int i = 0; i < 32; ++i) {
            if (fr[i] == 0) { // Setting the bit if zero count is 0
                ans += (1L << i);
            }
        }

        return ans;
    }

    // Function for adding to the window
    private void add(int[] fr, int val) {
        for (int i = 0; i < 32; ++i) {
            if (((val >> i) & 1) == 0) { // Checking if the bit is zero
                fr[i]++;
            }
        }
    }

    // Function for removing from the window
    private void sub(int[] fr, int val) {
        for (int i = 0; i < 32; ++i) {
            if (((val >> i) & 1) == 0) { // Checking if the bit is zero
                fr[i]--;
            }
        }
    }


    public int minimumDifference(int[] nums, int k) {
        int N = nums.length;

        int high = Arrays.stream(nums).max().orElse(0);

        if (N == 1) {
            return Math.abs(k - nums[0]);
        }

        if (k >= high) { // If k > max value, there is no point in further calculation as bitwise AND always reduces the value
            return k - high;
        }

        long ans = (long) 1e9 + 1;;
        int [] bits = new int[32];
        int left = 0;
        int right = 1;
        long cur = nums[0];
        add(bits, nums[0]);

        while (right < N) {
            // 한자리 수의 값일 경우 아래 2번 로직에서 정확한 값을 복원할 수 없다.
            ans = Math.min(ans, Math.abs(cur - k));

            while (cur > k && right < N) {
                add(bits, nums[right++]);
                cur = cal(bits);
                ans = Math.min(ans, Math.abs(cur - k));
            }

            // 예를 들어, 여기서 값을 복원하고 다음 loop 를 타기 때문에 여기 안에 있는 ans = Math.min(ans, Math.abs(cur - k)); 로직과
            // while 문 맨 앞에 있는 로직은 엄밀히 얘기하면 중복이다.
            // 하지만, left 와 right 가 하나 차이이고 바로 이 sub 로직을 타는 경우,
            // 하나의 element 의 0 의 개수가 더해지고 빼지기 때문에, 모든 bit 의 값이 0 이 되어 정확히 복원되지 않는다.
            // 이런 경우에는 맨 위의 while 문을 타게 되어 정확히 복원된 값이 미리 계산되기 때문이 이 로직에서 정확한 값이 복원되지 않더라도 별 문제가 없다.
            while (cur < k && left < right) {
                sub(bits, nums[left++]);
                cur = cal(bits);
                ans = Math.min(ans, Math.abs(cur - k));
            }

            if (cur == k) {
                return 0;
            }
        }

        return (int)ans;
    }
}
