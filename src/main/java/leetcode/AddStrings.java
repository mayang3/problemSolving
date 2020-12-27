package leetcode;


public class AddStrings {
	public String addStrings(String num1, String num2) {
		StringBuilder sb = new StringBuilder();

		int adding = 0;

		for (int i = num1.length()-1, j = num2.length()-1; i >= 0 || j >= 0; i--, j--) {
			int n1 = 0;
			int n2 = 0;

			if (i >= 0) {
				n1 = Character.getNumericValue(num1.charAt(i));
			}

			if (j >= 0) {
				n2 = Character.getNumericValue(num2.charAt(j));
			}

			int res = n1 + n2 + adding;

			sb.append(res % 10);

			adding = res / 10;
		}

		if (adding > 0) {
			sb.append(adding);
		}

		return sb.reverse().toString();
	}

	// 79567
	public static void main(String[] args) {
		AddStrings addStrings = new AddStrings();
		String res = addStrings.addStrings("78932", "635");

		System.out.println(res);
	}
}
