package algospot;

import java.util.*;

public class LUNCHBOX {

	static int hit(int N, int [] M, int [] E) {
		List<int []> order = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			order.add(new int[]{E[i], i});
		}

		Collections.sort(order, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return 0;
			}
			return o1[0] > o2[0] ? -1 : 1;
		});

		int ret = 0;
		int beginEat = 0;

		for (int i = 0; i < N; i++) {
			int box = order.get(i)[1];
			beginEat += M[box];
			ret = Math.max(ret, beginEat + E[box]);
		}

		return ret;
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt();

			int [] M = new int[N];
			int [] E = new int[N];

			for (int i = 0; i < N; i++) {
				M[i] = scanner.nextInt();
			}

			for (int i = 0; i < N; i++) {
				E[i] = scanner.nextInt();
			}

			System.out.println(hit(N, M, E));
		}
	}
}
