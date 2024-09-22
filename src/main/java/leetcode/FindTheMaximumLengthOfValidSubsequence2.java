package leetcode;

/**
 * @author neo82
 */
public class FindTheMaximumLengthOfValidSubsequence2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 10, 2};
        // 1,2,3,4,2
        int k = 6;

        FindTheMaximumLengthOfValidSubsequence2 findTheMaximumLengthOfValidSubsequence2 = new FindTheMaximumLengthOfValidSubsequence2();
        System.out.println(findTheMaximumLengthOfValidSubsequence2.maximumLength(nums, k));
    }

    /**
     * (currNum + prevNum) % k = mod
     *
     * currNum + prevNum 를 k 로 나눈 나머지가 mod 이므로..
     *
     * currNum + prevNum = k * Q(임의의 수) + mod
     *
     * prevNum = k * Q + mod - currNum
     *
     * prevNum % k = (k * Q + mod - currNum) % k
     *
     * -> 여기서 k * Q 는 k 의 배수이기 때문에 % k 를 하면 어차피 0 이 되므로 생략 가능하다.
     *
     * 그래서, 아래와 같이 식이 된다.
     *
     * prevNum % k = (mod - currNum + k) % k
     *
     * 또한 여기서 양 항에 모듈러 연산이 있기 때문에 두 항중 하나는 생략이 가능하다.
     *
     * 왜냐하면 이 알고리즘에서 이미 currNum 을 구할때 % k 를 했기 때문이다. (-> 결국 currNum 이 prevNum 이 된다.)
     *
     * 그래서, prevNum = (mod - currNum + k) % k 가 된다.
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[k][k];
        int maxLength = 1;

        for (int num : nums) {
            int currNum = num % k;

            for (int mod = 0; mod < k; mod++) {
                int prevNum = (mod - currNum + k) % k;

                // 여기서 dp[currNum][mod] 에 미리 계산된 값이 저장되어 있을 수 있다.
                // -> 나머지의 값이기 때문에, 이전 인덱스를 나눈 나머지 값이 저장되어 있을 수 있다.
                dp[currNum][mod] = Math.max(dp[currNum][mod], 1 + dp[prevNum][mod]);
                maxLength = Math.max(maxLength, dp[currNum][mod]);
            }
        }

        return maxLength;
    }
}
