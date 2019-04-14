package strategies.numberTheory;

/**
 * 유클리드 알고리즘은 두 수 p,q (p>q) 의 공약수의 집합은
 *
 * p-q 와 q의 공약수 집합과 같다는 점을 이용한다.
 *
 * 따라서, p,q의 최대공약수 gcd(p,q) 는 항상 p-q 와 q의 최대 공약수 gcd(p-q,q) 와 같다.
 *
 * e.g ) 6과 15의 최대 공약수는?
 *
 * gcd(6,15) = gcd(9,6) = gcd(3,6) = gcd(3,3) = gcd(0,3)
 *
 * @author baejunbeom
 */
public class Euclidean {

	public static int gcd(int p, int q) {
		if (p < q) {
			int t = p;
			p = q;
			q = t;
		}

		if (q == 0) return p;
		return gcd(p-q, q);
	}

	/**
	 * p<q 일 때 처리를 따로 하지 않음.
	 * p<q 인 입력이 들어올 경우, p % q = p 이므로, 다음 재귀 호출은 자동으로 gcd(q,p)가 된다.
	 * @param p
	 * @param q
	 * @return
	 */
	public static int gcd2(int p, int q) {
		if (q == 0) return p;
		return gcd2(q, p % q);
	}
}
