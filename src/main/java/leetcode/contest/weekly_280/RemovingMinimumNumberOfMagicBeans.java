package leetcode.contest.weekly_280;

import leetcode.MinimumRemoveToMakeValidParentheses;

import java.util.Arrays;

public class RemovingMinimumNumberOfMagicBeans {
	public long minimumRemoval(int[] beans) {
		Arrays.sort(beans);

		int n = beans.length;

		long [] ps = new long[n];

		ps[0] = beans[0];

		for (int i = 1; i < n; i++) {
			ps[i] = ps[i-1] + beans[i];
		}

		long min = Long.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			long removed = 0;
			// 앞부분
			if (i > 0) {
				removed = ps[i-1];
			}

			// 뒷부분
			if (i < n-1) {
				removed += ((ps[n-1] - ps[i]) - ((long)beans[i] * (long)(n-i-1)));
			}

			min = Math.min(min, removed);
		}

		return min;
	}

	public static void main(String[] args) {
		int [] beans = {3,4};

		RemovingMinimumNumberOfMagicBeans removingMinimumNumberOfMagicBeans = new RemovingMinimumNumberOfMagicBeans();
		System.out.println(removingMinimumNumberOfMagicBeans.minimumRemoval(beans));

	}
}
