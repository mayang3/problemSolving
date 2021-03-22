package leetcode;

import java.util.*;

public class NextGreaterElement2 {
	public int[] nextGreaterElements(int[] A) {
		int n = A.length;
		Stack<Integer> stack = new Stack<>();
		int [] res = new int[n];
		Arrays.fill(res, -1);

		for (int i = 0; i < (n * 2); i++) {
			while (stack.isEmpty() == false && A[stack.peek()] < A[i % n]) {
				res[stack.pop()] = A[i % n];
			}

			stack.push(i % n);
		}

		return res;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,1};

		NextGreaterElement2 nextGreaterElement2 = new NextGreaterElement2();
		System.out.println(Arrays.toString(nextGreaterElement2.nextGreaterElements(nums)));
	}
}
