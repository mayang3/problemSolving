package baekjoon.sort;

// https://www.acmicpc.net/problem/2108

import java.util.*;

/**
 * 산술평균 : N 개의 수들의 합을 N으로 나눈 값
 * 중앙값 : N 개의 수들을 증가하는 순서로 나열했을 경우, 그 중앙에 위치하는 값
 * 최빈값 : N 개의 수들 중 가장 많이 나타나는 값
 * 범위 : N 개의 수들 중 최대값과 최소값의 차이
 */
public class 통계학 {
	static class MaxCnt {
		int num;
		int cnt;

		MaxCnt(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int [] arr = new int[N];

		int sum = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
			sum += arr[i];
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);

			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}

		// 산술평균
		System.out.println(Math.round((double)sum / (double)N));

		// 중앙값
		Arrays.sort(arr);
		System.out.println(arr[arr.length/2]);

		// 최빈값
		PriorityQueue<MaxCnt> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.cnt == o2.cnt) {
				if (o1.num == o2.num) {
					return 0;
				}

				// cnt 는 클수록 앞으로 오고
				// num 은 작을수록 앞으로 오므로, 이 두개의 조건은 반대가 되어야 한다.
				return o1.num < o2.num ? -1 : 1;
			}

			return o1.cnt < o2.cnt ? 1 : -1;
		});

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			pq.add(new MaxCnt(entry.getKey(), entry.getValue()));
		}

		MaxCnt first = pq.poll();
		MaxCnt second = pq.poll();

		if (second != null && first.cnt == second.cnt) {
			System.out.println(second.num);
		} else {
			System.out.println(first.num);
		}

		// 범위
		System.out.println(max - min);
	}

}
