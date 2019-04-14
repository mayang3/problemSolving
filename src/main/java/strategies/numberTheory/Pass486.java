package strategies.numberTheory;

/**
 * 1천만 이하의 모든 수의 약수의 수를 계산하는 알고리즘
 *
 * @author baejunbeom
 */
public class Pass486 {
	// Ten Million
	static final int TM = 1000 * 1000 * 10;
	// minFactor[i] = i의 가장 작은 소인수
	static int [] minFactor = new int[TM+1];
	// minFactorPower[i] = i의 소인수 분해에는 minFactor[i] 의 몇승이 포함되어 있는가?
	static int [] minFactorPower = new int[TM+1];
	// factor[i] = i 가 가진 약수의 수
	static int [] factors = new int[TM+1];

	static void getFactorsSmart() {
		factors[1] = 1;

		for (int n=2 ; n<=TM ; n++) {
			// 소수인 경우 약수가 두개밖에 없다. (최소 약수가 자기 자신인 경우는 소수)
			if (minFactor[n] == n) {
				minFactorPower[n] = 1;
				factors[n] = 2;
			} else {
				// 소수가 아닌 경우, 가장 작은 소인수로 나눈 수 (m) 의
				// 약수의 수를 응용해 n의 약수의 수를 찾는다.
				int p = minFactor[n];
				int m = n / p;

				// m이 p로 더이상 나누어지지 않는 경우
				if (p != minFactor[m]) {
					minFactorPower[n] = 1;
				} else {
					minFactorPower[n] = minFactorPower[m] + 1;
				}

				int a = minFactorPower[n];
				// n 의 약수의 갯수 = m 의 약수의 갯수 / n 의 가장작은 약수의 차수 * (n 의 가장 작은 약수의 차수 + 1)
				factors[n] = (factors[m] / a) * (a+1);
			}
		}
	}
}
