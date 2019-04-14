package hackerrank.cs.dataStructure.hash;

import java.util.*;

public class HashTables {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int m = scanner.nextInt();
		int n = scanner.nextInt();

		Map<String, Integer> dict = new HashMap<>();

		for (int i = 0; i < m; i++) {
			String dicStr = scanner.next();

			Integer cnt = dict.get(dicStr);

			if (cnt == null) {
				dict.put(dicStr, 1);
			} else {
				dict.put(dicStr, ++cnt);
			}
		}

		boolean success = true;

		for (int i = 0; i < n; i++) {
			String compare = scanner.next();

			Integer cnt = dict.get(compare);

			if (cnt == null || cnt <= 0) {
				success = false;
				break;
			} else {
				dict.put(compare, --cnt);
			}
		}

		if (success) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
