package strategies.bitmask;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Graduation {
	static final int INF = 987654321;
	static final int MAX_N = 4096;

	// 전공 과목의 수
	static int n = 4;
	// 들어야할 과목의 수
	static int k = 4;
	// 학기의 수
	static int m = 4;
	// 한 학기에 최대로 들을 수 있는 과목의 수
	static int l = 4;

	// prerequisite[i] = i번째 과목의 선수과목 집합
	static int [] prerequisite = new int[MAX_N];
	// classes[i] = i번째 학기에 개설되는 과목의 집합
	static int [] classes = new int[10];

	static int [][] cache = new int[10][1 << MAX_N];

	/**
	 * 이번 학기가 semester 이고, 지금까지 들은 과목의 집합이 taken 일 때,
	 * k개 이상의 과목을 모두 들으려면 몇 학기나 더 있어야 하는가?
	 * 불가능한 경우에는 INF 를 반환한다.
	 * @param semester
	 * @param taken
	 * @return
	 */
	static int graduate(int semester, int taken) {
		// base case 1 : k개 이상의 과목을 이미 들은 경우
		if (Integer.bitCount(taken) >= k) return 0;
		// base case 2 : m학기가 전부 지난 경우
		if (semester == m) return INF;

		if (cache[semester][taken] != -1) {
			return cache[semester][taken];
		}

		cache[semester][taken] = INF;

		// 이번 학기에 들을 수 있는 과목 중 아직 듣지 않은 과목들을 찾는다.
		int canTake = (classes[semester] & ~taken);

		// 선수 과목을 다 듣지 않은 과목들을 걸러낸다.
		for (int i=0 ; i<n ; i++) {
			if ((canTake & (1 << i)) > 0
				&& (taken & prerequisite[i]) != prerequisite[i]) {
				canTake &= ~(1 << i);
			}
		}

		// 이 집합의 모든 부분집합을 순회한다.
		for (int take = canTake ; take > 0 ; take = ((take - 1) & canTake)) {
			// 한 학기에 1과목까지만 들을 수 있다.
			if (Integer.bitCount(take) > l) continue;

			cache[semester][taken] = Math.min(cache[semester][taken], graduate(semester+1, taken | take) + 1);
		}

		// 이번 학기에 아무것도 듣지 않을 경우
		cache[semester][taken] = Math.min(cache[semester][taken], graduate(semester+1, taken));
		return cache[semester][taken];
	}

	public static void main(String[] args) {
		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		System.out.println(graduate(1, 1));
	}
}
