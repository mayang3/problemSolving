package hackerrank.cs.algorithm.implement;

import java.util.LinkedList;
import java.util.Scanner;

public class FormingAMagicSquare {

	static int [][] square = new int[3][3];
	static int min = 987654321;

	static class MagicSquare {
		int [][] ms;

		MagicSquare(int [][] ms) {
			this.ms = ms;
		}

		int diffCost(int [][] diff) {
			int cost = 0;

			cost += Math.abs(diff[0][0] - ms[0][0]);
			cost += Math.abs(diff[0][1] - ms[0][1]);
			cost += Math.abs(diff[0][2] - ms[0][2]);

			cost += Math.abs(diff[1][0] - ms[1][0]);
			cost += Math.abs(diff[1][1] - ms[1][1]);
			cost += Math.abs(diff[1][2] - ms[1][2]);

			cost += Math.abs(diff[2][0] - ms[2][0]);
			cost += Math.abs(diff[2][1] - ms[2][1]);
			cost += Math.abs(diff[2][2] - ms[2][2]);

			return cost;
		}

		boolean isVerifyHorizontal() {
			return (ms[0][0] + ms[0][1] + ms[0][2]) == 15
				&& (ms[1][0] + ms[1][1] + ms[1][2]) == 15
				&& (ms[2][0] + ms[2][1] + ms[2][2]) == 15;
		}

		boolean isVerifyVertical() {
			return (ms[0][0] + ms[1][0] + ms[2][0]) == 15
				&& (ms[0][1] + ms[1][1] + ms[2][1]) == 15
				&& (ms[0][2] + ms[1][2] + ms[2][2]) == 15;
		}

		boolean isVerifyDiagonal() {
			return ms[0][0] + ms[1][1] + ms[2][2] == 15
				&& ms[0][2] + ms[1][1] + ms[2][0] == 15;
		}

		boolean isVerify() {
			return isVerifyHorizontal() && isVerifyVertical() && isVerifyDiagonal();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (int i=0 ; i<3; i++) {
			for (int j=0 ; j<3 ; j++) {
				square[i][j] = scanner.nextInt();
			}
		}

		int [][] ms = new int[3][3];

		LinkedList<Integer> nums = new LinkedList<>();

		for (int i=1 ; i<=9 ; i++) {
			nums.add(i);
		}

		calMagicSquare(ms, nums);
		System.out.println(min);
	}

	static void calMagicSquare(int [][] ms, LinkedList<Integer> nums) {

		YX yx = findYX(ms);

		if (yx == null) {
			MagicSquare magicSquare = new MagicSquare(ms);

			if (magicSquare.isVerify()) {
				min = Math.min(min, magicSquare.diffCost(square));
			}

			return;
		}

		int y = yx.y;
		int x = yx.x;

		for (int i=0 ; i<nums.size() ; i++) {
			LinkedList<Integer> newNums = new LinkedList(nums);
			ms[y][x] = newNums.remove(i);

			calMagicSquare(ms, newNums);

			ms[y][x] = 0;
		}
	}

	/**
	 *
	 * @param square
	 * @return
	 */
	static YX findYX(int[][] square) {
		for (int y=0 ; y<3 ; y++) {
			for (int x=0 ; x<3 ; x++) {
				if (square[y][x] == 0) {
					return new YX(y, x);
				}
			}
		}

		return null;
	}

	static class YX {
		int y;
		int x;

		YX(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
