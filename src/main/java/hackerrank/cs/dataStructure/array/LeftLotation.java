package hackerrank.cs.dataStructure.array;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class LeftLotation {
	static int[] leftRotation(int[] a, int d) {
		LinkedList<Integer> la = new LinkedList<>();

		for (int v : a) {
			la.add(v);
		}

		while (d-- > 0)
			la.addLast(la.removeFirst());

		int [] ret = new int[a.length];

		for (int i=0 ; i<a.length ; i++) {
			ret[i] = la.removeFirst();
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int d = in.nextInt();
		int[] a = new int[n];
		for(int a_i = 0; a_i < n; a_i++){
			a[a_i] = in.nextInt();
		}
		int[] result = leftRotation(a, d);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
		}
		System.out.println("");


		in.close();
	}
}
