package baekjoon.implement;

import java.util.Scanner;

public class 공 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);


		int [] indices = new int[4];
		int [] cups = new int[4];

		for (int i=1 ; i<=3 ; i++) {
			cups[i] = i;
			indices[i] = i;
		}

		int m = scanner.nextInt();

		while (m-- > 0) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();

			int temp = cups[indices[a]];
			cups[indices[a]] = cups[indices[b]];
			cups[indices[b]] = temp;

			temp = indices[a];
			indices[a] = indices[b];
			indices[b] = temp;
		}

		System.out.println(cups[1]);

	}
}
