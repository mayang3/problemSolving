package hackerrank.cs.dataStructure.array;

import java.util.*;

/**
 * @author baejunbeom
 */
public class SparseArrays {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		List<String> set = new ArrayList<>();

		for (int i=0 ; i<N ; i++) {
			set.add(scanner.next());
		}

		int Q = scanner.nextInt();

		for (int i=0 ; i<Q ; i++) {
			String qs = scanner.next();

			int sum = 0;
			for (String origin : set) {
				if (origin.equals(qs)) {
					sum++;
				}
			}

			System.out.println(sum);
		}
	}
}
