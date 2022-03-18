package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		LinkedList<Integer> counts = new LinkedList<>();

		SegmentTree segmentTree = new SegmentTree((int)-1e4, (int)1e4);

		for (int i = nums.length-1; i >= 0; i--) {
			counts.addFirst(segmentTree.getCount(nums[i]));
			segmentTree.add(nums[i]);
		}

		return counts;
	}

	static class SegmentTree {
		int min;
		int max;
		int count;
		SegmentTree left;
		SegmentTree right;

		public SegmentTree(int min, int max) {
			this.min = min;
			this.max = max;
		}

		public void add(int num) {
			this.count++;

			if (min == max) {
				return;
			}

			int mid = ((max - min) / 2) + min;

			if (num <= mid) {
				if (left == null) {
					left = new SegmentTree(min, mid);
				}

				left.add(num);
			} else {
				if (right == null) {
					right = new SegmentTree(mid+1, max);
				}

				right.add(num);
			}
		}

		public int getCount(int num) {
			if (min >= num) {
				return 0;
			}

			if (max < num) {
				return count;
			}

			return (left == null ? 0 : left.getCount(num)) + (right == null ? 0 : right.getCount(num));
		}
	}

	public static void main(String[] args) {
		int [] nums = {-1,-3};

		CountOfSmallerNumbersAfterSelf countOfSmallerNumbersAfterSelf = new CountOfSmallerNumbersAfterSelf();
		System.out.println(countOfSmallerNumbersAfterSelf.countSmaller(nums));
	}
}
