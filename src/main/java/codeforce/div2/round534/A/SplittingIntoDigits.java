package codeforce.div2.round534.A;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SplittingIntoDigits {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		System.out.println(solve(n, new LinkedList<>()));
	}

	private static List<Integer> solve(int n, LinkedList<Integer> list) {
		if (n == 0) {
			return list;
		}

		for (int i = 1; i <= 9; i++) {
			list.addLast(i);
			solve(n-i, list);
			list.removeLast();
		}

		return null;
	}
}
