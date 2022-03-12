package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class NextGreaterElement3 {
	public int nextGreaterElement(int n) {
		StringBuilder s = new StringBuilder();
		s.append(n);

		TreeMap<Integer, Integer> treeMap = new TreeMap<>();

		int index1 = -1;
		int index2 = -1;

		for (int i = s.length() - 2; i >= 0; i--) {
			int num1 = s.charAt(i);
			int num2 = s.charAt(i+1);

			// descending
			if (num1 < num2) {
				treeMap.put(num2, i+1);
				index1 = i;
				index2 = treeMap.higherEntry(num1).getValue();
				break;
			}

			treeMap.put(num2, i+1);
		}

		if (index1 == -1) {
			return -1;
		}

		// swap
		char temp = s.charAt(index1);
		s.setCharAt(index1, s.charAt(index2));
		s.setCharAt(index2, temp);

		char [] arr = s.substring(index1 + 1).toCharArray();
		Arrays.sort(arr);

		long ans = Long.valueOf(s.substring(0, index1+1).concat(String.valueOf(arr)));

		return ans > Integer.MAX_VALUE ? -1 : (int)ans;
	}

	public static void main(String[] args) {
		NextGreaterElement3 nextGreaterElement3 = new NextGreaterElement3();
		System.out.println(nextGreaterElement3.nextGreaterElement(2147483486));
	}
}
