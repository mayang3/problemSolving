package leetcode;

import java.util.*;

public class SlidingWindowMedian {
	public double[] medianSlidingWindow(int[] nums, int k) {
		double [] medians = new double[nums.length - k + 1];

		// priority queue 의 comparator 를 다음과 같이 작성하면 Integer.MIN_VALUE 에서 오류가 발생한다. 이유가 뭘까?
		// PriorityQueue<Integer> leftPq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		PriorityQueue<Integer> leftPq = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> rightPq = new PriorityQueue<>();

		for (int i = 0; i < k; i++) {
			rightPq.add(nums[i]);
		}

		// task to balance
		for (int i = 0; i < (k / 2); i++) {
			leftPq.add(rightPq.poll());
		}

		int resIndex = 0;
		medians[resIndex++] = getMedian(leftPq, rightPq, k);

		for (int i = k; i < nums.length; i++) {

			boolean remove = leftPq.remove(nums[i - k]);
			if (remove == false) {
				rightPq.remove(nums[i-k]);
			}

			// insert
			if (leftPq.isEmpty() == false && leftPq.peek() > nums[i]) {
				leftPq.add(nums[i]);
			} else {
				rightPq.add(nums[i]);
			}

			reBalance(leftPq, rightPq, k);

			medians[resIndex++] = getMedian(leftPq, rightPq, k);
		}

		return medians;
	}

	private void reBalance(PriorityQueue<Integer> leftPq, PriorityQueue<Integer> rightPq, int k) {
		if (k % 2 == 0) {
			while (leftPq.size() > k / 2) {
				rightPq.add(leftPq.poll());
			}

			while (rightPq.size() > k / 2) {
				leftPq.add(rightPq.poll());
			}
		} else {
			while (leftPq.size() > k / 2) {
				rightPq.add(leftPq.poll());
			}

			while (rightPq.size() > k / 2  + 1) {
				leftPq.add(rightPq.poll());
			}
		}
	}

	private double getMedian(PriorityQueue<Integer> leftPq, PriorityQueue<Integer> rightPq, int k) {
		if (k % 2 == 0) {
			return ((double)leftPq.peek() + rightPq.peek()) / (double)2;
		}

		if (leftPq.size() > rightPq.size()) {
			return leftPq.peek();
		}

		return rightPq.peek();
	}

	public static void main(String[] args) {

		System.out.println(Integer.MIN_VALUE);

		int [] nums = {-2147483648,-2147483648,2147483647,-2147483648,1,3,-2147483648,-100,8,17,22,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
		int k = 6;

		SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
		System.out.println(Arrays.toString(slidingWindowMedian.medianSlidingWindow(nums, k)));
	}
}
