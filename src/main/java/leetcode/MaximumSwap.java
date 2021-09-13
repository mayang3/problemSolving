package leetcode;

import java.util.TreeMap;
import java.util.TreeSet;

public class MaximumSwap {
	public int maximumSwap(int num) {
		TreeMap<Integer, TreeSet<Integer>> treeMap = new TreeMap<>();

		String s = String.valueOf(num);

		for (int i = 0; i < s.length(); i++) {
			treeMap.computeIfAbsent(Character.getNumericValue(s.charAt(i)), t -> new TreeSet<>()).add(i);
		}

		char [] arr = s.toCharArray();

		for (int i = 0; i < s.length()-1; i++) {
			int here = Character.getNumericValue(s.charAt(i));

			treeMap.get(here).remove(i);

			if (treeMap.get(here).isEmpty()) {
				treeMap.remove(here);
			}

			if (treeMap.lastKey() > here) {
				int j = treeMap.lastEntry().getValue().pollLast();
				char temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				break;
			}
		}

		return Integer.valueOf(new String(arr));
	}

	public static void main(String[] args) {
		MaximumSwap maximumSwap = new MaximumSwap();
		System.out.println(maximumSwap.maximumSwap(89459544));
	}
}
