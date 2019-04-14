package strategies.linearDataStructure;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author baejunbeom
 */
public class Josephus {

	/**
	 * 조세푸스의 역사학자 문제
	 *
	 * @param n
	 * @param k
	 * @return
	 */
	static int[] cal(LinkedList<Integer> n, int k) {

		int i = 0;

		n.remove(0);

		while (n.size() > 2) {

			if (i == k-1) {
				n.removeFirst();
				i=0;
				continue;
			}

			n.addLast(n.poll());
			i++;
		}

		int [] ret = new int[2];

		ret[0] = n.pollFirst();
		ret[1] = n.pollFirst();

		Arrays.sort(ret);

		return ret;
	}

	public static void main(String[] args) {

		LinkedList<Integer> n = new LinkedList<>();

		for (int i=1 ; i<=40 ; i++) {
			n.add(i);
		}

		int[] cal = cal(n, 3);

		System.out.println(Arrays.toString(cal));
	}
}
