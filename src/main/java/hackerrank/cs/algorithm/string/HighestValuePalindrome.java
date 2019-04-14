package hackerrank.cs.algorithm.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("ALL")
public class HighestValuePalindrome {

	/**
	 * 기본적인 룰들)
	 *
	 * 1. 앞쪽 숫자부터 바꾸는게 항상 유리하다.
	 * 2. 가능하면 앞, 뒤의 숫자를 9 로 맞추는게 좋다.
	 * 3. k 가 남는 경우 2번을 수행할때, 꼭 앞뒤 숫자가 다른 경우가 아니라도 가능하다.
	 * (앞 뒤 숫자가 같은 경우라도 k 가 남는다면 둘다 9로 바꾸는게 유리하다.)
	 *
	 * dc (diffIndexSet) 을 앞쪽 char 와 뒤쪽 char 가 일치하지 않는 숫자라고 정의하자.
	 *
	 * 그러면.. 다음과 같이 정의할 수 있다.
	 *
	 * 1. k - dc < 0 이면, 불가능
	 * 2. k == 0 이면 그대로 리턴
	 * 3. k != 0 이고, k - dc >= 0 인 경우,
	 *
	 * 3-1. k-dc == 0 인 경우, 앞뒤 숫자중 틀린 숫자만 바꿔야한다. 그렇게만 해도 k 를 다 써버리게 된다.
	 * 3-2. k-dc == 1 인 경우,
	 * 	3-2-1. 앞뒤 숫자가 다른 경우, 앞 뒤 숫자중 "9" 가 아닌 경우에만 "9" 로 바꾼다 -> (이미 9인 경우 k 를 써버릴 필요가 없기 때문에..)
	 * 	3-2-2. s 가 홀수인 경우 l == r 인 경우가 있을 수 있다. 이 경우 k 가 남아있다면 이 숫자를 바꿔준다.
	 * 3-3. k-dc >= 2 인 경우, 기본적으로 앞, 뒤 숫자 모두를 바꿀 수 있다.
	 * 	3-3-1. 다만, 이미 앞 뒤 숫자중 이미 숫자가 "9" 인 경우에는 숫자를 바꾸게 되면 불필요하게 k 를 소모하게 되므로 바꾸지 않는다.
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();
		String s = scanner.next();

		char [] ret = Arrays.copyOf(s.toCharArray(), s.length());

		Set<Integer> diffIndexSet = getDiffIndexSet(s);

		// k - dc < 0 이면, 불가능하다.
		if (k - diffIndexSet.size() < 0) {
			System.out.println(-1);
			return;
		}

		// k == 0 이면, 그대로 리턴한다.
		// 참고) dc 가 0일 때 그대로 리턴하면 안된다.
		// dc 가 0이더라도 k > 0 이라면 더욱 최적화 할 수 있다.
		if (k == 0) {
			System.out.println(print(ret));
			return;
		}

		int l = 0;
		int r = s.length()-1;

		// k - dc > 0 인 경우,
		while (l <= r) {
			int diff = k - diffIndexSet.size();

			// k - dc == 0 이면, 앞, 뒤 숫자중 큰 숫자만 바꾼다.
			if (diff == 0) {
				if (ret[l] < ret[r]) {
					ret[l] = ret[r];
					k--;
					diffIndexSet.remove(l);
				} else if (ret[l] > ret[r]){
					ret[r] = ret[l];
					k--;
					diffIndexSet.remove(l);
				}
			} else if (diff == 1) {
				if (ret[l] != ret[r]) {
					diffIndexSet.remove(l);

					if (ret[l] != '9') {
						ret[l] = '9';
						k--;
					}

					if (ret[r] != '9') {
						ret[r] = '9';
						k--;
					}
				} else if (l == r) {
					if (ret[l] != '9') {
						ret[l] = '9';
						k--;
					}
				}
			} else if (diff >= 2) {
				if (ret[l] != ret[r]) {
					diffIndexSet.remove(l);
				}

				if (ret[l] != '9') {
					ret[l] = '9';
					k--;
				}

				if (ret[r] != '9') {
					ret[r] = '9';
					k--;
				}
			}

			l++;
			r--;
		}

		System.out.println(print(ret));

	}

	static Set<Integer> getDiffIndexSet(String s) {
		int l = 0;
		int r = s.length() - 1;

		Set<Integer> diffSet = new HashSet<Integer>();

		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				diffSet.add(l);
			}

			l++;
			r--;
		}

		return diffSet;
	}

	static String print(char[] ret) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ret.length; i++) {
			builder.append(ret[i]);
		}

		return builder.toString();
	}

	static class Ret {
		char [] r;
		int k;

		Ret(char[] r, int k) {
			this.r = r;
			this.k = k;
		}
	}
}
