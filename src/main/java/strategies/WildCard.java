package strategies;

public class WildCard {

	private static int count = 0;

	// 와일드카드 패턴 w가 문자열 s에 대응되는 여부를 반환한다.
	public boolean match(char [] w, char [] s) {
		System.out.println(++count);

		// 1. w[pos] 와 s[pos] 를 맞춰나간다.
		int pos = 0;

		// 1-1. 해당 position 이 ? 이거나, 같은 문자라면 position 증가시킨다.
		while (pos < s.length && pos < w.length && (w[pos] == '?' || w[pos] == s[pos])) {
			pos++;
		}

		// 더 이상 대응할 수 없으면 왜 while 문이 끝났는지 확인한다.
		// 2. 패턴 끝에 도달해서 끝난 경우 : 문자열도 끝났어야 대응됨
		if (pos == w.length) {
			return pos == s.length;
		}

		// 3. * 를 만나서 끝난 경우 : * 에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
		if (w[pos] == '*') {
			// 3-1. 현재 position 에서 skip 위치만큼 건너뛰면서 확인한다.
			// 3-2. skip 의 최대위치는 s의 길이만큼이다.
			for (int skip = 0; pos+skip <= s.length ; skip++) {
				if (match(String.valueOf(w).substring(pos + 1).toCharArray(), String.valueOf(s).substring(pos + skip).toCharArray())) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {
		String a = "****a";
		String b = "aaaaaab";

		System.out.println(a.length());
		System.out.println(b.length());

		WildCard wildCard = new WildCard();
		System.out.println(wildCard.match(a.toCharArray(), b.toCharArray()));
	}
}
