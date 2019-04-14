package stratgies2.dp_advance;

/**
 * @author baejunbeom
 */
public class KthAnswer {

	public static void main(String[] args) {
//		generate(2, 2, "");
//		generate2(2,2,"");

		calcBino();
//		generate3(2,2,"");
		System.out.println(kth(2,2,2));
	}

	/**
	 * s : 지금까지 만든 신호
	 * n : 더 필요한 - 의 개수
	 * m : 더 필요한 o 의 개수
	 *
	 * @param n
	 * @param m
	 * @param s
	 */
	static void generate(int n, int m, String s) {
		// base case n=m=0
		if (n==0 && m ==0) {
			System.out.println(s);
			return;
		}

		// 신호가 장점인 케이스의 아스키 코드가 단점인 케이스의 아스키 코드보다 항상 앞에 온다.
		if (n>0) generate(n-1, m, s+"-");
		if (m>0) generate(n, m-1, s+"o");
	}

	static int skip=2;

	/**
	 * skip 개를 건너뛰고 출력한다.
	 *
	 * 이때, 전역변수 skip 은 k-1 로 초기화 된다.
	 * skip 이 0보다 작은 경우를 기저사례로 선택해 종료한 것을 눈여겨 보자.
	 * skip 이 -1까지 감소했다는 말은 이미 k번째 신호를 출력했다는 의미이다.
	 *
	 * 더 이상 다른 신호를 생성할 필요가 없기 때문에 재귀 호출을 하지 않는다.
	 * 따라서 k번째 신호를 찾은 뒤에 generate2() 는 (거의) 곧장 종료하게 된다.
	 *
	 * (핵심) 3번째 모스부호를 출력한다고 하면, 앞의 1,2 번과 뒤의 4~ 이후를 모두 건너 뛰어야 한다.
	 *
	 * 이때, 앞의 1,2 번이 건너뛰는 부분은 if (skip == 0) 의 조건에 의해 수행되고,
	 * 뒤의 4~ 이후를 건너뒤는 부분은 if (skip < 0)의 조건에 의해 수행된다.
	 *
	 * @param n
	 * @param m
	 * @param s
	 */
	static void generate2(int n, int m, String s) {
		// base case : skip < 0
		if (skip < 0) {
			return;
		}

		// base case : n=m=0
		if (n==0 && m==0) {
			if (skip == 0) System.out.println(s);
			skip--; // 출력이후에 감소를 시키고 있기 때문에, 만약 skip 을 k로 초기화 한다면 4번째 값이 찍히게 된다.
			return;
		}

		if (n>0) generate2(n-1, m, s+"-");
		if (m>0) generate2(n, m-1, s+"o");

	}

	/*
		k-1 개 건너뛰고 첫 번째 신호를 출력하는 좀더 똑똑한 알고리즘
	 */

	// K 의 최대값 + 100. 오버플로를 막기 위해 이보다 큰 값은 구하지 않는다.
	static final int M = 1000000000 + 100;
	// nCr => n+mCn
	static int [][] bino = new int[201][201]; // (n,m <= 100)

	// 필요한 모든 이항 계수를 미리 계산해 둔다.

	/**
	 * O(nm)
	 */
	static void calcBino() {
		for (int i=0 ; i<=200 ; i++) {
			// 이항계수에서 무조건 1이 되는 경우
			bino[i][0] = bino[i][i] = 1;

			// 여기서는 위의 이항계수에서 무조건 1이 되는 케이스 사이에 있는 답이 구해진다.
			// 즉 bino[i][0] < ... < bino[i][i] 사이의 답이 구해진다.
			// bino[i][0] 은 i 개 중에 아무것도 선택하지 않는 방법이므로 무조건 1가지가 되고,
			// bino[i][i] 는 i 개 중에 i개를 순서없이 선택하는 방법이므로 무조건 1가지 방법이 된다.
			for (int j=1 ; j<i ; j++) {
				bino[i][j] = Math.min(M, bino[i-1][j-1] + bino[i-1][j]);
			}
		}
	}

	/**
	 * O(n+m)
	 *
	 * @param n
	 * @param m
	 * @param s
	 */
	static void generate3(int n, int m, String s) {
		if (skip < 0) return;

		if (n==0 && m==0) {
			if (skip == 0) {
				System.out.println(s);
			}

			skip--;
			return;
		}

		// 현재 만들 수 있는 조합의 수가 건너뛸 수보다 적다면, 건너뛴다.
		if (bino[n+m][n] <= skip) {
			skip -= bino[n+m][n];
			return;
		}

		if (n>0) generate3(n-1, m, s+"-");
		if (m>0) generate3(n, m-1, s+"o");
	}

	/*
		최종 버전
		hint!) generate() 처럼 모든 문자열을 만드는 완전 탐색 함수에서 시작하는 것이 아니라,
		사전순으로 가장 먼저 오는 신호 하나를 계산하는 재귀 함수에서 시작하는 것이다.

		n 개의 장점과 m 개의 단점으로 구성된 신호 중 skip 개를 건너뛰고 제일 먼저 오는 신호를 반환하는 함수 kth(n,m,skip) 을 만들어 보자.
	 */

	/**
	 *
	 * @param n 더 필요한 "-" 의 갯수
	 * @param m 더 필요한 "o" 의 갯수
	 * @param skip
	 * @return
	 */
	static String kth(int n, int m, int skip) {
		// n == 0 인 경우 나머지 부분은 전부 o일 수밖에 없다.
		if (n == 0) {
			StringBuffer sb = new StringBuffer();
			for (int i=0 ; i<m ; i++) {
				sb.append("o");
			}
			return sb.toString();
		}

		if (skip < bino[n+m-1][n-1]) {
			return "-" + kth(n-1, m, skip);
		}

		return "o" + kth(n, m-1, skip - bino[n+m-1][n-1]);
	}

}
