package leetcode;

/*

There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

*/

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class MedianOfTwoSortedArrays {

	/**
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		List<Integer> arrayList = new ArrayList<>();

		if ((nums1 == null || nums1.length <= 0) && (nums2 != null && nums2.length > 0)) {
			for (int n2 : nums2) {
				arrayList.add(n2);
			}

		} else if ((nums1 != null && nums1.length > 0) && (nums2 == null || nums2.length <= 0)) {
			for (int n1 : nums1) {
				arrayList.add(n1);
			}

		} else {
			arrayList = makeArrayList(nums1, nums2);
		}

		if (arrayList.size() % 2 == 0) {
			int length = arrayList.size() - 1;

			return (arrayList.get(length / 2) + arrayList.get((length / 2) + 1)) / 2.0;
		}

		return arrayList.get((arrayList.size() / 2));
	}

	private List<Integer> makeArrayList(int[] nums1, int[] nums2) {
		int[] before = nums1;
		int[] after = nums2;

		if (nums1[0] > nums2[0]) {
			before = nums2;
			after = nums1;
		}

		List<Integer> arrayList = new ArrayList<>();
		boolean modify = false;
		int afterIndex = 0;

		for (int i=0 ; i<before.length ; i++) {
			if (before[i] > after[0]) {
				for (int j = afterIndex ; j < after.length ; j++) {
					arrayList.add(j);
					modify = true;
					afterIndex++;
				}
			}

			arrayList.add(before[i]);
		}

		if (modify == false) {
			for (int a : after) {
				arrayList.add(a);
			}
		}
		return arrayList;
	}

	public static void main(String[] args) {
		int [] nums1 = {1, 1, 3, 3};
		int [] nums2 = {1, 1, 3, 3};

		MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
		double medianSortedArrays = median.findMedianSortedArrays(nums1, nums2);

		System.out.println(medianSortedArrays);
	}

}
