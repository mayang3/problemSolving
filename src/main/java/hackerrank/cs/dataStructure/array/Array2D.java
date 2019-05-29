package hackerrank.cs.dataStructure.array;

import java.util.Scanner;

/**
 * @author neo82
 */
public class Array2D {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int [][] arr = new int[6][6];

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}

		int max = Integer.MIN_VALUE;

		for (int y = 1; y < 5; y++) {
			for (int x = 1; x < 5; x++) {
				int sum = arr[y][x];

				sum += arr[y-1][x] + arr[y-1][x-1] + arr[y-1][x+1];
				sum += arr[y+1][x] + arr[y+1][x-1] + arr[y+1][x+1];

				max = Math.max(max, sum);
			}
		}

		System.out.println(max);
	}
}

