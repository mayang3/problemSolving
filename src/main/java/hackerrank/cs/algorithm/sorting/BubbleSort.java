package hackerrank.cs.algorithm.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BubbleSort {
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

	public static void main(String[] args) {
		FastReadWrite frw = new FastReadWrite();

		int n = frw.nextInt();

		int [] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = frw.nextInt();
		}

		int swap = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;

					swap++;
				}
			}
		}

		System.out.println(String.format("Array is sorted in %d swaps.", swap));
		System.out.println(String.format("First Element: %d", arr[0]));
		System.out.println(String.format("Last Element: %d", arr[n-1]));
	}
}
