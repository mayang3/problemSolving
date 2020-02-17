package leetcode;

/**
 * @author neo82
 */
public class MaximalRectangle {

	/**
	 *
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		int n = matrix.length;
		int m = matrix[0].length;

		int [][] heights = new int[n][m];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				int num = Character.getNumericValue(matrix[y][x]);

				if (y == 0) {
					heights[y][x] = num;
				} else {
					heights[y][x] = num == 0 ? 0 : heights[y-1][x] + num;
				}

			}
		}

		int area = 0;

		for (int y = 0; y < n; y++) {
			area = Math.max(area, solve(heights[y], 0, m-1));
		}

		return area;
	}

	private int solve(int[] heights, int l, int r) {
		if (l == r) {
			return heights[l];
		}

		int area = 0;

		if (l < r) {
			int m = (l + r) / 2;

			// divide left and right
			area = Math.max(solve(heights, l, m), solve(heights, m+1, r));

			int left = m;
			int right = m+1;
			int height = Math.min(heights[left], heights[right]);

			area = Math.max(area, (right-left+1) * height);
			// from center
			while (l < left || right < r) {

				if (l == left && right < r) {
					right++;
					height = Math.min(height, heights[right]);
				} else if (r == right && l < left) {
					left--;
					height = Math.min(height, heights[left]);
				} else if (heights[left-1] >= heights[right+1]) {
					left--;
					height = Math.min(height, heights[left]);
				} else {
					right++;
					height = Math.min(height, heights[right]);
				}

				area = Math.max(area, (right - left + 1) * height);
			}
		}

		return area;
	}

	public static void main(String[] args) {
		char [][] matrix = {
			{'1','1','0','1','0','1'},
			{'0','1','0','0','1','1'},
			{'1','1','1','1','0','1'},
			{'1','1','1','1','0','1'}
		};
		System.out.println(new MaximalRectangle().maximalRectangle(matrix));
	}
}
