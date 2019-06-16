package hackerrank.cs.dataStructure.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author neo82
 */
public class SimpleTextEditor {

	static class FastReadWrite {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			try {
				if (st == null || st.hasMoreTokens() == false) {
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
	}

	public static void main(String[] args) {
		FastReadWrite scanner = new FastReadWrite();

		int q = scanner.nextInt();

		StringBuilder S = new StringBuilder();
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < q; i++) {
			int op = scanner.nextInt();

			switch (op) {
				case 1:
					stack.push(S.toString());
					S.append(scanner.next());
					break;
				case 2:
					stack.push(S.toString());
					int k = scanner.nextInt();
					S.delete(S.length() - k, S.length());
					break;
				case 3:
					System.out.println(S.charAt(scanner.nextInt() - 1));
					break;
				case 4:
					S.delete(0, S.length());
					S.append(stack.pop());
					break;
				default:
					throw new IllegalArgumentException("Ooops");
			}
		}
	}
}
