package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RotatingTheBox {
	public char[][] rotateTheBox(char[][] box) {
		int n = box.length;
		int m = box[0].length;

		Map<Integer, TreeSet<Integer>> map = new HashMap<>();

		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[i].length; j++) {
				if (box[i][j] == '*') {
					map.computeIfAbsent(i, t -> new TreeSet<>()).add(j);
				}
			}
		}

		for (int i = 0; i < box.length; i++) {
			for (int j = box[i].length-1; j >= 0; j--) {
				if (box[i][j] == '#') {
					TreeSet<Integer> treeSet = map.getOrDefault(i, new TreeSet<>());

					Integer ceiling = treeSet.higher(j);

					// 장애물이 없는 경우, 내 뒤에 빈 공간이 없거나 모두 빈 공간이다.
					if (ceiling == null) {
						box[i][j] = '.';
						box[i][box[i].length - 1] = '#';
						treeSet.add(box[i].length-1);
						map.put(i, treeSet);
						continue;
					}

					// 장애물이 있는 경우, 나의 위치를 장애물 바로 앞으로 바꾸고, 새로운 나의 위치를 장애물로 인식하게끔 넣어준다.
					box[i][j] = '.';
					box[i][ceiling - 1] = '#';
					// 옮겨진 나의 위치를 장애물로 인식하게끔 해준다.
					treeSet.add(ceiling - 1);
					map.put(i, treeSet);
				}
			}
		}

		char [][] newBoxes = new char[m][n];

		for (int col = 0; col < m; col++) {
			for (int row = n-1; row >=0 ; row--) {
				newBoxes[col][n-1-row] = box[row][col];
			}
		}

		return newBoxes;
	}

	public static void main(String[] args) {
		char [][] boxes = {{'*'}};

		RotatingTheBox rotatingTheBox = new RotatingTheBox();

		for (char [] b : rotatingTheBox.rotateTheBox(boxes)) {
			System.out.println(Arrays.toString(b));
		}
	}
}
