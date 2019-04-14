package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baejunbeom
 */
public class MaximumProductSubArray {

	/**
	 * O(n2) - time exceeded
	 *
	 * brute force
	 *
	 * @param nums
	 * @return
	 */
	public int maxProductByBruteForce(int[] nums) {
		int max = Integer.MIN_VALUE;
		int [] maxArr = new int[nums.length];

		for (int i=0; i<nums.length ; i++) {
			maxArr[i] = nums[i];

			if (max < maxArr[i]) {
				max = maxArr[i];
			}

			for (int j=i+1; j<nums.length ; j++) {

				maxArr[i] *= nums[j];

				if (max < maxArr[i]) {
					max = maxArr[i];
				}
			}

		}

		return max;
	}

	/**
	 * dp 를 이용한 방법.
	 *
	 * 핵심은.. 이전 단계의 MAX 값과 MIN 값을 모두 가지고 있고,
	 * 이 값에 대한 곱을 수행한 결과값의 MIN, MAX 값을 다시 판단 하는 것..
	 *
	 * MIN, MAX 값을 두개 가져오는 이유는... 음수에 의해서 MIN, MAX 가 순식간에 뒤집어질 수 있으므로,,
	 * 이 두개를 항상 판단해서 가지고 다녀야 한다.
	 *
	 * 여기서 최소/ 최대값을 계산하는 방식이...
	 * 만약 최소값이 현재 i 에 해당하는 배열의 값보다 크다면 배열의 i 인덱스부터 새로 계산하는 방식 ..
	 *
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int max = nums[0];
		int min = nums[0];
		int result = nums[0];
		// 이 tempMax/tempMin 의 두개의 포인터를 별도로 두는 것은,
		// loop 돌때 변하는 max,min 값의 변경을 막기 위해서이다..
		// max, min 으로 받게 되면 min 계산할때는 변경된 max 값으로 계산이 됨..
		int tempMax;
		int tempMin;

		// O(N)
		// i 번째 인덱스 이전에 곱한 값과 i번째 인덱스의 value 를 곱한 값보다,
		// i 번째 인덱스 단독 값이 더 큰 경우가 있음..
		// 그럴 경우 i 번째 인덱스 단독 값이 그 인덱스까지의 곱중에 최대값이 된다.
		// 예를 들어 {0, 2} 가 입력일때 위와 같은 현상이 나타난다.
 		for (int i = 1 ; i < nums.length ; i++) {
			tempMax = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
			tempMin = Math.min(Math.min(max * nums[i], min * nums[i]), nums[i]);
			result = Math.max(result, tempMax);
			max = tempMax;
			min = tempMin;
		}

		return result;
	}



	public static void main(String[] args) {

		MaximumProductSubArray maximumProductSubArray = new MaximumProductSubArray();
		int i = maximumProductSubArray.maxProduct(new int[] {-2, 3, -4});

		System.out.println(i);
	}
}
