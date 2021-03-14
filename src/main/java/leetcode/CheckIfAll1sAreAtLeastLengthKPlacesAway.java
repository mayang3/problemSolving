package leetcode;

public class CheckIfAll1sAreAtLeastLengthKPlacesAway {
	public boolean kLengthApart(int[] nums, int k) {
		int min = Integer.MAX_VALUE;
		int cnt = 0;

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];

			if (num == 1) {
				if (i > 0) {
					min = Math.min(min, cnt);
				}
				cnt = 0;
			} else {
				cnt++;

			}
		}

		return min >= k;
	}

	public static void main(String[] args) {
		int [] nums = {0,1,0,1};
		int k = 2;

		CheckIfAll1sAreAtLeastLengthKPlacesAway away = new CheckIfAll1sAreAtLeastLengthKPlacesAway();
		System.out.println(away.kLengthApart(nums, k));

	}
}
