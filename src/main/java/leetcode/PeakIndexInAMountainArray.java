package leetcode;

/**
 * @author neo82
 */
public class PeakIndexInAMountainArray {
	public static void main(String[] args) {
		int [] A = {0, 1, 0};

		PeakIndexInAMountainArray mountainArray = new PeakIndexInAMountainArray();
		System.out.println(mountainArray.peakIndexInMountainArray(A));
	}

	public int peakIndexInMountainArray(int[] A) {
		int maxIndex = 0;
		int maxValue = 0;

		for (int i = 0; i < A.length; i++) {
			if (maxValue < A[i]) {
				maxIndex = i;
				maxValue = A[i];
			}
 		}

		return maxIndex;
	}
}
