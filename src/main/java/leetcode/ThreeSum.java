package leetcode;

import java.util.*;

/**
 * @author baejunbeom
 */
public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();

		// 이 정렬이 반드시 되어 있어야 한다.
		// 이유는 j 와 k의 포인터를 이동시킬때 기준값이 정렬이 되어있다고가정되어야 하기 때문이다
		// 예를 들어서, {-1, 0, 1, 2, -1, -4} 가 있다고 가정할때,
		// i = 0, j=2, k=4 라고 가정하자.
		// 이때 j의 인덱스 값이 하나 더 증가해서 3 이 되어야, -1, 2, -1 = 0 이 되어서 답을 찾을 수 있다.
		// 하지만 잘못된 정렬로 인해 k 의 값이 줄어들게 된다면.. i=0, j=2, k=3 이 되어서 -1, 1, 2 가 되어버려서 답을 못찾게 된다.
		// 이런 일이 발생하는 원인은, 이 포인터를 증가/감소 시켜서 하는 연산은 모든 경우의 수를 검사하는 것은 아니고,
		// 값이 target 보다 크다면 큰값의 포인터(k) 를 감소시키고,
		// 값이 target 보다 작다면 작은 값의 포인터(j) 를 증가시키는 개념이기 때문이다.
		// 이 개념의 달성을 위해서는 반드시! 정렬이 되어있어야 한다~
		Arrays.sort(nums);
		for (int i = 0; i + 2 < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
				continue;
			}
			int j = i + 1, k = nums.length - 1;
			int target = -nums[i];
			while (j < k) {
				if (nums[j] + nums[k] == target) {
					res.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
					k--;
					while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
					while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
				} else if (nums[j] + nums[k] > target) {
					k--;
				} else {
					j++;
				}
			}
		}
		return res;
	}

//	/**
//	 * 최초 내가 짠 솔루션인데.. 많은 문제가 있었음.
//	 * 아래가 이어서 나오는 메소드가 릿코드에서의 솔루션..
//	 *
//	 * 투 포인터 문제라고 해서, 포인터를 두개만 생각했던것이 패착..
//	 * 투 포인터 문제는 포인터를 두개 이상의 여러개를 사용할 수 있다고 생각하자..
//	 * @param nums
//	 * @return
//	 */
//	public List<List<Integer>> threeSum(int[] nums) {
//
//		int sum = 0;
//
//		Set<List<Integer>> resultHashSet = new HashSet<>();
//
//		if (nums == null || nums.length <= 0) {
//			return new ArrayList<>(resultHashSet);
//		}
//
//		Arrays.sort(nums);
//
//		for (int i = 0 ; i<nums.length - 2; i++) {
//			sum = nums[i];
//			List<Integer> subList = new ArrayList<>();
//			subList.add(nums[i]);
//
//			for (int j = i+1 ; j < nums.length ; j++) {
//
//				sum += nums[j];
//
//				if (sum == 0) {
//					subList.add(nums[j]);
//				} else {
//					sum -= nums[j];
//				}
//
//				if (sum == 0 && subList.size() == 3) {
//					resultHashSet.add(subList);
//					sum = nums[i];
//					subList = new ArrayList<>();
//					subList.add(nums[i]);
//				}
//			}
//
//
//		}
//
//		return new ArrayList<>(resultHashSet);
//	}

	public static void main(String[] args) {
		int [] nums = {-1,0,1,2,-1,-4};

		ThreeSum threeSum = new ThreeSum();
		List<List<Integer>> lists = threeSum.threeSum(nums);

		System.out.println(lists);
	}
}
