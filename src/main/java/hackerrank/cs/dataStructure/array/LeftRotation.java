package hackerrank.cs.dataStructure.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LeftRotation {

	public static void main(String[] args) {
		FastReadWrite fastReadWrite = new FastReadWrite();

		int n = fastReadWrite.nextInt();
		int r = fastReadWrite.nextInt();

		Deque<Integer> dq = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			dq.add(fastReadWrite.nextInt());
		}

		for (int i = 0; i < r; i++) {
			dq.add(dq.poll());
		}

		for (int v : dq) {
			System.out.print(v + " ");
		}
	}

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

			void flush() {
				try {
					bw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
}
