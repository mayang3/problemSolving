package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class BuildingsWithAnOceanView {
	public int[] findBuildings(int[] heights) {
		LinkedList<Integer> res = new LinkedList<>();

		int max = 0;

		for (int i = heights.length-1; i >= 0 ; i--) {
			if (heights[i] > max) {
				res.add(0, i);
			}

			max = Math.max(max, heights[i]);
		}

		return res.stream().mapToInt(value -> value).toArray();
	}

	public static void main(String[] args) {
		int [] heights = {2,2,2,2};

		BuildingsWithAnOceanView buildingsWithAnOceanView = new BuildingsWithAnOceanView();
		System.out.println(Arrays.toString(buildingsWithAnOceanView.findBuildings(heights)));
	}
}
