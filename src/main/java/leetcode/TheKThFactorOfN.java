package leetcode;

import java.util.ArrayList;
import java.util.List;

public class TheKThFactorOfN {
	public int kthFactor(int n, int k) {
		List<Integer> divisors = new ArrayList();
		int sqrtN = (int) Math.sqrt(n);
		for (int x = 1; x < sqrtN + 1; ++x) {
			if (n % x == 0) {
				--k;
				divisors.add(x);
				if (k == 0) {
					return x;
				}
			}
		}

		// If n is a perfect square
		// we have to skip the duplicate
		// in the divisor list
		if (sqrtN * sqrtN == n) {
			++k;
		}

		// 현재 divisor 의 사이즈에서 k 만큼의 거리를 되돌린 값으로 나누면 해당 쌍의 값이 나온다.
		// 예를 들어, n=12, k=3 인 경우,
		// [1,2,3,4,6,12] 일때, divisors = [1,2,3] 이 된다.
		// 이때 만약 k<=3 이라면, 바로 답을 찾을 수 있으므로, 1,2,3 중에 한 값을 리턴한다.
		// 만약 k > 3 이라면 남은 값은 0보다 크게 된다.
		// 이때 k 는 3번 인덱스 ~ 나머지 값 사이에 존재한다.
		// 즉, [1,2,3 -> 4,6] k=5 이다.
		// 그런데 divisors = [1,2,3] 밖에 없으므로, 각 쌍에 해당하는 값을 찾아야 한다.
		// 그리고 이것은 현재 위치 (index 2) 에서 다시 남은 k 만큼 뒤로 돌아가면 된다.
		// 즉 index 3 에서 (자신의 위치를 포함해서) 두칸을 되돌아가면 index 1 이 되므로,
		// n / 2 (index 1 의 값) 을 하면 k=5 에 해당하는 값인 6이 나오게 된다.
		int nDiv = divisors.size();
		return (k <= nDiv) ? n / divisors.get(nDiv - k) : -1;
	}

	public static void main(String[] args) {
		TheKThFactorOfN theKThFactorOfN = new TheKThFactorOfN();
		System.out.println(theKThFactorOfN.kthFactor(12, 5));
	}
}
