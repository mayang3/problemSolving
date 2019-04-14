package hackerrank.cs.dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

public class FindMaximumIndexProduct {
	
	static class Pair {
		int value;
		int index;

		Pair(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	/**
	 * left -> right 방향으로 움직이면서 leftProd 를 채우고,
	 * right -> left 방향으로 움직이면서 rightProd 를 채운다.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// n <= 10^5
		int n = scanner.nextInt();

		int [] arr = new int[n+1];
		int [] leftProd = new int[n+1];
		int [] rightProd = new int[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = scanner.nextInt();
		}

		Stack<Pair> s1 = new Stack<Pair>();
		Stack<Pair> s2 = new Stack<Pair>();

		// left -> right
		// sample : [5, 4, 3, 4, 5]
		for (int i = 1; i <= n; i++) {
			// 1. stack 에 들어있는 값이 현재 값보다 적거나 같다면, 모두 빼낸다.
			// 아래 3번 로직에서 stack 에 push 되는 경우..
			// 1-1. arr[i+1] > arr[i] 일 경우, arr[i] 보다 val 이 작은 녀석들을 pop 한것은 항상 유효하다.
			// 1-2. arr[i+1] < arr[i] 일 경우, arr[i+1] ~ arr[i] 사이의 값이 pop 되었다면 유효하지 않을 수 있을것 같은데..
			//      -> 그렇지 않음.. 왜냐하면 arr[i+1] ~ arr[i] 사이의 값이 pop 되었다는 것은 arr[i+1] 이전의 최대값은 arr[i] 라는 뜻이기 때문에 항상 유효함..
			// 예를 들어, [6,7,8,4] 를 보면..
			// arr[i] = 8, arr[i+1] = 4 라고 보자..
			// 그러면, 6,7 이 pop 되었다고 하더라도, arr[i+1] 의 위치에서 보면 arr[i]=8 이 최대값이다.
			while (!s1.empty() && s1.peek().value <= arr[i]) {
				s1.pop();
			}

			// 2. 비어있지 않다면, 일단 현재 값을 넣는다.
			if (s1.empty()) {
				s1.push(new Pair(arr[i], i));
				continue;
			}

			// 3. 앞에서 arr[i] 보다 작은 녀석들은 모두 pop 해버렸기 때문에, s1 의 peek 값이 항상 최대값이다.
			leftProd[i] = s1.peek().index;
			// 4. 현재 값을 추가한다.
			s1.push(new Pair(arr[i], i));
		}

		// right -> left
		for (int i = n; i >= 1; i--) {
			while (!s2.empty() && s2.peek().value <= arr[i]) {
				s2.pop();
			}

			if (s2.empty()) {
				s2.push(new Pair(arr[i], i));
				continue;
			}

			rightProd[i] = s2.peek().index;

			s2.push(new Pair(arr[i], i));
		}

		long ans = 0;

		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, 1L * leftProd[i] * rightProd[i]);
		}

		System.out.println(ans);
	}
}
