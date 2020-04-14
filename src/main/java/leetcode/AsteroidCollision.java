package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author neo82
 */
public class AsteroidCollision {
	public int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < asteroids.length; i++) {
			int asteroid = asteroids[i];

			if (asteroid > 0) {
				stack.push(asteroid);
			} else {
				boolean remain = true;

				while (stack.isEmpty() == false) {
					int pop = stack.pop();

					if (pop < 0) {
						remain = false;
						stack.push(pop);
						stack.push(asteroid);
						break;
					} else if (pop + asteroid == 0) { // 충돌해서 둘다 소멸되는 경우
						remain = false;
						break;
					} else if (pop + asteroid > 0) { // 충돌해서 음수가 소멸되는 경우
						remain = false;
						stack.push(pop);
						break;
					}
				}

				if (remain && stack.isEmpty()) {
					stack.push(asteroid);
				}
			}
		}

		int [] ret = new int[stack.size()];

		for (int i = ret.length-1; i >= 0 ; i--) {
			ret[i] = stack.pop();
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] asteroids = {10, 2, -5};

		AsteroidCollision asteroidCollision = new AsteroidCollision();
		int[] res = asteroidCollision.asteroidCollision(asteroids);

		System.out.println(Arrays.toString(res));
	}
}
