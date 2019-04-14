package strategies.dp.optimization;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Quantization {
	private static final int INFINITE = 987654321;

	// A[]: 양자화해야할 수열. 정렬한 상태
	// pSum[] : A[] 의 부분합을 저장한다. pSum[i] 는 A[0]....A[i] 의 부분합
	// pSqSum[] : A[] 제곱의 부분합을 저장한다. pSqSum[i] 는 A[0]^2...A[i]^2 의 합
	int n;
	int [] A = new int[101];
	int [] pSum = new int[101];
	int [] pSqSum = new int[101];
	int [][] cache = new int[101][11];

	private void preCalc() {
		Arrays.sort(A, 0, n);

		pSum[0] = A[0];
		pSqSum[0] = A[0] * A[0];

		for (int i=1 ; i<n ; i++) {
			pSum[i] = pSum[i-1] + A[i];
			pSqSum[i] = pSqSum[i-1] + A[i] * A[i];
		}
	}

	// A[lo]...A[hi] 구간을 하나의 숫자로 표현할 때 최소 오차 합을 계산한다.
	// 가장 핵심 부분..
	private int minError(int lo, int hi) {
		// 부분합을 이용해 A[lo]~A[hi] 까지의 합을 구한다.
		int sum = pSum[hi] - (lo == 0 ? 0 : pSum[lo-1]);
		// 제곱의 부분합을 이용해 A[lo]~A[hi] 까지의 제곱의 부분합을 구한다.
		int sqSum = pSqSum[hi] - (lo == 0 ? 0 : pSqSum[lo-1]);

		// 평균을 반올림한 값으로 이 수들을 표현한다.
		// double 에서 0.5 를 더한다음에 int 로 변환시켜서 반올리을 처리하는 부분을 눈여겨 보자..
		int m = (int)(0.5 + (double)sum / (hi-lo+1));

		// sum(A[i]-m)^2 를 전개한 결과를 부분 합으로 표현
		int ret = sqSum - (2 * m * sum) + (m * m * (hi - lo + 1));

		return ret;
	}

	/**
	 * 즉, 아래 메소드의 input 의 의미는, 다음과 같다
	 *
	 * from index 부터 시작하는 수열 A[from] 을 parts 개의 묶음으로 묶어서,
	 * 반환값은 A[from]~A[N] 까지의 양자화한 값의 제곱의 최소값을 반환하겠다.
	 *
	 * @param from 수열의 시작 index
	 * @param parts 묶음 개수
	 * @return
	 */
	public int quantize(int from, int parts) {
		// base case 1 : 모든 숫자를 다 양자화했을때..
		if (from == n) {
			return 0;
		}

		// base case 2 : 숫자는 아직 남았는데, 더 묶을 수 없을때 아주 큰 값을 반환한다.
		if (parts == 0) {
			return INFINITE;
		}

		// 메모이제이션 된 값이 있다면,
		if (cache[from][parts] != -1) {
			return cache[from][parts];
		}

		cache[from][parts] = INFINITE;

		// 조각의 길이를 변화시켜가며 최소치를 찾는다.
		// minError 는 수열의 현재 묶음에서의 최소 제곱합,
		// quantize 는 수열의 다음 묶음들에서의 최소 제곱합이다.

		// 즉, 현재 위치의 최소값 + 이전위치까지의 최소값을 더한 값이 최종 결과 값이다.
		for (int partSize = 1 ; from + partSize <= n ; partSize++) {
			cache[from][parts] = Math.min(cache[from][parts]
				, minError(from, from + partSize - 1)
					+ quantize(from + partSize, parts -1));
		}

		return cache[from][parts];
	}

	public static void main(String[] args) {
		Quantization quantization = new Quantization();
		quantization.n = 9;

//		int [] input = {3,3,3,1,2,3,2,2,2,1};
		int [] input = {1,744,755,4,897,902,890,6,777};

		for (int i=0 ; i<input.length ; i++) {
			quantization.A[i] = input[i];
		}

		for (int [] subCache : quantization.cache) {
			Arrays.fill(subCache, -1);
		}

		quantization.preCalc();
		int quantize = quantization.quantize(0, 3);

		System.out.println(quantize);
	}

}
