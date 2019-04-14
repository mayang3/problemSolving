package hackerrank.compete;

import java.util.Scanner;

/**
 */
public class ServerRoomSafety {



	static String checkAll(int n, int[] height, int[] position) {

		boolean left = checkLeft(height, position);
		boolean right = checkRight(height, position);


		if (left && right) {
			return "BOTH";
		} else if (left && !right) {
			return "LEFT";
		} else if (!left && right) {
			return "RIGHT";
		}

		return "NONE";
	}

	static boolean checkLeft(boolean covered, int start, int [] height, int [] position) {
		// 끝까지 다 검사했으면 true
		if (start >= position.length) {
			return true;
		}

		int next = start+1;

		for (int i=start+1 ; i<position.length ; i++) {
			// 1. 내가 덮여있지 않은 경우
			// 1-1. 내가 덮여있지 않고 다음 기둥도 덮지 못한 경우
			if (!covered && position[start] + height[start] < position[i]) {
				return false;
			}

			// 1-2. 내가 덮여있지 않지만 다음 기둥은 덮은 경우 or 내가 덮여 있는 경우, 다음 start 값을 구한다
			if (position[start] + height[start] < position[i]) {
				next = i-1;
				break;
			}
		}

		return checkLeft(true, next, height, position);
	}



	static boolean checkRight(int[] height, int[] position) {
		for (int i=position.length-1 ; i>=0 ; i--) {
			int j=i-1;

			boolean covered = false;

			while (j>=0) {
				if (position[i] - height[i] <= position[j]) {
					covered = true;
					j--;
					continue;
				}

				if (covered == false) {
					return false;
				}

				j++;
				break;
			}

			i=j+1;
		}

		return true;
	}

	static boolean checkLeft(int [] height, int[] position) {

		boolean covered = false;

		for (int i=0 ; i<position.length ; i++) {
			int j=i+1;

			while (j<position.length) {

				if (position[i] + height[i] >= position[j]) {
					covered = true;
					j++;
					continue;
				}

				if (covered == false) {
					return false;
				} else {
					j++;
				}

				j--;
				break;
			}

			i=j-1;
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] position = new int[n];
		for(int position_i = 0; position_i < n; position_i++){
			position[position_i] = in.nextInt();
		}
		int[] height = new int[n];
		for(int height_i = 0; height_i < n; height_i++){
			height[height_i] = in.nextInt();
		}
		String ret = checkAll(n, height, position);
		System.out.println(ret);
		in.close();
	}
}
