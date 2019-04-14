package hackerrank.cs.dataStructure.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class DynamicArray {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int Q = scanner.nextInt();

		List<LinkedList<Integer>> arrayList = new ArrayList<>();
		int lastAnswer=0;

		for (int i=0 ; i<N ; i++) {
			arrayList.add(new LinkedList<>());
		}

		while (Q-- > 0) {
			int action = scanner.nextInt();
			int x = scanner.nextInt();
			int y = scanner.nextInt();

			if (action == 1) {
				arrayList.get((x^lastAnswer)% N).add(y);
			} else if (action == 2) {
				LinkedList<Integer> integers = arrayList.get((x ^ lastAnswer) % N);
				lastAnswer = integers.get(y % integers.size());
				System.out.println(lastAnswer);
			}
		}
	}
}
