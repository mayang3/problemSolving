package leetcode;

import java.util.Arrays;

public class MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts {

	static int MODULEO = (int)1e9 + 7;
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		int hLen = horizontalCuts.length;
		int vLen = verticalCuts.length;

		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);

		long maxWidth = Math.abs(0 - horizontalCuts[0]);
		long maxHeight = Math.abs(0 - verticalCuts[0]);

		for (int i = 1; i < horizontalCuts.length; i++) {
			maxWidth = Math.max(maxWidth, Math.abs(horizontalCuts[i] - horizontalCuts[i-1]));
		}

		for (int i = 1; i < verticalCuts.length; i++) {
			maxHeight = Math.max(maxHeight, Math.abs(verticalCuts[i] - verticalCuts[i-1]));
		}

		maxWidth = Math.max(maxWidth, Math.abs(horizontalCuts[hLen-1] - h));
		maxHeight = Math.max(maxHeight, Math.abs(verticalCuts[vLen-1] - w));

		return (int)(((maxWidth % MODULEO) * (maxHeight % MODULEO)) % MODULEO);
	}

	public static void main(String[] args) {
		int h = 5;
		int w = 4;
		int [] horizontalCuts = {3};
		int [] verticalCuts = {3};

		MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts area = new MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts();

		System.out.println(area.maxArea(h, w, horizontalCuts, verticalCuts));
		System.out.println((int)1e9+7);
	}
}
