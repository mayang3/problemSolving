package leetcode;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		char[] sa = s.toCharArray();
		Arrays.sort(sa);

		char[] ta = t.toCharArray();
		Arrays.sort(ta);

		return new String(sa).equals(new String(ta));
	}

	public static void main(String[] args) {
		ValidAnagram validAnagram = new ValidAnagram();
		System.out.println(validAnagram.isAnagram("eat", "a1e"));
	}
}
