package leetcode;

import java.util.*;

public class SlidingWidowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		int resIndex = 0;
		int [] res = new int[nums.length - k + 1];

		Deque<Integer> deque = new ArrayDeque<>();

		for (int i = 0; i < nums.length; i++) {
			// if there are indices in deque, then poll items.
			// 현재 알고리즘으로는 큐 안에 최대 사이즈가 윈도우의 사이즈를 넘어서는 것이 가능하다.
			while (!deque.isEmpty() && deque.peek() < i - k + 1) {
				deque.pollFirst();
			}

			// 큐의 마지막에 현재 값보다 적은 값이 있다면 모두 빼내준다.
			// 즉, 현재값보다 큰 값이 들어오면 기존에 들어가 있던 값들중에 현재값보다 작은 값들은 모두 빼준다.
			// 위와 같은 과정을 거치고 나면, 현재 큐에는 앞에서부터 순서대로 큰 값이 들어가있게 된다.
			while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
				deque.pollLast();
			}

			// offer
			deque.offer(i);

			if (i >= k - 1) {
				res[resIndex++] = nums[deque.peekFirst()];
			}
		}

		return res;
	}


	public static void main(String[] args) {
		int [] nums = {7, 2, 4};
		int k = 2;

		SlidingWidowMaximum slidingWidowMaximum = new SlidingWidowMaximum();
		System.out.println(Arrays.toString(slidingWidowMaximum.maxSlidingWindow(nums, k)));
	}
}
