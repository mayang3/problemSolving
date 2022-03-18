package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
		Stack<Integer> stack = new Stack<>();
		for (int num : nums) {
			while (!stack.isEmpty() && stack.peek() < num)
				map.put(stack.pop(), num);
			stack.push(num);
		}
		for (int i = 0; i < findNums.length; i++)
			findNums[i] = map.getOrDefault(findNums[i], -1);
		return findNums;
	}

	public static void main(String[] args) {
		int [] num1 = {4,1,2};
		int [] num2 = {1,3,4,2};

		NextGreaterElement nextGreaterElement = new NextGreaterElement();
		System.out.println(Arrays.toString(nextGreaterElement.nextGreaterElement(num1, num2)));
	}
}
