package leetcode;

import java.util.*;

public class RobotBoundedInCircle {
	public boolean isRobotBounded(String instructions) {

		Queue<Character> q = new LinkedList<>();

		for (char c : instructions.toCharArray()) {
			q.add(c);
		}
		// north : 00
		// west : 01
		// south : 10
		// east : 11
		int loc = 0;
		int x = 0;
		int y = 0;

		while (q.isEmpty() == false) {
			char ch = q.poll();

			if ('G' == ch) {
				if (loc == 0) {
					y++;
				} else if (loc == 1) {
					x--;
				} else if (loc == 2) {
					y--;
				} else if (loc == 3) {
					x++;
				}

			} else if ('L' == ch) {
				loc = loc == 3 ? 0 : loc+1;
			} else if ('R' == ch) {
				loc = loc == 0 ? 3 : loc-1;
			}
		}

		if (x == 0 && y == 0) {
			return true;
		} else if ((x != 0 || y != 0) && loc == 0) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		RobotBoundedInCircle circle = new RobotBoundedInCircle();
		boolean res = circle.isRobotBounded("GL");

		System.out.println(res);
	}
}
