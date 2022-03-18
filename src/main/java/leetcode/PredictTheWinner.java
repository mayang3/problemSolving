package leetcode;

public class PredictTheWinner {
	public boolean PredictTheWinner(int[] nums) {
		return minimax(nums, 0, nums.length-1, true) >= 0;
	}

	int minimax(int [] nums, int left, int right, boolean player1) {
		if (left == right) {
			return player1 ? nums[left] : -nums[left];
		}

		if (player1) {
			return Math.max((minimax(nums, left+1, right, false) + nums[left]),
							minimax(nums, left, right-1, false) + nums[right]);
		} else {
			return Math.min((minimax(nums, left+1, right, true) - nums[left]),
							 minimax(nums, left, right-1, true) - nums[right]);
		}
	}

	public static void main(String[] args) {
		int [] nums = {1,5,233,7};

		PredictTheWinner predictTheWinner = new PredictTheWinner();
		System.out.println(predictTheWinner.PredictTheWinner(nums));
	}
}
