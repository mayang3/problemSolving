package hackerrank.cs.dataStructure.hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class FrequencyQueries {
	static class FastReadWrite {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			StringTokenizer st;

			String next() {
				try {
					if (st == null || st.hasMoreTokens() == false) {
						// default delimeter
						st = new StringTokenizer(br.readLine());
					}

					return st.nextToken();

				} catch (Exception e) {
					e.printStackTrace();
				}

				return null;
			}

			int nextInt() {
				return Integer.parseInt(next());
			}

			long nextLong() {
				return Long.parseLong(next());
			}

			void print(Object o) {
				try {
					bw.write(String.valueOf(o));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			void println(Object o) {
				try {
					bw.write(o + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			void flush() {
				try {
					bw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	/**
	 * 문제 자체는 간단한게 시간이 빡빡함..
	 *
	 * countMap.containsValue() 하는데 O(n) time 이 걸리기 때문에,
	 * 카운트별 출현갯수를 카운트 하는 별도의 inverseMap 을 두었다.
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		FastReadWrite frw = new FastReadWrite();

		int q = frw.nextInt();

		Map<Integer, Integer> countMap = new HashMap<>();
		Map<Integer, Integer> inverseMap = new HashMap<>();
		StringBuilder sb = new StringBuilder();


		for (int i = 0; i < q; i++) {
			int op = frw.nextInt();
			int val = frw.nextInt();

			if (op == 1) {
				if (countMap.containsKey(val) == false) {
					countMap.put(val, 1);
					// 키 값이 최초로 입력되었을 경우에도, inverseMap 에는 기존 카운트가 1인 경우가 있을 수 있으므로 merge
					increase(inverseMap, 1);
				} else {
					increase(countMap, val);
					// 키 값이 기존에 있는 경우
					// inverseMap 에는 신규 카운트에서 1을 더해주고,
					// 기존 카운트에서 1을 빼준다. -> 이때 뺀 value 가 0이라면 삭제한다.
					increase(inverseMap, countMap.get(val));
					decreaseOrRemove(inverseMap, countMap.get(val)-1);
				}
			} else if (op == 2 && countMap.containsKey(val)) {
				int originVal = countMap.get(val);

				// 출현 빈도가 1줄었을때, inverseMap 에서는 기존 출현빈도에서 1을 제거해준다.
				decreaseOrRemove(inverseMap, originVal);

				// 줄어든 출현빈도의 카운트를 1 더해준다. -> 이때 줄어든 출현빈도가 0 이라면 저장할 필요가 없다.
				if (originVal > 1) {
					increase(inverseMap, originVal-1);
				}

				decreaseOrRemove(countMap, val);
			} else if (op == 3){
				if (inverseMap.containsKey(val)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			}
		}

		System.out.println(sb.toString());
	}

	static void decreaseOrRemove(Map<Integer, Integer> map, int val) {
		map.merge(val, -1, Integer::sum);

		if (map.get(val) == 0) {
			map.remove(val);
		}
	}

	static void increase(Map<Integer, Integer> map, int val) {
		map.merge(val, 1, Integer::sum);
	}
}
