package leetcode;

import java.util.*;

public class RelativeSortArray {
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr2.length; i++) {
			map.put(arr2[i], i);
		}

		Integer [] convertArr = new Integer[arr1.length];

		for (int i = 0; i < arr1.length; i++) {
			convertArr[i] = arr1[i];
		}

		Arrays.sort(convertArr, (o1, o2) -> {
			Integer v1 = map.get(o1);
			Integer v2 = map.get(o2);

			if (v1 == null && v2 == null) {
				return o1 - o2;
			} else if (v1 != null && v2 == null) {
				return -1;
			} else if (v1 == null && v2 != null) {
				return 1;
			}

			return v1 - v2;
		});

		for (int i = 0; i < convertArr.length; i++) {
			arr1[i] = convertArr[i];
		}

		return arr1;
	}

	public static void main(String[] args) {
		int [] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
		int [] arr2 = {2,1,4,3,9,6};

		RelativeSortArray relativeSortArray = new RelativeSortArray();

		int[] ret = relativeSortArray.relativeSortArray(arr1, arr2);

		System.out.println(Arrays.toString(ret));
	}
}
