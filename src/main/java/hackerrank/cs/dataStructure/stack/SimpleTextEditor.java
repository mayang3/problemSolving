package hackerrank.cs.dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Initially your editor contains an empty string S.
 *
 * You must perform Q operations of the following 4 types:
 *
 * 1. append(W) - Append string W to the end of S.
 * 2. delete(k) - Delete the last k characters of S.
 * 3. print(k) - Print the kth character of S.
 * 4. undo() - Undo the last (not previously undone) operation of type 1 or 2,
 * reverting S to the state it was in prior to that operation.
 *
 * [Input Format]
 *
 * the first line contains an integer, Q, denoting the number of operations.
 *
 * Each line i of the Q subsequent lines (where 0 <= i < Q) defines an operation to be performed.
 *
 * Each operation starts with a single integer, t(where t /in {1,2,3,4}),
 *
 * denoting a type of operation as defined in the Problem Statement above.
 *
 * If the operation requires an argument, t is followed by its space-seperated argument.
 *
 * For example, if t=1 and W="abcd" , line i will be 1 abcd.
 *
 * [Constraints]
 *
 * 1 <= Q <= 10^6
 * 1 <= k <= |S|
 *
 * The sum of the lengths of all W in the input <= 10^6
 * The sum of k over all delete operations <= 2 * 10^6
 *
 * All input characters are lowercase English letters.
 *
 * It is guaranteed that the sequence of operations given as input is possible to perform.
 *
 * Output format
 *
 * Each operation of type 3 must print the kth character on a new line.
 *
 * Sample Input
 *
 * 8
 * 1 abc
 * 3 3
 * 2 3
 * 1 xy
 * 3 2
 * 4
 * 4
 * 3 1
 *
 * Sample Output
 *
 * c
 * y
 * a
 *
 *
 * @author baejunbeom
 */
public class SimpleTextEditor {

	static class Cmd {
		int c;
		String ss;

		Cmd(int c, String ss) {
			this.c = c;
			this.ss = ss;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int Q = scanner.nextInt();

		String S = "";
		Stack<Cmd> undoStack = new Stack();

		for (int i = 0; i < Q; i++) {
			int C = scanner.nextInt();

			if (1 == C) {
				// append
				String st = scanner.next();

				undoStack.add(new Cmd(C, st));

				S += st;
			} else if (2 == C) {
				// delete
				// k range is : 1 <= k < |S|
				int sIdx = Math.max(S.length() - scanner.nextInt(), 0);

				undoStack.add(new Cmd(C, S.substring(sIdx)));

				// 기존 String 에서 subString 할때 주의해야한다.
				// 이때, 그냥 replace 해버린다면 글자가 한글자인 경우 띄엄띄엄 제거될 수 있다.
				// 예를 들어, abcdefgc 일 때, c 를 replace 한다면 index 2,7 에 있는 c가 모두 삭제될 수 있음

				// 또한, 아래와 같이 index 로 자를때는 startIndex 와 endIndex 가 0으로 같을때를 주의해야 한다.
				// startIndex 와 endIndex 가 0이라는 것은, String 의 length 가 1인데.. 이것을 지워버리고 최종적으로 String 의 길이는 0을 남기겠다는 뜻이다..
				S = remove(S, 0, sIdx);

			} else if (3 == C) {
				System.out.println(S.toCharArray()[scanner.nextInt()-1]);
			} else if (4 == C) {
				Cmd pop = undoStack.pop();

				if (pop.c == 1) {
					S = remove(S, 0, S.length() - pop.ss.length());
				} else if (pop.c == 2) {
					S += pop.ss;
				}
			}
		}
	}


	static String remove(String S, int st, int end) {
		if (st == 0 && end == 0) {
			return "";
		}

		return S.substring(st, end);
	}
}
