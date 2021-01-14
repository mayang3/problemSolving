package leetcode;

import java.util.Arrays;
import java.util.Random;

public class RandomPickWithWeight {

	static class Solution {
		int [] pSum;
		Random r = new Random();

		public Solution(int[] w) {
			pSum = new int[w.length];

			for (int i = 1; i < w.length; i++) {
				pSum[i] = pSum[i-1] + w[i];
			}
		}
		public int pickIndex() {
			int idx = r.nextInt(pSum[pSum.length - 1]) + 1;
			int i = Arrays.binarySearch(pSum, idx);
			return i >= 0 ? i : -i-1;
		}
	}

	public static void main(String[] args) {
		int [] w = {2, 5, 3, 4};

		Solution solution = new Solution(w);

		for (int i = 0; i < 50; i++) {
			System.out.println(solution.pickIndex());
		}
	}
}
