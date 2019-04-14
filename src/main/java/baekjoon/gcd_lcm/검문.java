package baekjoon.gcd_lcm;

import java.util.*;

/**
 * https://www.acmicpc.net/board/view/11630
 *
 * n개의 수를 입력받아서 m으로 나눈 나머지에 대해 모든 n개의 나머지가 같게 하는 m들을 찾는 문제입니다.
 *
 * 어떤수 x를 y로 나누었을 때의 나머지가 n이라 하면 x = y * z + n으로 표현할 수 있으므로(z는 임의의 자연수)
 *
 * 저는 우선 입력받은 수들을 배열로 저장한 뒤, 오름차순 정렬을 한 것을 x0, x1, ..., xn이라 두었습니다.
 *
 * 그리고 나누는 수 m에 대해 모두 나머지가 n을 가진다면 가장 큰 수 xn과 가장 작은 수 x0에 대해
 *
 * (xn - x0) = m(zn - z0) 을 얻을 수 있고, (xn - x0)는 단순한 뺄셈이므로 쉽게 구할 수 있습니다.
 *
 * 따라서 나머지가 같게 하는 수 m은 (xn - x0)의 약수가 됨을 알 수 있습니다. (임의의 수 z와 곱셈을 하여 (xn - x0)이 되므로)
 *
 * 여기서, m 은 xn - x0 뿐만 아니고, x2 - x1 or x3-x1 등의 모든 경우의 약수이지만,
 *
 * 이 모든 경우는 xn - x0 의 약수에 포함되어야 하므로, 가장큰수 - 가장작은수의 약수만 구하면 된다.
 *
 * 1로 나누는 경우는 제외하므로 m에 1은 포함될 수 없으므로
 *
 * 2 <= m <= (xn - x0) 을 만족하는 (xn - x0)의 약수를 찾는 문제로 생각할 수 있다.
 */
public class 검문 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int [] arr = new int[N];

		for (int i=0 ; i<N ; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

		Set<Integer> divisorSet = getDivisor(arr[N - 1] - arr[0]);

		Set<Integer> ret = new HashSet<>();

		// Xn - X0 의 약수 중에 실제로 같은 나머지로 나누어지는 녀석들만 선별해낸다.
		for (int candidate : divisorSet) {
			int remainder = arr[0] % candidate;

			boolean mod = true;

			for (int i=1 ; i<N ; i++) {
				if (arr[i] % candidate != remainder) {
					mod = false;
					break;
				}
			}

			if (mod) {
				ret.add(candidate);
			}
		}

		List<Integer> list = new ArrayList<>(ret);

		Collections.sort(list);

		for (int v : list) {
			System.out.print(v + " ");
		}

	}

	/**
	 *
	 * @param n
	 * @return
	 */
	static Set<Integer> getDivisor(int n) {
		Set<Integer> ret = new HashSet<>();

		int sqrtn = (int)Math.sqrt(n);

		for (int i=2 ; i<=sqrtn ; i++) {
			if (n % i == 0) {
				ret.add(i);
			}
		}

		Set<Integer> additional = new HashSet<>();

		for (int v : ret) {
			additional.add(n/v);
		}

		ret.addAll(additional);
		ret.add(n); // 자기 자신도 약수에 꼭 포함시켜줘야 한다.

		return ret;
	}
}
