package leetcode;

/**
 * @author neo82
 */
public class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		int tortoise = nums[0];
		int hare = nums[0];

		// 최초 시작 지점은 무조건 같으므로 do, while 로 실행시켜준다.
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		tortoise = nums[0];

		while (tortoise != hare) {
			tortoise = nums[tortoise];
			hare = nums[hare];
		}

		return hare;
	}


	public static void main(String[] args) {
		int [] nums = {1,3,4,2,2};

		FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
		int duplicate = findDuplicateNumber.findDuplicate(nums);

		System.out.println(duplicate);

	}
}
