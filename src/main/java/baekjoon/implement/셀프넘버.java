package baekjoon.implement;

import java.util.HashSet;
import java.util.Set;

public class 셀프넘버 {

	static void solve() {
		Set<Integer> set = new HashSet<>();

		for (int i=1 ; i<=10000 ; i++) {
			int sum = i;
			int quotient = i;

			while (quotient > 0) {
				sum += (quotient%10);
				quotient /= 10;
			}

			set.add(sum);

			if (!set.contains(i)) {
				System.out.println(i);
			}
		}
	}

	public static void main(String[] args) {
		solve();
	}

}
