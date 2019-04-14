package hackerrank.cs.algorithm.search;

import java.util.*;

public class GreedLandMetro {

	static class Track {
		Map<Long, List<Pair>> map = new HashMap<Long, List<Pair>>();

		void add(long r, long start, long end) {
			List<Pair> pairs = map.get(r);

			if (pairs == null || pairs.isEmpty()) {
				LinkedList<Pair> ll = new LinkedList<Pair>();
				ll.add(new Pair(start, end));
				map.put(r, ll);
			} else {
				pairs.add(new Pair(start,end));
				map.put(r, pairs);
			}
		}

		// track 의 겹친 부분들을 합친다.
		void merge() {
			for (Map.Entry<Long, List<Pair>> entry : map.entrySet()) {
				List<Pair> pairs = entry.getValue();

				if (pairs == null || pairs.isEmpty()) {
					continue;
				}

				// start 포인트 순서대로 정렬
				// 바로 뒤에 있는 것들과의 merge 여부만을 확인하기 위해서..
				Collections.sort(pairs, new Comparator<Pair>() {
					@Override
					public int compare(Pair o1, Pair o2) {
						return (int)(o1.start - o2.start);
					}
				});

				LinkedList<Pair> ll = new LinkedList<Pair>();
				ll.addLast(pairs.get(0));

				for (int i = 1; i < pairs.size(); i++) {
					Pair p1 = ll.removeLast();
					Pair p2 = pairs.get(i);

					if (p2.start <= p1.end) {
						ll.addLast(new Pair(p1.start, Math.max(p1.end,p2.end)));
					} else {
						ll.addLast(p1);
						ll.addLast(p2);
					}
				}

				map.put(entry.getKey(), ll);
			}
		}

		long getSize(long m) {
			long sum = 0;

			for (Map.Entry<Long, List<Pair>> entry : map.entrySet()) {
				long train = 0;

				// train 의 길이를 먼저 다 더하고 최종적으로 m 에서 빼야 한다.
				// 왜냐하면.. pair 가 여러개 있는 경우, (즉, 떨어져있는 track 이 여러개 있다면)
				// m 에서 먼저 뺀다면, 중복해서 lampposts 이 더해질 수 있다.
				for (Pair p : entry.getValue()) {
					train += (p.end - p.start + 1);
				}

				sum += (m - train);
			}

			return sum;
		}

		// k 의 숫자는 같은 지역에 여러개가 겹칠 수 있기 때문에,
		// 최종적으로 k 의 track이 머지된 후에, 기본식에 대입해야 한다.
		long getKSize() {
			return map.size();
		}

	}

	static class Pair {
		long start;
		long end;

		Pair(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// n,m <= 10^9
		long n = scanner.nextLong();
		long m = scanner.nextLong();
		// k is track number
		long k = scanner.nextLong();

		Track track = new Track();

		for (int i = 0; i < k; i++) {
			long r = scanner.nextLong();
			long c1 = scanner.nextLong();
			long c2 = scanner.nextLong();

			track.add(r, c1, c2);
		}

		track.merge();

		long ans = Math.max(n - track.getKSize(), 0) * m + track.getSize(m);

		System.out.println(ans);
	}
}
