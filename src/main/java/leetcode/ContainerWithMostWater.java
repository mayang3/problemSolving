package leetcode;

/**
 * @author neo82
 */
public class ContainerWithMostWater {
	public static void main(String[] args) {
		int [] heights = {10,14,10,4,10,2,6,1,6,12};

		ContainerWithMostWater water = new ContainerWithMostWater();
		int maxArea = water.maxArea(heights);

		System.out.println(maxArea);
	}

	public int maxArea(int[] height) {
		int left = 0;
		int right = height.length - 1;

		int max = Integer.MIN_VALUE;

		while (left < right) {
			max = Math.max(max, (right-left) * Math.min(height[left], height[right]));

			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}

		return max;
	}
}
