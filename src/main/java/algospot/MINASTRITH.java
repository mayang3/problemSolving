package algospot;

import java.util.Scanner;

public class MINASTRITH {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			int N = scanner.nextInt();

			double [][] arr = new double[N][3];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = scanner.nextDouble();
				}
			}
		}
	}
}
