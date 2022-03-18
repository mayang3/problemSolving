package leetcode.contest.weekly_280;

public class MinimumOperationsToMakeTheArrayAlternating {
	public int minimumOperations(int[] nums) {
		//populate the frequency array, with 0 for evens and 1 for odds (this is basically counting sort!)
		int freq[][] = new int[100005][2];
		for(int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			// shorthand for the following: i&1
			//    -> returns 0 if even
			//    -> returns 1 if odd
			if (i % 2 == 0) {
				freq[curr][0]++;
			} else {
				freq[curr][1]++;
			}
		}

		int maxEvenFreq = 0; // max evens frequency of a number
		int maxOddFreq = 0; // max odds frequency of a number
		int ans = 0;
		for (int i = 1; i <= 100000; i++) {
			// (i is curr)
			//current frequency of occurrences of i among even
			int currEvenFreq = freq[i][0];
			//current frequency of occurrences of i among odd
			int currOddFreq = freq[i][1];

			// Add the maximum frequency of odd indexes to maximum frequency even indexes
			//and vice versa, it will give us how many elements we don't need to change.
			int temp = Math.max(currEvenFreq + maxOddFreq, currOddFreq + maxEvenFreq);
			ans = Math.max(ans, temp);

			// check if freq[i][0] or freq[i][1] are new max frequency values for even or odd
			maxEvenFreq = Math.max(maxEvenFreq, currEvenFreq); // max evens frequency of a number
			maxOddFreq = Math.max(maxOddFreq, currOddFreq); // max odds frequency of a number
		}
		return nums.length - ans;
	}

	public static void main(String[] args) {
		int [] nums = {2,2,2,2,2};

		MinimumOperationsToMakeTheArrayAlternating minimumOperationsToMakeTheArrayAlternating = new MinimumOperationsToMakeTheArrayAlternating();
		System.out.println(minimumOperationsToMakeTheArrayAlternating.minimumOperations(nums));
	}
}
