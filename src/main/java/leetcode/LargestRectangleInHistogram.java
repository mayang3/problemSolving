package leetcode;

/**
 * @author neo82
 */
public class LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		return solve(heights, 0, heights.length - 1);
	}

	private int solve(int[] heights, int l, int r) {
		if (l == r) {
			return heights[l];
		}

		int area = 0;

		if (l < r) {
			int m = (l + r) / 2;

			// left area
			area = Math.max(area, solve(heights, l, m));

			// right area
			area = Math.max(area, solve(heights, m+1, r));

			// center area
			int left = m;
			int right = m+1;
			int h = Math.min(heights[left], heights[right]);

			// 현재 divide 된 범위 내에서 full scan
			while (left >= l || right <= r) {
				// 현재 범위의 area 계산
				area = Math.max(area, (right - left + 1) * h);

				if (left == l && right == r) {
					break;
				} else if (left == l && right < r) {
					right++;
					h = Math.min(h, heights[right]);
				} else if (right == r && left > l) {
					left--;
					h = Math.min(h, heights[left]);
				} else if (heights[left-1] >= heights[right+1]) {
					left--;
					h = Math.min(h, heights[left]);
				} else if (heights[left-1] < heights[right+1]) {
					right++;
					h = Math.min(h, heights[right]);
				}
			}
		}

		return area;
	}

	public static void main(String[] args) {
		int [] heights = {4,2,0,3,2,4,3,4};

		LargestRectangleInHistogram rectangle = new LargestRectangleInHistogram();
		int res = rectangle.largestRectangleArea(heights);

		System.out.println(res);
	}
}
