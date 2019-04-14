package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 */
public class FindThePoint {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();


		for (int i=0 ; i<n ; i++) {
			int px = scanner.nextInt();
			int py = scanner.nextInt();
			int qx = scanner.nextInt();
			int qy = scanner.nextInt();

			int rx = px + ((qx-px)*2);
			int ry = py + ((qy-py)*2);

			System.out.println(rx + " " + ry);
		}
	}
}
