package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baejunbeom
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		String prefix = "";
		int max = 0;

		for (int i = 1 ; i<strs.length ; i++) {
			String before = strs[i-1];
			String after = strs[i];

			int index = after.indexOf(before);

			if (index > max) {
				max = index;
				prefix = before.substring(0, index);
			}
		}

		return prefix;
	}

	public static void main(String[] args) {
		String [] aa = {"abc", "ab", "abcedf", "ab"};

		LongestCommonPrefix prefix = new LongestCommonPrefix();
		String s = prefix.longestCommonPrefix(aa);

		System.out.println(s);
	}
}
