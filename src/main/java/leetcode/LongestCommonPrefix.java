package leetcode;

/**
 * @author baejunbeom
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 1) {
			return strs[0];
		}

		int len = 0;

		while (len < 200) {
			if (strs[0].length() <= len) {
				return strs[0].substring(0, len);
			}

			char ch = strs[0].charAt(len);

			for (int i = 1; i < strs.length; i++) {
				if (strs[i].length() <= len || ch != strs[i].charAt(len)) {
					return strs[0].substring(0, len);
				}
			}

			len++;
		}

		return strs[0].substring(0, len);
	}

	public static void main(String[] args) {
		String [] aa = {"flower1","flower123"};

		LongestCommonPrefix prefix = new LongestCommonPrefix();
		String s = prefix.longestCommonPrefix(aa);

		System.out.println(s);
	}
}
