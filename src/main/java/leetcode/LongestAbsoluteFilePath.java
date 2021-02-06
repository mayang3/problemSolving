package leetcode;

import java.util.Stack;

public class LongestAbsoluteFilePath {

	public int lengthLongestPath(String input) {
		int max = 0;

		Stack<Integer> stack = new Stack<>();

		for (String s : input.split("\n")) {
			// number of "\t"
			int level = s.lastIndexOf("\t") + 1;

			while (stack.isEmpty() == false && level < stack.size()) {
				stack.pop();
			}

			int len = (stack.isEmpty() ? 0 : stack.peek() + 1) + s.length() - level;

			stack.push(len);

			if (s.contains(".")) {
				max = Math.max(max, len);
			}

		}

		return max;
	}

	public static void main(String[] args) {
		LongestAbsoluteFilePath path = new LongestAbsoluteFilePath();

		System.out.println(path.lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));

	}
}
