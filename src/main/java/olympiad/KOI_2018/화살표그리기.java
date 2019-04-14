package olympiad.KOI_2018;

import java.util.*;

public class 화살표그리기 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Map<Integer, LinkedList<Integer>> map = new HashMap<>();

		for (int i = 0; i < n; i++) {
			int x = scanner.nextInt();
			int color = scanner.nextInt();

			if (map.containsKey(color)) {
				LinkedList<Integer> ll = map.get(color);
				ll.add(x);
				map.put(color, ll);
			} else {
				LinkedList<Integer> ll = new LinkedList<>();
				ll.add(x);

				map.put(color, ll);
			}
		}

		long sum = 0;

		for (Map.Entry<Integer, LinkedList<Integer>> entry : map.entrySet()) {
			if (entry.getValue().size() < 2) {
				continue;
			}

			LinkedList<Integer> xList = entry.getValue();
			Collections.sort(xList);

			for (int i = 0; i < xList.size(); i++) {
				int cur = xList.get(i);

				if (i == 0) {
					sum += (xList.get(i+1) - cur);
					continue;
				}

				if (i == xList.size() - 1) {
					sum += cur - xList.get(i-1);
					continue;
				}

				sum += Math.min(cur - xList.get(i-1), xList.get(i+1) - cur);
			}
		}

		System.out.println(sum);
	}
}
