package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class LongestIncreaseSubstitution {

	public int lis(int [] input) {
		// base case : A is empty
		if (input == null || input.length <= 0) {
			return 0;
		}

		int ret = 0;

		for (int i=0 ; i<input.length ; i++) {
			List<Integer> inputList = new ArrayList<>();

			for (int j=i+1; j<input.length ; j++) {
				// 현재 자신의 위치 이후에 나오는 수열들에 대해서, 자신 보다 큰 수라면 다음 탐색 대상에 포함시킨다.
				if (input[i] < input[j]) {
					inputList.add(input[j]);
				}
			}

			ret = Math.max(ret, 1+lis(listToArray(inputList)));
		}

		return ret;
	}

	int n;
	int cache[] = new int[100];
	int S[] = new int[100];

	public int lis2(int start) {
		if (cache[start] == -1) {
			return cache[start];
		}

		// 항상 S[start] 는 있기 때문에 길이는 최하 1
		cache[start] = 1;

		// 캐시 값을 갱신한다!
		// 이 기법도 잘 기억해둘것..
		// dp 에서 먼저 캐시한 값이 나중에 탐색한 값보다 꼭 최적이라는 보장은 없기 때문에,
		// 비교해서 "최적 부분 구조" 에 해당하는 캐시값을 갱신해준다.
		for (int next = start+1 ; next < n ; next++) {
			if (S[start] < S[next]) {
				cache[start] = Math.max(cache[start], lis2(next) + 1);
			}
		}

		return cache[start];
	}


	private int[] listToArray(List<Integer> inputList) {
		int[] intList = new int[inputList.size()];

		for (int i=0 ; i<inputList.size() ; i++) {
			intList[i] = inputList.get(i);
		}

		return intList;
	}

	public static void main(String[] args) {
		LongestIncreaseSubstitution substitution = new LongestIncreaseSubstitution();

		Scanner sc = new Scanner(System.in);
		int [] res = new int[sc.nextInt()];

		for (int c=0 ; c<res.length ; c++) {
			int[] numArr = new int[sc.nextInt()];

			for (int i = 0; i < numArr.length; i++) {
				numArr[i] = sc.nextInt();
			}

			res[c] = substitution.lis(numArr);
		}

		for (int r : res) {
			System.out.println(r);
		}
	}
}
