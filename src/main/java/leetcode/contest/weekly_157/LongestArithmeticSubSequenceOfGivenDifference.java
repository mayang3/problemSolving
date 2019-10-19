package leetcode.contest.weekly_157;

import java.util.HashMap;

public class LongestArithmeticSubSequenceOfGivenDifference {
	public int longestSubsequence(int[] arr, int difference) {
		HashMap<Integer, Integer> dp = new HashMap<>();
		int longest = 0;
		for(int i=0; i<arr.length; i++) {
			dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
			longest = Math.max(longest, dp.get(arr[i]));
		}
		return longest;
	}

	public static void main(String[] args) {
		int [] arr = {1,5,7,8,5,3,4,2,1};
		int difference = -2;

		LongestArithmeticSubSequenceOfGivenDifference sequence = new LongestArithmeticSubSequenceOfGivenDifference();

		int i = sequence.longestSubsequence(arr, difference);

		System.out.println(i);
	}
}
