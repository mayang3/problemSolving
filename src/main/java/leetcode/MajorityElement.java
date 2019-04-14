package leetcode;

import java.util.*;

/**
 * @author baejunbeom
 */
public class MajorityElement {

	/**
	 * 최초 알고리즘.. Accept 하긴 했지만 성능이 그리 좋지 못함..
	 * 해시맵 생성/입력에 의외로 많은 시간이 걸리는듯..
	 *
	 * @param nums
	 * @return
	 */
//	public int majorityElement(int[] nums) {
//		Map<Integer, Integer> map = new HashMap<>();
//
//		int size = nums.length / 2;
//
//		for (int num : nums) {
//			Integer count = map.getOrDefault(num, 0);
//
//			if (count >= size) {
//				return num;
//			}
//
//			map.put(num, ++count);
//		}
//
//		return 0;
//	}

	/**
	 * leetcode 에 공유된 솔루션.. 여기서 count 는 정확하지 않다.
	 * 배열에 있는 원소들의 배열에 따라 1이 모자르기도 하고 더해지기도 한다.
	 *
	 * 하지만, 우리의 목적은 majority element 를 구하는것..
	 *
	 * 아래 알고리즘을 보니.. 붙어있는 element 의 개수와 연관관계를 생각하는듯..
	 *
	 * 예를 들어, n/2 개의 원소가 보장된다느 것은, 다음과 같은 결과를 보장한다.
	 *
	 * f(1) = 0, f(2) = 1, f(3) = 1, f(4) = 2, f(5) = 2, f(6) = 3, f(7) = 3
	 *
	 * 이 관계의 특성을 이용하는 듯 하는데.. 100퍼센트 이해는 못했다..
	 *
	 *
	 * O(n) 만에 끝나므로.. 맨 아래의 솔루션보다도 빠르다.
	 *
	 * 릿코드 저지 기준으로 71.40 beats
	 *
	 * @param nums
	 * @return
	 */
	public int majorityElement(int [] nums) {
		int major=nums[0], count = 1;
		for(int i=1; i<nums.length;i++){
			if(count==0){
				count++;
				major=nums[i];
			}else if(major==nums[i]){
				count++;
			}else count--;

		}
		return major;
	}

	/**
	 * 또는 아래와 같이 할수도 있다.
	 *
	 * 직관력은 제일 뛰어나다. 성능도 1번에 비하면 훨씬 낫다.
	 *
	 * Arrays.sort 는 O (n log n) 의 퍼포먼스를 보인다.
	 *
	 * 릿코드 저지 기준으로 40.63 beats
	 *
	 * @param nums
	 * @return
	 */
//	public int majorityElement(int [] nums) {
//		Arrays.sort(nums);
//		return nums[nums.length / 2];
//	}

	public static void main(String[] args) {
		int [] nums = {1,1,2,2,2,1,1,1,2};

		MajorityElement majorityElement = new MajorityElement();
		int i = majorityElement.majorityElement(nums);

		System.out.println(i);
	}
}
