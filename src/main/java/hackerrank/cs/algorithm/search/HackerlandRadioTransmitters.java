package hackerrank.cs.algorithm.search;

import java.util.*;

/**
 * bear://x-callback-url/open-note?id=948FACD6-43F9-4306-835F-01B9754CD192-54277-00034D7B5AD06F27
 */
public class HackerlandRadioTransmitters {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		TreeSet<Integer> ts = new TreeSet<>();

		for (int i = 0; i < n; i++) {
			ts.add(scanner.nextInt());
		}

		int cnt = 0;

		int first = ts.first();

		while (true) {
			// 1. 첫번째 좌표의 위치를 가져온다.
			Integer ceiling = ts.ceiling(first);

			// 2. 첫번째 좌표 + k 번째 보다 같거나 작은 house 중에 가장 큰 좌표를 가진 house 에 안테나를 설치한다.
			// 이때, 2 번의 부분 작업이 항상 참임을 귀류법으로 증명할 수 있어야 한다.
			// 즉, 첫번째 좌표 + k 번째 보다 큰 house 중에 최적의 해가 있음이 모순임을 보이고,
			// 첫번째 좌표 + k 번째보다 같거나 작은 house 중에 가장 크지 않은 좌표를 가진 house 중에 최적의 해가 있음이 모순임을 보이면 이 풀이는 항상 성립한다.
			Integer floor = ts.floor(ceiling + k);

			cnt++;

			// 3. (2) 에서 설치한 안테나의 좌표 + k 보다 큰 좌표를 가진 house 중에 가장 작은 좌표를 가진 house 를 찾는다.
			// 이 house 는 다음 시작점이 된다.
			Integer higher = ts.higher(floor + k);

			if (higher == null) {
				break;
			}

			first = higher;
		}

		System.out.println(cnt);
	}
}
