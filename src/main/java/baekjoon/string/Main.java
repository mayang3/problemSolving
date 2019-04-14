package baekjoon.string;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author baejunbeom
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");

		String next = scanner.next();

		Main main = new Main();
		int count = main.count(next);

		System.out.println(count);

	}

	int count(String next) {
		StringTokenizer stringTokenizer = new StringTokenizer(next, " ");

		return stringTokenizer.countTokens();
	}
}
