package leetcode;

import java.util.Arrays;

public class MergeSortedArray {
	public void merge(int [] nums1, int m, int [] nums2, int n) {
		int i = m + n - 1;
		int curM = m - 1;
		int curN = n - 1;

		while (curM >= 0 && curN >= 0) {
			nums1[i--] = nums1[curM] > nums2[curN] ? nums1[curM--] : nums2[curN--];
		}

		while (i >= 0 && curM >= 0) {
			nums1[i--] = nums1[curM--];
		}

		while (i >= 0 && curN >= 0) {
			nums1[i--] = nums2[curN--];
		}
	}

	public static void main(String[] args) {
		int [] nums1 = {1,2,3,0,0,0};
		int [] nums2 = {2,5,6};

		MergeSortedArray mergeSortedArray = new MergeSortedArray();

		mergeSortedArray.merge(nums1, 3, nums2, 3);

		System.out.println(Arrays.toString(nums1));
	}
}
