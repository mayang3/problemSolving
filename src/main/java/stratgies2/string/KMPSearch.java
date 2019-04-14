package stratgies2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class KMPSearch {

	public static void main(String[] args) {
		System.out.println(kmpSearch("ABABDABACDABABCABAB".toCharArray(), "ABABCABAB".toCharArray()));
	}

	/**
	 * 짚더미 H의 부분 문자열로 바늘 N이 출현하는 시작 위치들을 모두 반환한다.
	 *
	 * getPartialMatch 를 제외하고 생각하면 반복문의 전체 수행 횟수는 O(|H|) 이다.
	 *
	 * 즉, 바늘의 길이와 상관없이 항상 짚더미의 길이에만 비례하는 것이다.!
	 *
	 * @param H 짚더미
	 * @param N 바늘
	 * @return
	 */
	static List<Integer> kmpSearch(final char [] H, final char [] N) {
		int n = H.length;
		int m = N.length;

		List<Integer> ret = new ArrayList<>();

		// pi[i] = N[..i] 의 접미사도 되고 접두사도 되는 문자열의 최대 길이
		int [] pi = getPartialMatch(N);

		int begin = 0; // i
		int matched = 0; // 매칭된 횟수 , j

		while (begin <= n - m) {
			// 만일 짚더미의 해당 글자가 바늘의 해당 글자와 같다면,
			// H 가 검색 대상, N 이 패턴
			if (matched < m && H[begin + matched] == N[matched]) {
				matched++;
				// 결과적으로 m 글자가 모두 일치했다면 답에 추가한다.
				if (matched == m) {
					ret.add(begin);
				}
			} else {
				// 예외 : matched 가 0인 경우에는 다음 칸에서부터 계속
				if (matched == 0) {
					begin++;
				} else {
					// 다음 비교를 위한 기준인 begin 을 옮긴다.
					begin += matched - pi[matched-1];
					// begin 을 옮겼다고 처음부터 다시 비교할 필요가 없다.
					// 옮긴 후에도 pi[matched-1] 만큼은 항상 일치하기 때문이다.
					// 때문에 pi[matched-1] 의 길이만큼은 건너뛰고 확인하면 된다.
					matched = pi[matched-1];
				}
			}
		}

		return ret;
	}

	/**
	 * N 에서 자기 자신을 찾으면서 나타나는 부분 일치를 이용해, pi[] 를 계산한다.
	 * pi[i]=N[..i] 의 접미사도 되고 접두사도 되는 문자열의 최대 길이
	 *
	 * eg) 만약 ABA 라면 pi[2] 의 값은 1이다.
	 * 즉, 홀수일 경우 중간에 있는 경계값은 pi 의 길이에 포함되지 않는다.
	 *
	 * @param N
	 * @return
	 */
	static int [] getPartialMatch(char [] N) {
		int m = N.length;
		int [] pi = new int[m];

		// KMP 로 자기 자신을 찾는다.
		// N 을 N에서 찾는다. begin=0 이면 자기 자신을 찾아버리니까 안됨!
		int begin=1;
		int matched=0;

		// 비교할 문자가 N의 끝에 도달할 때까지 찾으면서 부분 일치를 모두 기록한다.
		while (begin + matched < m) {
			if (N[begin + matched] == N[matched]) {
				matched++;
				pi[begin+matched-1] = matched;
			} else {
				if (matched == 0) {
					begin++;
				} else {
					begin += matched - pi[matched-1];
					matched = pi[matched-1];
				}
			}
		}

		return pi;
	}

	/**
	 * N 에서 자기 자신을 찾으면서 나타나는 부분 일치를 이용해 pi[] 를 계산한다.
	 * pi[i]=N[..i] 의 접미사도 되고 접두사도 되는 문자열의 최대 길이
	 *
	 * @param N
	 * @return
	 */
	private static int [] getPartialMatchNaive(char [] N) {
		int m = N.length;
		int [] pi = new int[m];

		// 단순한 문자열 검색 알고리즘을 구현한다.
		for (int begin=1 ; begin<m ; begin++) {
			for (int i=0 ; i+begin < m ; i++) {
				if (N[begin+i] != N[i]) {
					break;
				}
				// i+1 글자가 서로 대응되었다.
				pi[begin+i] = Math.max(pi[begin+i], i+1);
			}
		}

		return pi;
	}
}
