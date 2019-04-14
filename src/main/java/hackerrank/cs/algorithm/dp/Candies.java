package hackerrank.cs.algorithm.dp;

/**
 * https://www.hackerrank.com/challenges/candies/problem
 *
 * @author baejunbeom
 */
public class Candies {
	static int candies(int n, int[] arr) {
		if (arr == null || arr.length <= 0 || n <= 0) {
			return 0;
		}

		int targetIndex = findTargetIndex(arr);

		int [] result = new int[n];
		result[targetIndex] = 1;

		int left = targetIndex;
		int right = targetIndex;

		while (left >= 0 || right <= n-1) {

			if (left > 0) {
				// 왼쪽으로 이동
				if (arr[left] - arr[left - 1] < 0) {
					result[left - 1] = 1;
				} else {
					result[left - 1] = result[left] + 1;
				}
			}

			left--;

			if (right < n-1) {
				// 오른쪽으로 이동
				if (arr[right] - arr[right + 1] >= 0) {
					result[right + 1] = 1;
				} else {
					result[right + 1] = result[right] + 1;
				}
			}

			right++;
		}

		int sum = 0;

		for (int re : result) {
			sum += re;
		}

		return sum;
	}

	static int findTargetIndex(int[] arr) {

		for (int i=0 ; i<arr.length ; i++) {
			int beforeNum = i == 0 ? arr[i] + 1 : arr[i-1];
			int afterNum = i == arr.length - 1 ? arr[i-1] + 1 : arr[i+1];

			// 앞 뒤 숫자가 모두 자신보다 클때
			if (beforeNum >= arr[i] && afterNum >= arr[i]) {
				return i;
			}
		}

		throw new IllegalArgumentException();
	}

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		int[] arr = new int[n];
//		for(int arr_i = 0; arr_i < n; arr_i++){
//			arr[arr_i] = in.nextInt();
//		}
//		int result = candies(n, arr);
//		System.out.println(result);
//		in.close();
	}
}
