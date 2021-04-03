public class TrappingRainWater {
	public int trap(int[] height) {
		int left = 0;
		int right = 0;
		int n = height.length;

		for (int i = 0; i < n; i++) {
			if (height[i] > 0) {
				left = right = i;
				break;
			}
		}

		int heightSum = 0;
		int bottom = Integer.MAX_VALUE;
		int width = 0;

		for (int i = right; i < n; i++) {
			// 물을 가둘 수 있는 구간에서의 가장 낮은 높이를 구한다.
			if (height[right] < height[left]) {
				bottom = Math.min(bottom, height[i]);
			}

			// 물을 가둘 수 있는 공간에서의 trap 의 높이를 구한다.
			heightSum += height[right];

			// height[left] > 0 && height[right] > 0 이고, height[right] 가 bottom 보다 크다면 물을 가둘 수 있다.
			if (height[right] > bottom) {
				width = Math.max(width, ((right - left - 1) * Math.min(height[right], height[left])) - heightSum);
			}


		}

		return 0;
	}

	public static void main(String[] args) {
		int [] height = {0,1,0,2,1,0,1,3,2,1,2,1};

		TrappingRainWater trappingRainWater = new TrappingRainWater();
//		System.out.println(trappingRainWater.trap());
	}
}
