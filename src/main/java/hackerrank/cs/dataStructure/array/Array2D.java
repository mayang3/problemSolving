package hackerrank.cs.dataStructure.array;

import java.util.Scanner;

/**
 */
public class Array2D {

	static int [][] matrix = new int[6][6];

	static class HourGlass {
		int topLeft;
		int top;
		int topRight;
		int center;
		int bottomLeft;
		int bottom;
		int bottomRight;

		HourGlass(int y, int x) {
			topLeft = matrix[y-1][x-1];
			top = matrix[y-1][x];
			topRight = matrix[y-1][x+1];
			center = matrix[y][x];
			bottomLeft = matrix[y+1][x-1];
			bottom = matrix[y+1][x];
			bottomRight = matrix[y+1][x+1];
		}

		int getSum() {
			return topLeft + top + topRight + center + bottomLeft + bottom + bottomRight;
		}
	}

	static int solve() {

		int ret = Integer.MIN_VALUE;

		for (int y=1 ; y<matrix.length-1 ; y++) {
			for (int x=1 ; x<matrix.length-1 ; x++) {
				ret = Math.max(ret, new HourGlass(y,x).getSum());
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (int y=0 ; y<6 ; y++) {
			for (int x=0 ; x<6 ; x++) {
				matrix[y][x] = scanner.nextInt();
			}
		}

		System.out.println(solve());
	}

}
