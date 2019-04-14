package strategies.anaysis;

import java.util.ArrayList;
import java.util.List;

/**
 * 알고리즘 분석의 실제 예제 프로그램
 *
 * 1차원 배열에서 연속된 부분 구간 중 그 합이 최대인 구간을 찾는 문제.
 *
 * 예를 들어, 배열 [-7,4,-3,6,3,-8,3,4] 에서 최대 합을 갖는 부분 구간은
 *
 * [4,-3,6,3] 으로 그 합은 10이다.
 *
 * 이 문제는 여러가지 알고리즘으로 해결 할 수 있는 것으로 유명한데,
 *
 * 아래에서는 시간 복잡도가 서로 다른 여러 알고리즘을 구현하고 각 알고리즘의 수행 시간이 어떻게 다른지 확인해보자.
 *
 * @author baejunbeom
 */
public class AlgorithmAnalysis {

	static final int MIN = Integer.MIN_VALUE;

	/**
	 * 시간복잡도 O(N^3) 의 첫번째 알고리즘
	 *
	 * @param A
	 * @return
	 */
	static int inefficientMaxSum(final List<Integer> A) {
		int N = A.size();
		int ret = MIN;

		for (int i=0 ; i<N ; i++) {
			for (int j=i ; j<N ; j++) {
				// 구간 A[i,j] 의 합을 구한다.
				int sum = 0;

				for (int k=i ; k<=j ; k++) {
					sum += A.get(k);
				}

				ret = Math.max(ret, sum);
			}
		}

		return ret;
	}

	/**
	 * 앞에서의 알고리즘에서 중복 계산을 줄인 알고리즘이다.
	 * 앞의 알고리즘에서 j가 증가한다고 해서, k 를 다시 1부터 계산할 필요는 없다.
	 *
	 * j 가 증가할때마다 앞에서부터 다시 계산해서 sum 값을 비교할 필요 없이,
	 * j 가 증가할때마다 MAX 값과 비교해서 max 에 저장해두면 된다.
	 *
	 * @param A
	 * @return
	 */
	static int betterMaxSum(final List<Integer> A) {
		int N = A.size();
		int ret = MIN;

		for (int i=0 ; i<N ; i++) {
			int sum=0;

			for (int j=i ; j<N ; j++) {
				// 구간 A[i,j]의 합을 구한다.
				sum += A.get(j);
				ret = Math.max(ret, sum);
			}
		}

		return ret;
	}

	static int fastMaxSum(final List<Integer> A, int lo, int hi) {
		// base case : 구간의 길이가 1일 경우
		if (lo == hi) return A.get(lo);

		int mid = (lo + hi) / 2;

		int left = MIN;
		int right = MIN;
		int sum = 0;

		// 1. 최대 구간이 중간에 걸쳐있는 경우를 찾는다.
		// 1-1. 왼쪽 구간 의 합중 최대 합의 구간을 찾는다.
		for (int i=mid ; i>=lo ; i--) {
			sum += A.get(i);
			left = Math.max(left, sum);
		}

		// 1-2. 오른쪽 구간 의 합중 최대 합이 구간을 찾는다.
		sum = 0;
		for (int j=mid+1 ; j<=hi ; j++) {
			sum += A.get(j);
			right = Math.max(right, sum);
		}

		// 최대 구간이 두 조각 중 하나에만 속해 있는 경우의 답을 재귀 호출로 찾는다.
		int single = Math.max(fastMaxSum(A, lo, mid),
			                  fastMaxSum(A, mid+1, hi));

		// 결국 리턴되는 값은 왼쪽, 오른쪽, 가운데 구간의 합중 최고합을 계속 리턴하게 된다.
		return Math.max(left+right, single);
	}

	/**
	 * A[i] 를 오른쪽 끝으로 갖는 구간의 최대 합을 반환하는 함수 maxAt(i) 를 정의해 보자.
	 * 그런데, A[i] 에서 끝나는 최대 합 부분 구간은 항상 A[i] 하나만으로 구성되어 있거나,
	 * A[i-1] 를 오른쪽 끝으로 갖는 최대합 부분 구간의 오른쪽에 A[i]를 붙인 형태로 구성되어 있다.
	 * 따라서, maxAt() 을 다음과 같은 점화식으로 표현할 수 있다.
	 *
	 * maxAt(i) = max(0, maxAt(i-1)) + A[i]
	 *
	 * 시간복잡도 O(N)
	 *
	 * @param A
	 * @return
	 */
	static int fastestMaxSum(final List<Integer> A) {
		int N = A.size();
		int ret = MIN;
		int psum = 0;

		for (int i=0 ; i<N ; i++) {
			psum = Math.max(psum, 0) + A.get(i);
			ret = Math.max(psum, ret);
		}

		return ret;
	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>();
		input.add(-7);
		input.add(4);
		input.add(-3);
		input.add(6);
		input.add(3);
		input.add(-8);
		input.add(3);
		input.add(4);

		int ret = fastestMaxSum(input);

		System.out.println(ret);
	}
}
