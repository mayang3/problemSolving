package hackerrank.compete;

import java.util.Arrays;
import java.util.Scanner;

/**
 */
public class ExamRush {

	static int examRush(int[] tm, int t) {

		Arrays.sort(tm);

		int count = 0;

		for (int tt : tm) {
			t -= tt;

			if (t<0) break;

			count++;
		}

		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int t = in.nextInt();
		int[] tm = new int[n];
		for(int tm_i = 0; tm_i < n; tm_i++){
			tm[tm_i] = in.nextInt();
		}
		int result = examRush(tm, t);
		System.out.println(result);
		in.close();
	}
}
