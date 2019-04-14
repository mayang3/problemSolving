package strategies;

import java.util.Arrays;
import java.util.Iterator;

// 쿼드 트리 뒤집기
public class QuadTree {
	char [][] decompressed = new char[16][16];

	/**
	 * 쿼드트리 압축 해제만 담당하는 메소드
	 * @param iter
	 * @param y
	 * @param x
	 * @param size
	 */
	void decompress(Iterator<Character> iter, int y, int x, int size) {
		Character head = iter.next();

		// base case : 첫글자가 b 또는 w 인 경우
		// 해당 사이즈 만큼의 배열을 전부 b 또는 w로 색칠한다.
		if (head == 'b' || head == 'w') {
			for (int dy = 0 ; dy < size ; dy++) {
				for (int dx = 0 ; dx < size ; dx++) {
					decompressed[y+dy][x+dx] = head;
				}
			}
		} else {
			// 네 부분을 각각 순서대로 압축 해제한다.
			// 시작점의 x,y 는 각 0,0 이다.
			int half = size / 2;

			// 왼쪽 위 귀퉁이
			decompress(iter, y, x, half);
			// 왼쪽 아래 귀퉁이
			decompress(iter, y, x + half, half);
			// 오른쪽 위 귀퉁이
			decompress(iter, y+half, x, half);
			// 오른쪽 아래 귀퉁이
			decompress(iter, y+half, x+half, half);

		}
	}

	/**
	 * 실제로 쿼드트리 압축된 부분을 뒤집는 부분
	 * @param iter
	 * @return
	 */
	String reverse(Iterator<Character> iter) {
		Character head = iter.next();

		if (head == 'b' || head == 'w') {
			return String.valueOf(head);
		}

		String upperLeft = reverse(iter);
		String upperRight = reverse(iter);
		String lowerLeft = reverse(iter);
		String lowerRight = reverse(iter);

		return new String("x") + lowerLeft + lowerRight + upperLeft + upperRight;
	}
		

	public static void main(String[] args) {
		QuadTreeIter xbwwb = new QuadTreeIter("xxwwwbxwxwbbbwwxxxwwbbbwwwwbb");

		QuadTree quadTree = new QuadTree();
		quadTree.decompress(xbwwb, 0, 0, 16);

		System.out.println(Arrays.deepToString(quadTree.decompressed));
		System.out.println(quadTree.reverse(new QuadTreeIter("xbwwb")));
	}

	static class QuadTreeIter implements Iterator<Character> {

		private char [] chars;
		private int current = 0;

		QuadTreeIter(String string) {
			this.chars = string.toCharArray();
		}

		@Override
		public boolean hasNext() {
			return chars != null && chars[current] != '\u0000';
		}

		@Override
		public Character next() {
			return chars[current++];
		}
	}
}
