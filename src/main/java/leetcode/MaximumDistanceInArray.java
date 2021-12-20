package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumDistanceInArray {
	public int maxDistance(List<List<Integer>> arrays) {
		int min = arrays.get(0).get(0);
		int max = arrays.get(0).get(arrays.get(0).size()-1);
		int distance = 0;

		for (int i = 1; i < arrays.size(); i++) {
			int hereMin = arrays.get(i).get(0);
			int hereMax = arrays.get(i).get(arrays.get(i).size()-1);

			distance = Math.max(distance, Math.abs(max - hereMin));
			distance = Math.max(distance, Math.abs(hereMax - min));

			if (min == 0) {
				min = hereMin;
			}

			if (max == 0) {
				max = hereMax;
			}


			min = Math.min(hereMin, min);
			max = Math.max(hereMax, max);
		}

		return distance;
	}

	public static void main(String[] args) {
		List<List<Integer>> arr = new ArrayList<>();

		arr.add(Arrays.asList(new Integer [] {1,2,3}));
		arr.add(Arrays.asList(new Integer [] {1}));

		MaximumDistanceInArray maximumDistanceInArray = new MaximumDistanceInArray();
		System.out.println(maximumDistanceInArray.maxDistance(arr));
	}
}
