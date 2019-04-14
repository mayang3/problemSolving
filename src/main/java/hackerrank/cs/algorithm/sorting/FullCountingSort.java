package hackerrank.cs.algorithm.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class FullCountingSort {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		List<String>[] listArr = new List[101];

		for (int i = 0; i < 101; i++) {
			listArr[i] = new ArrayList<String>();
		}

		for (int i = 0, half=n/2; i < n; i++) {
			int x = scanner.nextInt();
			String s = scanner.next();

			if (i < half) {
				s = "-";
			}

			listArr[x].add(s);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 101; i++) {

			List<String> sList = listArr[i];

			if (sList.isEmpty()) {
				continue;
			}

			// 얘를..
			// sout(s + " "); -> 이런식으로 하면 timeout 남
			for (String s : sList) {
				sb.append(s).append(" ");
			}
		}

		System.out.println(sb.toString());
	}
}
