package leetcode;

import java.util.*;

public class RobotBoundedInCircle {
	public boolean isRobotBounded(String instructions) {
		// clockwise,
		int pos = 0;
		int y = 0;
		int x = 0;

		for (int i = 0; i < instructions.length(); i++) {
			char ins = instructions.charAt(i);

			if (ins == 'G') {
				if (pos == 0) {
					y--;
				} else if (pos == 1) {
					x++;
				} else if (pos == 2) {
					y++;
				} else if (pos == 3) {
					x--;
				}
			} else if (ins == 'L') {
				pos = (pos + 3) % 4;
			} else if (ins == 'R') {
				pos = (pos + 1) % 4;
			}
		}

		if (x == 0 && y == 0) {
			return true;
		} else if ((x != 0 || y != 0) && pos == 0) {
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
