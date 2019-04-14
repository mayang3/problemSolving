package strategies.tree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author baejunbeom
 */
public class Nerd2 {

	// 현재 다른 점에 지배당하지 않는 점들의 목록을 저장한다.
	// coords[x] = y
	TreeMap<Integer, Integer> coords = new TreeMap<>();

	// 새로운 점 (x,y) 가 기존의 다른 점들에 지배당하는지 확인한다.
	boolean isDominated(int x, int y) {
		// x 보다 오른쪽에 있는 점들 중 가장 왼쪽에 있는 점을 찾는다.
		Map.Entry<Integer, Integer> entry = coords.ceilingEntry(x);

		// 그런 점이 없으면 (x,y)는 지배당하지 않는다.
		if (entry == null) {
			return false;
		}

		// 이점은 x보다 오른쪽에 있는 점 중 가장 위에 있는 점이므로,
		// (x,y)가 어느 점에 지배되려면 이 점도 지배되어야 한다.
		return y < entry.getValue();
	}

	// 새로운 점 (x,y) 에 지배당하는 점들을 트리에서 지운다.
	void removeDominated(int x, int y) {
		Map.Entry<Integer, Integer> entry = coords.floorEntry(x);

		// (x,y) 보다 왼쪽에 있는 점이 없다!
		if (entry == null) {
			return;
		}

		// loop 불변식 : entry 는 (x,y)의 바로 왼쪽에 있는 점
		while(true) {
			// (x,y) 바로 왼쪽에 오는 점을 찾는다.
			// entry 가 표시하는 점이 (x,y) 에 지배되지 않는다면 곧장 종료
			if (entry.getValue() > y) {
				break;
			}

			// 이전 점이 더 없으므로 entry 만 지우고 종료한다.
			if (entry.equals(coords.firstEntry())) {
				coords.remove(entry.getKey());
				break;
			} else {
				// entry 를 이전 점으로 변경해두고 현재 점의 entry 를 삭제한다.
				Map.Entry<Integer, Integer> lowerEntry = coords.lowerEntry(entry.getKey());
				coords.remove(entry.getKey());
				entry = lowerEntry;
			}
		}
	}

	// main 계산 함수이다.
	// 새로운 점 (x,y) 가 추가되었을 때, coords 를 갱신하고,
	// 다른 점에 지배당하지 않는 점들의 개수를 반환한다.
	public int registered(int x, int y) {
		// (x, y) 가 이미 지배당하는 경우에는 그냥 (x, y) 를 버린다.
		if (isDominated(x, y)) {
			return coords.size();
		}

		// 기존에 있던 점 중 (x,y) 에 지배당하는 점들을 지운다.
		removeDominated(x, y);
		coords.put(x, y);
		return coords.size();
	}

	public static void main(String[] args) {
//		Map<Integer, Integer> input1 = new LinkedHashMap<>();
//		input1.put(72, 50);
//		input1.put(57, 67);
//		input1.put(74, 55);
//		input1.put(64, 60);

		Map<Integer, Integer> input2 = new LinkedHashMap<>();
		input2.put(1, 5);
		input2.put(2, 4);
		input2.put(3, 3);
		input2.put(4, 2);
		input2.put(5, 1);

		Nerd2 nerd2 = new Nerd2();

		int sum = 0;

		for (Map.Entry<Integer, Integer> entry : input2.entrySet()) {
			int registered = nerd2.registered(entry.getKey(), entry.getValue());

			sum += registered;

			System.out.println(registered);
		}

		System.out.println(sum);

	}

}
