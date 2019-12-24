package leetcode;

/**
 * @author neo82
 */
public class HouseRobber {
	public int rob(int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		} else if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int before2 = nums[0];
		int before1 = Math.max(nums[0], nums[1]);
		int cur = 0;

		for (int i = 2; i < nums.length; i++) {
			cur = Math.max(before2 + nums[i], Math.max(before1, nums[i]));

			before2 = before1;
			before1 = cur;
		}

		return cur;
	}

	public static void main (String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		int rob = houseRobber.rob(new int[] {1,2,3,1});

		System.out.println(rob);
	}
}
