package leetcode;

public class SquirrelSimulation {
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
		int totalDistance = 0;
		int maxDiff = 0;
		int nutPos = 0;

		for (int i = 0 ; i < nuts.length ; i++) {
			int [] nut = nuts[i];

			int distanceFromTree = (Math.abs(tree[0] - nut[0]) + Math.abs(tree[1] - nut[1]));
			int distanceFromSquirrel = (Math.abs(squirrel[0] - nut[0]) + Math.abs(squirrel[1] - nut[1]));
			totalDistance += (distanceFromTree * 2);

			if (maxDiff <= distanceFromTree - distanceFromSquirrel) {
				nutPos = i;
				maxDiff = distanceFromTree - distanceFromSquirrel;
			}
		}

		totalDistance -= (Math.abs(tree[0] - nuts[nutPos][0]) + Math.abs(tree[1] - nuts[nutPos][1]));
		totalDistance += (Math.abs(squirrel[0] - nuts[nutPos][0]) + Math.abs(squirrel[1] - nuts[nutPos][1]));

		return totalDistance;
	}

	public static void main(String[] args) {
		int [] tree = {3,2};
		int [] squirrel = {0,1};
		int [][] nuts = {{2,0},{4,1},{0,4},{1,3},{1,0},{3,4},{3,0},{2,3},{0,2},{0,0},{2,2},{4,2},{3,3},{4,4},{4,0},{4,3},{3,1},{2,1},{1,4},{2,4}};

		SquirrelSimulation squirrelSimulation = new SquirrelSimulation();
		System.out.println(squirrelSimulation.minDistance(5, 5, tree, squirrel, nuts));

	}
}
