package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeftMostColumnWithOne {

	public static void main(String[] args) {
		LeftMostColumnWithOne leftMostColumnWithOne= new LeftMostColumnWithOne();
		System.out.println(leftMostColumnWithOne.leftMostColumnWithOne(new BinaryMatrix() {
		}));
	}

	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
		List<Integer> dimensions = binaryMatrix.dimensions();

		int row = dimensions.get(0);
		int col = dimensions.get(1);

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < row; i++) {
			min = Math.min(min, solve(binaryMatrix, i, 0, col-1));
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private int solve(BinaryMatrix binaryMatrix, int row, int left, int right) {
		if (left == right) {
			if (left == 0 && binaryMatrix.get(row, left) == 1) {
				return left;
			} else if (binaryMatrix.get(row, left-1) == 0 && binaryMatrix.get(row, left) == 1) {
				return left;
			}

			return Integer.MAX_VALUE;
		}

		if (left < right) {
			if (left + 1 == right && binaryMatrix.get(row, left) == 0 && binaryMatrix.get(row, right) == 1) {
				return right;
			}

			int mid = (left + right) / 2;

			if (binaryMatrix.get(row, mid) == 0) {
				return solve(binaryMatrix, row, mid+1, right);
			} else {
				return solve(binaryMatrix, row, left, mid);
			}
		}

		return Integer.MAX_VALUE;
	}

	interface BinaryMatrix {
		int [][] mat = {{0,0,0,1},{0,0,1,1},{0,1,1,1}};

		default int get(int row, int col) {
			return mat[row][col];
		}

		default List<Integer> dimensions() {
			List<Integer> list = new ArrayList<>();

			list.add(mat.length);
			list.add(mat[0].length);

			return list;
		}
	}
}

