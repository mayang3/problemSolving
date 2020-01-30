package leetcode;

public class TrappingRainWater {
	public int trap(int[] height) {
		// the case of impossible to hold water
		if (height == null || height.length < 3) {
			return 0;
		}

		int ans = 0;
		int l = 0;
		int r = height.length - 1;

		// 왼쪽과 오른쪽의 시작점을 찾는다.
		while (l < r && height[l] <= height[l+1]) l++;
		while (l < r && height[r] <= height[r-1]) r--;

		while (l < r) {
			int leftHeight = height[l];
			int rightHeight = height[r];

			// 오른쪽 기둥이 왼쪽 기둥보다 큰 경우, 왼쪽 기둥을 앞으로 당긴다.
			if (leftHeight <= rightHeight) {
				while (l < r && leftHeight >= height[++l]) {
					ans += leftHeight - height[l];
				}
			} else {
				// 왼쪽 기둥이 더 큰 경우, 오른쪽 기둥을 앞으로 당긴다.
				while (l < r && rightHeight >= height[--r]) {
					ans += rightHeight - height[r];
				}
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int [] height = {0,1,0,2,1,0,1,3,2,1,2,1};

		TrappingRainWater trp = new TrappingRainWater();
		int res = trp.trap(height);

		System.out.println(res);
	}
}
