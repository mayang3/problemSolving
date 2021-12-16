package leetcode;

public class MinimumAddToMakeParenthesisValid {
	public int minAddToMakeValid(String s) {
		int left = 0;
		int right = 0;
		int res = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			if (ch == '(') {
				left++;
			} else {
				right++;

				if (right > left) {
					res++;
					left = 0;
					right = 0;
				}
			}
		}

		if (left > 0) {
			res += (left - right);
		}


		return res;
	}

	public static void main(String[] args) {
		MinimumAddToMakeParenthesisValid minimumAddToMakeParenthesisValid = new MinimumAddToMakeParenthesisValid();
		System.out.println(minimumAddToMakeParenthesisValid.minAddToMakeValid("()))(("));
	}
}
