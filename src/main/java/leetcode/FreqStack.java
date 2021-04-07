package leetcode;

import java.util.*;

public class FreqStack {
	TreeMap<Integer, PriorityQueue<Node>> treeMap = new TreeMap<>();
	Map<Integer, Integer> freqMap = new HashMap<>();
	int last = 0;

	public FreqStack() {
	}

	public void push(int val) {
		if (freqMap.containsKey(val)) {
			int freq = freqMap.get(val);
			freqMap.put(val, freq+1);
			treeMap.computeIfAbsent(freq+1, t -> makePriorityQueue()).add(new Node(val, this.last++));
		} else {
			freqMap.merge(val, 1, Integer::sum);
			treeMap.computeIfAbsent(1, t -> makePriorityQueue()).add(new Node(val, this.last++));
		}
	}

	public int pop() {
		for (int key : treeMap.descendingKeySet()) {
			if (treeMap.get(key).size() > 0) {
				int val = treeMap.get(key).poll().val;
				freqMap.merge(val, -1, Integer::sum);
				if (treeMap.get(key).size() == 0) {
					// 이 다음 loop 는 실행되지 않기 때문에 제거해도 안전하다.
					treeMap.remove(key);
				}
				return val;
			}
		}

		return 0;
	}

	static class Node {
		int val;
		int index;

		public Node(int val, int index) {
			this.val = val;
			this.index = index;
		}
	}

	static PriorityQueue<Node> makePriorityQueue() {
		return new PriorityQueue<>((o1, o2) -> o2.index - o1.index);
	}

	public static void main(String[] args) {
		FreqStack freqStack = new FreqStack();
		freqStack.push(1);
		freqStack.push(1);
		freqStack.push(1);
		freqStack.push(2);
		System.out.println(freqStack.pop());
		System.out.println(freqStack.pop());
		freqStack.push(2);
		freqStack.push(2);
		freqStack.push(1);
		System.out.println(freqStack.pop());
		System.out.println(freqStack.pop());
		System.out.println(freqStack.pop());

	}
}
