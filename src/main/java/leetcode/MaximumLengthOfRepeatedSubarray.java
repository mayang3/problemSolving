package leetcode;

public class MaximumLengthOfRepeatedSubarray {
	public int findLength(int[] A, int[] B) {
		int max = 0;

		int [][] dp = new int[A.length + 1][B.length + 1];

		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= B.length; j++) {
				if (A[i-1] == B[j-1]) {
					dp[i][j] = 1 + dp[i-1][j-1];
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int [] A = {1,2,3,2,1};
		int [] B = {3,2,1,4,7};

		MaximumLengthOfRepeatedSubarray maximumLengthOfRepeatedSubarray= new MaximumLengthOfRepeatedSubarray();
		System.out.println(maximumLengthOfRepeatedSubarray.findLength(A, B));
	}
}
