package stratgies2.trie;

import java.util.Scanner;

/**
 * 단어의 첫 몇글자를 가지고 나머지를 찾는 연산은 문자열의 접두사로 사전을 검색하는 작업이기 때문에,
 *
 * 트라이를 사용하기에 아주 적합하다.
 *
 * TODO 전체 타이핑 안함..
 *
 *
 */
public class SOLONG {
	// Alphabet size
	static final int ALPHABET_SIZE = 26;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int c = scanner.nextInt();

		for (int i = 0; i < c; i++) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();

			for (int j = 0; j < n; j++) {
				String s = scanner.next();
				int nn = scanner.nextInt();

				System.out.println(s + " " + nn);
			}

			for (int j = 0; j < m; j++) {
				String s = scanner.next();

				System.out.println(s);
			}
		}
	}

	static class TrieNode {
		TrieNode [] children = new TrieNode[ALPHABET_SIZE];
		// 이 노드에서 종료하는 문자열의 번호. 없으면 -1
		int terminal = -1;
		// 이 노드를 루트로 하는 트라이에 가장 먼저 추가된 단어의 번호. -1로 초기화
		int first = -1;
	}

	static TrieNode root;

	// 이 노드를 루트로 하는 트라이에 번호 id 인 문자열 key 를 추가한다.
	static void insert(String key, int id) {
		// 문자열이 끝났다면 terminal 만 바꾸고 종료
		TrieNode trieNode = root;

		for (int level = 0 ; level < key.length() ; level++) {
			// 현재 노드의 first 를 우선 갱신한다.
			if (trieNode.first == -1) {
				trieNode.first = id;
			}

			// 자식 노드가 없다면 생성
			if (trieNode.children[level] == null) {
				trieNode.children[level] = new TrieNode();
			}

			// recursive 호출
			trieNode = trieNode.children[level];
		}

		// 문자열이 끝났다면 terminal 을 변경
		trieNode.terminal = id;
	}

	// 이 노드까지 타이핑해 왔을 때, 번호 id인 key 를 타이핑 하기 위해,
	// 최소 몇 번의 키를 더 눌러야 하나?
	static int type(String key, int id) {
		return 0;
	}
}
