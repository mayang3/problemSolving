package leetcode;

/*

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

*/

/**
 * @author baejunbeom
 */
public class NumberOfIsland {

	public int numIslands(char[][] grid) {
		int count = 0;

		for (int x = 0 ; x < grid.length ; x++) {
			for (int y = 0, z=y+1 ; y < grid[x].length ; y++) {

				if (z <= grid[x].length && grid[x][z] == 1) {
					continue;
				}

				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		char [][] grids = {
			{'1','1','1','1','0'},
			{'1','1','0','1','0'},
			{'1','1','0','0','0'},
			{'0','0','0','0','0'}
		};

		NumberOfIsland numberOfIsland = new NumberOfIsland();
		numberOfIsland.numIslands(grids);
	}
}
