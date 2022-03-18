package leetcode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class KthLargestNumberInArray {
	public String kthLargestNumber(String[] nums, int k) {
		Arrays.sort(nums, (o1, o2) -> {
			BigInteger b1 = new BigInteger(o1);
			BigInteger b2 = new BigInteger(o2);

			return b2.compareTo(b1);
		});

		return nums[k-1];
	}

	public static void main(String[] args) {
		String [] nums = {"2","21","12","1"};
		int k = 1;

		KthLargestNumberInArray kthLargestNumberInArray = new KthLargestNumberInArray();
		System.out.println(kthLargestNumberInArray.kthLargestNumber(nums, k));
	}
}
