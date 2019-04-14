package strategies.exhaustiveSearch;

import java.util.LinkedList;

/**
 * 순서에 상관없이 n 개중 네개를 고르는 문제를 찾는다고 해보자.
 * forLoop 과 같이 작성하게 된다면. n개중 다섯개를 찾는것과 같이 출력의 수가 늘어난다면,
 * 로직을 수정해야 한다.
 *
 * 하지만, recursion 을 이용한 exhaustive search 를 한다면 로직의 변경없이 유연한 변경이 가능하다.
 */
public class FromLoopToRecursion {

	/**
	 * 순서에 상관없이 n 개중 네개를 고르는 모든 경우 출력
	 * @param n
	 */
	static void forLoop(int n) {
		for (int i=0 ; i<n ; i++)
			for(int j=i+1 ; j<n ; j++)
				for(int k=j+1 ; k<n ; k++)
					for (int l = k + 1; l < n; l++)
						System.out.println(i + " " + j + " " + k + " " + l);
	}

	/**
	 * n : 전체 원소의 수
	 * picked : 지금까지 고른 원소들의 번호
	 * toPick : 더 고를 원소의 수
	 *
	 * @param n
	 * @param picked
	 * @param toPick
	 */
	static void pick(int n, LinkedList<Integer> picked, int toPick) {
		// base case
		if (toPick == 0) {
			System.out.println(picked);
			return;
		}

		int smallest = picked.isEmpty() ? 0 : picked.getLast() + 1;

		for (int next = smallest ; next < n ; next++) {
			picked.addLast(next);
			pick(n, picked, toPick-1);
			picked.removeLast();
		}
	}

	public static void main(String[] args) {
		forLoop(10);
		System.out.println();
		pick(10, new LinkedList<>(), 4);
	}
}
