package strategies.priorityQueue;

import java.util.List;

/**
 * @author baejunbeom
 */
public class Heap {

	// 정수 원소를 갖는 최대힙에 새 원소 삽입하기
	public void push(List<Integer> heap, int newValue) {
		// 힙의 맨 끝에 newValue 를 삽입한다.
		heap.add(newValue);

		// 현재 newValue 의 위치
		int idx = heap.size() - 1;

		// 루트에 도달하거나 newValue 이상의 원소를 가진 parent 를 만날 때까지
		while (idx > 0 && heap.get((idx - 1) / 2) < heap.get(idx)) {
			swap(heap, idx, (idx - 1) / 2);
		}
	}

	private void swap(List<Integer> heap, int to, int from) {
		Integer removed = heap.remove(to);

		heap.add(heap.get(from));
		heap.add(from, removed);
	}

	//정수를 담는 최대 heap 에서 heap[0] 을 제거한다.
	public void pop(List<Integer> heap) {
		// 힙의 맨 끝에서 값을 가져와 루트에 덮어씌운다.
		Integer removed = heap.remove(heap.size() - 1);
		heap.add(0, removed);

		int here = 0;

		while (true) {
			int left = here * 2 + 1;
			int right = here * 2 + 2;

			// leaf node 에 도달한 경우
			if (left >= heap.size()) {
				break;
			}

			// heap[here] 가 내려갈 위치를 찾는다.
			int next = here;

			// left child node 의 값이 더 큰 경우
			if (heap.get(next) < heap.get(left)) {
				next = left;
			}
			// right child node 의 값이 더 큰 경우
			if (right < heap.size() && heap.get(next) < heap.get(right)) {
				next = right;
			}
			// 힙의 맨 끝의 값이 제일 큰 경우
			if (next == here) {
				break;
			}

			swap(heap, here, next);

			here = next;
		}
	}
}
