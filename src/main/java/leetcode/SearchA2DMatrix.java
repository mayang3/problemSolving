package leetcode;

/**
 * @author neo82
 */
public class SearchA2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0) {
			return false;
		}

		int n = matrix.length;
		int m = matrix[0].length;

		for (int y = 0; y < n; y++) {
			if (solve(matrix[y], target, 0, m-1)) {
				return true;
			}
		}
		
		return false;
	}

	boolean solve(int[] matrix, int target, int left, int right) {
		if (left > right) {
			return false;
		} else if (left == right) {
			return matrix[left] == target;
		}

		int m = (left + right) / 2;

		if (matrix[m] >= target) {
			return solve(matrix, target, left, m);
		}

		return solve(matrix, target, m+1, right);
	}

	/**
	 * type 이 HORIZONTAL 이면.. 가장 가까운 row 를 리턴한다. 없으면 -1 을 리턴
	 * type 이 VERTICAL 이면.. 정확히 일치하는 index 를 리턴한다. 없으면 -1 을 리턴
	 * @return
	 */

	public static void main(String[] args) {
		int[][] m = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

		SearchA2DMatrix searchA2DMatrix = new SearchA2DMatrix();

		boolean res = searchA2DMatrix.searchMatrix(m, 20);

		System.out.println(res);
	}
}
