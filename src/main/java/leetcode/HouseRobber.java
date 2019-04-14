package leetcode;

/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 */

/**
 * @author baejunbeom
 */
public class HouseRobber {

	/**
	 * dp 풀이법..
	 *
	 * 핵심 개념은, 이전의 짝수 번째 인덱스의 max 값이랑, 이전에 홀수번째 인덱스의 max 값을 서로 비교해보는 것..
	 *
	 * @param nums
	 * @return
	 */
	public int rob(int[] nums) {

		int a = 0;
		int b = 0;

		for (int i = 0 ; i<nums.length ; i++) {
			if (i % 2 == 0) {
				a = Math.max(a + nums[i], b);
			} else {
				b = Math.max(a, b + nums[i]);
			}
		}

		return Math.max(a, b);
	}

	public static void main (String[] args) {
		HouseRobber houseRobber = new HouseRobber();
		int rob = houseRobber.rob(new int[] {300, 1, 2, 400, 4, 6, 8, 500});

		System.out.println(rob);
	}
}
