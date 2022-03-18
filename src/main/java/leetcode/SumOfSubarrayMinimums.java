package leetcode;

import java.util.Stack;

public class SumOfSubarrayMinimums {
	public int sumSubarrayMins(int[] A) {
		int len = A.length;
		Stack<Integer> stack = new Stack<>();
		int[] left = new int[len];
		int[] right = new int[len];
		for (int i = 0; i < A.length; i++) {
			left[i] = i + 1;
			right[i] = len - i;
		}
		// previous less element
		for (int i = 0; i < len; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				stack.pop();
			}
			left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
			stack.push(i);
		}
		//next less element
		stack = new Stack<>();
		for (int i = 0; i < len; i++) {
			while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
				right[stack.peek()] = i - stack.peek();
				stack.pop();
			}
			stack.push(i);
		}
		long ans = 0;
		long mod = (int)1e9 + 7;
		for (int i = 0; i < len; i++) {
			ans = (ans + (long)A[i] * left[i] * right[i]) % mod;
		}
		return (int)ans;

	}

	public static void main(String[] args) {
		int[] arr = {1, 9, 7, 8, 3, 4, 6, 1};

		SumOfSubarrayMinimums sumOfSubarrayMinimums = new SumOfSubarrayMinimums();
		System.out.println(sumOfSubarrayMinimums.sumSubarrayMins(arr));
	}
}
