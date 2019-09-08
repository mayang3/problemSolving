package leetcode;

import java.util.*;

public class DailyTemperatures {
	public static void main(String[] args) {
		int [] T = {73, 74, 75, 71, 69, 72, 76, 73};

		DailyTemperatures dailyTemperatures = new DailyTemperatures();

		int[] ret = dailyTemperatures.dailyTemperatures(T);

		System.out.println(Arrays.toString(ret));
	}

	/**
	 * Time complexity O(N)
	 *
	 * 각 인덱스에서 stack 의 push/pop 연산은 한번씩만 일어나게된다. 그러므로 O(N) 이다.
	 *
	 * @param T
	 * @return
	 */
	public int[] dailyTemperatures(int[] T) {
		int[] ans = new int[T.length];

		Stack<Integer> stack = new Stack();

		// compute from last index of T
		for (int i = T.length - 1; i >= 0; --i) {

			// discard values in stack lower than current index value
			while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
				stack.pop();
			}

			// top of stack is always closet value greater than current index value.
			ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;

			stack.push(i);
		}

		return ans;
	}
}
