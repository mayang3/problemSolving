package leetcode;

public class JumpGame {
	// O(n^2)
//	public boolean canJump(int[] nums) {
//		int n = nums.length;
//
//		boolean [] dp = new boolean[n];
//		dp[n-1] = true;
//
//		for (int i = n-2; i >= 0 ; i--) {
//			int furthestJump = Math.min(i + nums[i], n-1);
//
//			for (int j = i+1; j <= furthestJump; j++) {
//				if (dp[j]) {
//					dp[i] = true;
//					break;
//				}
//			}
//		}
//
//		return dp[0];
//	}

	// O(n) greedy solution
	// 현재 위치에서 뛰어넘을 수 있는 길이가 마지막의 길이보다 같거나 크다면 현재 위치에서는 항상 뛰어넘을 수 있다.
	public boolean canJump(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}
}
