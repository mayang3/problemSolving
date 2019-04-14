package hackerrank.cs.dataStructure.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class ArrayManipulation {

	/**
	 * time complexity O(n * m) 으로 너무 오래걸린다.
	 * -> solution 코드를 확인
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();

		Map<Integer, Long> map = new HashMap<>();

		long max = 0;

		for (int a0 = 0; a0 < m; a0++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			int k = in.nextInt();

			for (int i=a ; i<=b ; i++) {
				Long v = map.get(i);

				if (v == null) {
					long pv = Long.valueOf(k);
					max = Math.max(max, pv);
					map.put(i, pv);
				} else {
					long pv = v+k;
					max = Math.max(max, pv);
					map.put(i, pv);
				}
			}
		}

		System.out.println(max);

		in.close();
	}
}
