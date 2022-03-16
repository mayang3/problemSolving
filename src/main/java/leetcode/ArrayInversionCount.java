package leetcode;

import java.io.UnsupportedEncodingException;

public class ArrayInversionCount {
	public int solution(int[] A) {
		SegmentTree segmentTree = new SegmentTree(Integer.MIN_VALUE, Integer.MAX_VALUE);

		int count = 0;

		for (int i = A.length - 1; i >= 0; i--) {
			int val = A[i];

			if (i < A.length - 1) {
				count += segmentTree.query(val);
			}
			segmentTree.update(val);
		}

		return count;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		int[] A = {-1, 6, 3, 4, 7, 4};

		ArrayInversionCount arrayInversionCount = new ArrayInversionCount();
		System.out.println(arrayInversionCount.solution(A));
	}

	static class SegmentTree {
		long min;
		long max;
		int count;
		SegmentTree left;
		SegmentTree right;

		public SegmentTree(long min, long max) {
			this.min = min;
			this.max = max;
		}

		public void update(long val) {
			this.count += 1;

			if (min == max) {
				return;
			}

			long mid = ((long)(max - min) / 2L) + min;

			if (val <= mid) {
				if (this.left == null) {
					left = new SegmentTree(min, mid);
				}
				left.update(val);
			} else {
				if (this.right == null) {
					right = new SegmentTree(mid + 1, max);
				}
				right.update(val);
			}
		}

		public int query(long val) {
			if (min >= val) {
				return 0;
			}

			if (max < val) {
				return count;
			}

			return (left == null ? 0 : left.query(val))
				+ (right == null ? 0 : right.query(val));
		}
	}
}
