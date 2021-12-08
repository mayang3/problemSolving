package leetcode;

import java.util.Arrays;

public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
	public int uniqueLetterString(String S) {
		// index[C][0] : 알파벳 C 의 이전 위치를 나타낸다.
		// index[C][1] : 알파벳 C 의 현재 위치를 나타낸다.
		// 즉 index[C][X] : 는 알파벳 C 의 마지막 두 위치를 나타낸다.
		int[][] index = new int[26][2];

		for (int i = 0; i < 26; ++i) {
			Arrays.fill(index[i], -1);
		}

		int res = 0, N = S.length(), mod = (int)Math.pow(10, 9) + 7;

		for (int i = 0; i < N; ++i) {
			int c = S.charAt(i) - 'A';
			// 실제 조합할 수 있는 갯수를 계산한다.
			// 여기서 중요한 개념이 나오는데, 만약 XAXAXXAXXA 가 있다고 해보자.
			// 여기서 두번째에 있는 A 를 유니크 하게 만들려면,
			// XA(XAXX)AXXA 와 같이 양쪽 A 의 사이에 A 를 고립시켜야 한다.
			// 그렇다면 그러한 경우의 수는 얼마나 있을까?
			// 왼쪽 파트는 AXA 이므로 A(XA, AX(A 가 있을 수 있고,
			// 오른쪽 파트는 AXXA 이므로, A)XXA, AX)XA, AXX)A 가 있을 수 있다.
			// 그래서 두번째 A 를 유니크하게 만들 수 있는 총 개수는 2*3=6 개의 조합이 가능하다.
			// 이 원리를 가지고 각 character 를 순회하면서 앞 뒤의 캐릭터사이에 괄호를 넣을 수 있는 경우의 수를 계산한다.
			res = (res + (i - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
			index[c] = new int[] {index[c][1], i};
		}

		// 일단 위의 로직을 다 수행하고 나면, 맨 마지막 문자를 기준으로 한 경우의 수는 계산되지 않기 때문에,
		// 전체 길이를 대상으로 다시 한번 계산해준다.
		for (int c = 0; c < 26; ++c) {
			res = (res + (N - index[c][1]) * (index[c][1] - index[c][0]) % mod) % mod;
		}

		return res;
	}

	public static void main(String[] args) {
		CountUniqueCharactersOfAllSubstringsOfAGivenString cuc = new CountUniqueCharactersOfAllSubstringsOfAGivenString();
		System.out.println(cuc.uniqueLetterString("ABAB"));
	}
}
