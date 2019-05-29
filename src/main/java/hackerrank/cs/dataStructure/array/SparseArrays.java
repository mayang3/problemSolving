package hackerrank.cs.dataStructure.array;

import java.util.*;

/**
 * @author baejunbeom
 */
public class SparseArrays {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		Map<String, Integer> countMap = new HashMap<>();

		for (int i = 0; i < n; i++) {
			countMap.merge(scanner.next(), 1, Integer::sum);
		}

		int q = scanner.nextInt();

		for (int i = 0; i < q; i++) {
			System.out.println(countMap.getOrDefault(scanner.next(), 0));
		}
	}
}
