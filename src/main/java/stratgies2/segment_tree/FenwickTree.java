package stratgies2.segment_tree;

/**
 * 펜윅 트리 (Binary Indexed Tree) 의 구현
 *
 * 가상의 배열 A[] 의 부분 합을 빠르게 구현할 수 있도록 한다.
 *
 * 초기화시에는 A[] 의 원소가 전부 0 이라고 생각한다.
 */
public class FenwickTree {

	public static void main(String[] args) {
		int [] input = {1,2,3,4,5};

		FT ft = new FT(input, 5);

		System.out.println(ft.sum(3));
	}

	static class FT {
		int [] tree;

		FT(int [] input, int n) {
			tree = new int[n+1];

			for (int i = 0; i < n; i++) {
				add(i, input[i]);
			}
		}

		// A[0..pos] 의 부분 합을 구한다.
		int sum(int pos) {
			// 인덱스가 1부터 시작한다고 생각하자.
			pos++;

			int ret = 0;

			while (pos > 0) {
				ret += tree[pos];
				pos &= (pos-1); // 다음 구간을 찾기 위해 최종 비트를 지운다.
			}

			return ret;
		}

		// A[pos] 에 val 을 더한다.
		void add(int pos, int val) {
			pos++;

			while (pos < tree.length) {
				tree[pos] += val;
				pos += (pos & -pos); //
			}
		}
	}
}
