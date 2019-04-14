package baekjoon.string;

import java.util.Arrays;
import java.util.Scanner;

public class 알파벳찾기 {
	static int [] alphabet = new int[26];

	static {
		Arrays.fill(alphabet, -1);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();

		for (int i=0 ; i<s.length() ; i++) {
			char c = s.charAt(i);

			int index = (int)c-97;

			if (alphabet[index] < 0) {
				alphabet[index] = i;
			}
		}

		for (int v : alphabet) {
			System.out.print(v + " ");
		}

	}
}
