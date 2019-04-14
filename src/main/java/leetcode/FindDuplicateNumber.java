package leetcode;

/**
 * @author baejunbeom
 */
public class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		for (int i=0 ; i<nums.length-1 ; i++) {
			int j = i+1;
			int k = nums.length-1;

			while(j<=k) {
				if (nums[i] == nums[j]) {
					return nums[i];
				}

				if (nums[i] == nums[k]) {
					return nums[i];
				}

				j++;
				k--;
			}
		}

		return 0;
	}

	int findDuplicate3(int [] nums)
	{
		if (nums.length >= 1)
		{
			int slow = nums[0];
			int fast = nums[nums[0]];
			while (slow != fast)
			{
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while (fast != slow)
			{
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		return -1;
	}

	public static void main(String[] args) {
		int [] nums = {4,5,56,789,344,323,556,78,90,12,4,3,2,1};

		FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
		int duplicate = findDuplicateNumber.findDuplicate3(nums);

		System.out.println(duplicate);

	}
}
