package baekjoon.implement;

import java.util.Scanner;

public class 별찍기_1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		String output = "";

		for (int i=0 ; i<N ; i++) {
			output += "*";
			System.out.println(output);
		}
	}
}
