package leetcode;

public class CountAndSay {
	public String countAndSay(int n) {
		StringBuilder s1 = new StringBuilder("1");
		int i = 1;

		while (i < n) {
			char number = 't'; // temp
			int count = 0;
			StringBuilder s2 = new StringBuilder();

			for (int j = 0; j < s1.length(); j++) {
				char ch = s1.charAt(j);

				if (ch != number) {
					if (count > 0) {
						s2.append(count).append(number);
					}
					number = ch;
					count = 1;
				} else {
					count++;
				}
			}

			s2.append(count).append(number);
			s1 = s2;
			i++;
		}

		return s1.toString();
	}

	public static void main(String[] args) {
		CountAndSay countAndSay = new CountAndSay();
		System.out.println(countAndSay.countAndSay(30));
	}
}
