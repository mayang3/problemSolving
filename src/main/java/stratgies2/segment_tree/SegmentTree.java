package stratgies2.segment_tree;

public class SegmentTree {

	// 배열의 구간 최소 쿼리를 해결하기 위한 구간 트리의 구현
	// Range Minimum Query
	static class RMQ {
		// 배열의 길이
		int n;
		// 각 구간의 최소치
		// 트리구조인데, 비교적 꽉 찬 트리구조이므로 배열로 표시하여 메모리를 절약해준다.
		// 루트 노드를 배열의 1번 ㄴ원소로, 노드 i의 왼쪽 자손과 오른쪽 자손을 각각 2*i 와 2*i+1 번 원소로 표현하도록 하자.
		int [] rangeMin;

		RMQ(int [] array) {
			n = array.length;
			rangeMin = new int[n*4];
			init(array, 0, n-1, 1);
		}

		/**
		 * node 가 array[left~right] 배열을 표현할 때,
		 *
		 * node 를 root 로 하는 subtree 를 초기화 하고, 이 구간의 최소치를 반환한다.
		 *
		 * 그럼 초기화 과정에 걸리는 시간복잡도는?
		 *
		 * 각 노드마다 걸리는 시간은 O(1) 이니, 초기화 과정에는 노드와 같은 수가 걸린다.
		 *
		 * 앞에서 배열의 길이는 O(n) 이라고 했으니, 노드의 수도 O(n) 이고,
		 *
		 * 따라서 초기화 과정의 시간복잡도는 O(n) 이 된다.
		 *
		 * "초기화 과정은 dnc 방식으로 넣는다."
		 *
		 * @param array
		 * @param left
		 * @param right
		 * @param node
		 * @return
		 */
		int init(int [] array, int left, int right, int node) {
			// left 와 right 가 같은 지점을 가리킬때, 해당 인덱스를 그대로 넣는다.
			if (left == right) {
				return rangeMin[node] = array[left];
			}

			int mid = (left + right) / 2;
			int leftMin = init(array, left, mid, node * 2);
			int rightMin = init(array, mid + 1, right, node * 2 + 1);

			return rangeMin[node] = Math.min(leftMin, rightMin);
		}

		/**
		 * node 가 표현하는 범위 array[nodeLeft ~ nodeRight] 가 주어질 때,
		 *
		 * 이 범위와 array[left ~ right] 의 교집합의 최소치를 구한다.
		 *
		 * 이때, 트리의 바닥까지의 탐색은 최대 2번만 이루어지기 때문에
		 * (left 부분 and right 부분에서 최대 1번씩만 발생할 수 있다.)
		 *
		 * 전체 시간복잡도는 여전히 O(lgn) 이다.
		 * O(2lgn) = O(lgn)
		 *
		 * (e.g)
		 * 2^3=8, lg8 = 3
		 *
		 * @param left
		 * @param right
		 * @param node 현재 노드의 시작위치. 구간트리에서의 구간의 시작점을 나타낸다.
		 * @param nodeLeft
		 * @param nodeRight
		 * @return
		 */
		private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
			// 두 구간이 겹치지 않으면 아주 큰 값을 반환한다 -> 무시됨
			if (right < nodeLeft || nodeRight < left) {
				return Integer.MAX_VALUE;
			}

			// node 가 표현하는 범위가 array[left~right] 에 완전히 포함되는 경우
			if (left <= nodeLeft && nodeRight <= right) {
				return rangeMin[node];
			}

			// 양쪽 구간을 나눠서 푼 뒤 결과를 합친다.
			int mid = (nodeLeft + nodeRight) / 2;

			return Math.min(query(left, right, node * 2, nodeLeft, mid), query(left, right, node * 2 + 1, mid + 1, right));
		}

		/**
		 * query() 를 외부에서 호출하기 위한 인터페이스
		 *
		 * @param left
		 * @param right
		 * @return
		 */
		int query(int left, int right) {
			return query(left, right, 1, 0, n-1);
		}

		/**
		 * array[index] = newValue 로 바뀌었을 때 node 를 root 로 하는
		 *
		 * 구간 트리를 갱신하고 노드가 표현하는 구간의 최소치를 반환한다.
		 *
		 * @param index
		 * @param newValue
		 * @param node
		 * @param nodeLeft
		 * @param nodeRight
		 * @return
		 */
		private int update(int index, int newValue, int node, int nodeLeft, int nodeRight) {
			// index 가 노드가 표현하는 구간과 상관없는 경우엔 무시한다.
			// 이 때, 맨 아래 return 구간에서의 업데이트를 위해서,
			// 현재 node 구간의 최소값은 리턴해 주어야 한다.
			if (index < nodeLeft || nodeRight < index) {
				return rangeMin[node];
			}

			// 트리의 leaf 까지 내려온 경우 값을 갱신한다.
			if (nodeLeft == nodeRight) {
				return rangeMin[node] = newValue;
			}

			int mid = (nodeLeft + nodeRight) / 2;

			return rangeMin[node] =
				Math.min(
					update(index, newValue, node*2, nodeLeft, mid),
					update(index, newValue, node*2+1, mid+1, nodeRight));
		}

		int update(int index, int newValue) {
			return this.update(index, newValue, 1, 0, n-1);
		}
	}
}
