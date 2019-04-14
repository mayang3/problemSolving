package leetcode;

/**
 * @author baejunbeom
 */
public class ReverseString {

	public String reverseString(String s) {
		char[] chars = s.toCharArray();

		StringBuilder builder = new StringBuilder();

		for (int i = chars.length -1 ; i>= 0 ; i--) {
			builder.append(chars[i]);
		}

		return builder.toString();
	}

	public static void main(String[] args) {
		ReverseString reverseString = new ReverseString();
		String hello = reverseString.reverseString("hello");

		System.out.println(hello);
	}
}
