package leetcode;

public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		if (m > n) {
			return findMedianSortedArrays(nums2, nums1);
		}

		int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) / 2;

		double maxLeft = 0, minRight;

		while (imin <= imax) {
			i = (imin + imax) / 2;
			j = half - i;
			if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
				imin = i + 1;
			} else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
				imax = i - 1;
			} else {
				if (i == 0) {
					maxLeft = nums2[j - 1];
				} else if (j == 0) {
					maxLeft = nums1[i - 1];
				} else {
					maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
				}

				// 홀수라면 i 또는 j 가 인덱스의 범위를 벗어나 있을 수 있다.
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				if (i == m) {
					minRight = nums2[j];
				} else if (j == n) {
					minRight = nums1[i];
				} else {
					minRight = Math.min(nums1[i], nums2[j]);
				}

				return (maxLeft + minRight) / 2;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int[] num1 = {2,3};
		int[] num2 = {1};

		MedianOfTwoSortedArrays arrays = new MedianOfTwoSortedArrays();

		System.out.println(arrays.findMedianSortedArrays(num1, num2));
	}
}
