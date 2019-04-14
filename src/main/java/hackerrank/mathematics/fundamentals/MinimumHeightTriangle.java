package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

public class MinimumHeightTriangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int b = scanner.nextInt();
		int area = scanner.nextInt();

		System.out.println((int)Math.ceil((float)2*(float)area/(float)b));
	}
}
