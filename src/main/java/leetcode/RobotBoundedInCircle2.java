package leetcode;

public class RobotBoundedInCircle2 {
	public boolean isRobotBounded(String instructions) {
		// 동:0 남:1 서:2 북:3
		int y = 0;
		int x = 0;
		int dir = 3;

		for (int i = 0; i < instructions.length(); i++) {
			char ch = instructions.charAt(i);

			if (ch == 'G') {
				if (dir == 0) {
					x++;
				} else if (dir == 1) {
					y--;
				} else if (dir == 2) {
					x--;
				} else if (dir == 3) {
					y++;
				}
			} else if (ch == 'L') {
				dir = (dir + 3) % 4;
			} else if (ch == 'R') {
				dir = (dir + 1) % 4;
			}
		}

		if (x == 0 && y == 0) {
			return true;
		} else if ((x != 0 || y != 0) && dir != 3) {
			return true;
		}

		return false;
	}

//	public boolean isRobotBounded(String instructions) {
//		// clockwise,
//		int pos = 0;
//		int y = 0;
//		int x = 0;
//
//		for (int i = 0; i < instructions.length(); i++) {
//			char ins = instructions.charAt(i);
//
//			if (ins == 'G') {
//				if (pos == 0) {
//					y--;
//				} else if (pos == 1) {
//					x++;
//				} else if (pos == 2) {
//					y++;
//				} else if (pos == 3) {
//					x--;
//				}
//			} else if (ins == 'L') {
//				pos = (pos + 3) % 4;
//			} else if (ins == 'R') {
//				pos = (pos + 1) % 4;
//			}
//		}
//
//		if (x == 0 && y == 0) {
//			return true;
//		} else if ((x != 0 || y != 0) && pos == 0) {
//			return false;
//		}
//
//		return true;
//	}

	public static void main(String[] args) {
		RobotBoundedInCircle2 robotBoundedInCircle2 = new RobotBoundedInCircle2();
		System.out.println(robotBoundedInCircle2.isRobotBounded("GL"));
	}
}
