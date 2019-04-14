package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class BinaryWatch {

	private static final int [] hours = {1, 2, 4, 8};
	private static final int [] minutes = {1, 2, 4, 8, 16, 32};

	public List<String> readBinaryWatch(int num) {


		List<String> ans = new LinkedList<>();

		backtrack(ans, 0, 0, num);

		return ans;
	}

	private void backtrack(List<String> ans, int hIndex, int mIndex, int num) {

		if (num <= 0) {
			System.out.println(hIndex);
			System.out.println(mIndex);
			ans.add(hours[hIndex] + ":" + minutes[mIndex]);
			return;
		}

		if (hIndex < hours.length) {
			backtrack(ans, hIndex + 1, mIndex, num - 1);
		}

		if (mIndex < minutes.length) {
			backtrack(ans, hIndex, mIndex + 1, num - 1);
		}
	}

	public static void main(String[] args) {
		BinaryWatch binaryWatch = new BinaryWatch();
		List<String> strings = binaryWatch.readBinaryWatch(1);

		System.out.println(strings);
	}
}
