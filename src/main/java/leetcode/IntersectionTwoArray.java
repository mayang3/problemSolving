package leetcode;

import java.util.*;

/**
 * @author baejunbeom
 */
public class IntersectionTwoArray {

	/**
	 * Use two HashTable
	 *
	 * Time Complexity O(n)
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Set<Integer> resultSet = new HashSet<>();

		for (int i = 0 ; i<nums1.length ; i++) {
			set.add(nums1[i]);
		}

		for (int j = 0 ; j<nums2.length ; j++) {
			if (set.contains(nums2[j])) {
				resultSet.add(nums2[j]);
			}
		}

		int[] resultArray = new int[resultSet.size()];
		int k=0;

		for (Integer integer : resultSet) {
			resultArray[k++] = integer;
		}

		return resultArray;
	}

	/**
	 * Sort Both arrays, use two pointers by leetcode
	 *
	 * Time Complexity O(nlogn)
	 *
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectionTwoArray(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		Arrays.sort(nums1); // nlogn
		Arrays.sort(nums2); // nlogn

		int i = 0;
		int j = 0;
		// O(nums1.size) or O(nums2.size)
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				set.add(nums1[i]);
				i++;
				j++;
			}
		}
		int[] result = new int[set.size()];
		int k = 0;
		for (Integer num : set) {
			result[k++] = num;
		}

		return result;
	}

	public static void main(String[] args) {
		IntersectionTwoArray intersectionTwoArray = new IntersectionTwoArray();
		int[] intersection = intersectionTwoArray.intersection(new int[] {1, 2, 2, 1}, new int[] {2, 2});

		System.out.println(Arrays.toString(intersection));
	}
}
