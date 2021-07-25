package leetcode;

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		int i = 0;
		int j = 0;
		int k = 0;

		int [] mergeArray = new int[m + n];

		while (j < m && k < n) {
			if (nums1[j] < nums2[k]) {
				mergeArray[i] = nums1[j];
				j++;
			} else {
				mergeArray[i] = nums2[k];
				k++;
			}

			i++;
		}

		while (j < m) {
			mergeArray[i] = nums1[j];
			j++;
			i++;
		}

		while (k < n) {
			mergeArray[i] = nums2[k];
			k++;
			i++;
		}

		int mid = (m + n) / 2;

		if ((m + n) % 2 != 0) {
			return mergeArray[mid];
		}

		return (((double)mergeArray[mid - 1] + mergeArray[mid]) / (double)2);
	}

	public static void main(String[] args) {
		int [] num1 = {2};
		int [] num2 = {};

		MedianOfTwoSortedArrays arrays = new MedianOfTwoSortedArrays();

		System.out.println(arrays.findMedianSortedArrays(num1, num2));
	}
}
