package class1.week8;

// 2번 문제용
////////////////////////////////////////////////////////////////
//학번 : 60201976
//이름: 장채은
////////////////////////////////////////////////////////////////
public class MazeWall {

	int[][] maze;
	int[][] cost;
	int callCount;

	public MazeWall(int[][] input) {
		maze = input;

	}

	public void resetCount() {
		callCount = 0;
	}

	public int getCount() {
		return callCount;
	}

	public int minCost(int i, int j) { // return min. cost from (0,0) to (i,j)
		callCount++;
		if(i==0&&j==0) {
			return maze[i][j];
		}else if(i==0) {
			return maze[i][j] + minCost(i, j-1);
		}else if(j==0)	{
			return maze[i][j] + minCost(i-1, j);
		}else if(maze[i-1][j] == 999){
			return maze[i][j]+ minCost(i, j-1);
		}else if(maze[i][j-1] == 999){
			return maze[i][j]+ minCost(i-1, j);
		}else{
			return maze[i][j] + Math.min(minCost(i, j-1), minCost(i-1, j));
		}
	}

	private int minCostAll(int i, int j) {
		callCount++;

		return 0;

	}

	public void showCost() {
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++)
				System.out.printf("%5d", cost[i][j]);
			System.out.println();
		}
	}

	public String pathToString(int i, int j) {
		String ret = "(" + i + "," + j + ") ";
		return "";

	}

	public static void main(String[] args) {
		int[][] input = { { 3, 2, 5, 1, 4, 3, 6 }, { 2, 999, 999, 5, 999, 999, 1 }, { 5, 999, 9, 6, 3, 5, 4 },
				{ 1, 999, 3, 8, 6, 1, 7 }, { 7, 999, 8, 999, 9, 2, 2 }, { 8, 6, 2, 999, 6, 9, 5 },
				{ 2, 1, 7, 2, 4, 3, 1 }, };

		MazeWall pf = new MazeWall(input);

		System.out.println("2-1) MinimumCost = " + pf.minCost(input.length - 1, input.length - 1));
		System.out.println("Recursive Call Count = " + pf.getCount());
		pf.resetCount();
		System.out.println("2-2) Cost Matrix");
		pf.minCostAll(input.length - 1, input.length - 1);
		pf.showCost();
		System.out.println("Recursive Call Count with Cost Matrix = " + pf.getCount());
		System.out.println("2-3) Minimum Cost Path");
		System.out.println(pf.pathToString(input.length - 1, input.length - 1));

	}
// 2-4)시간복잡도 답안
//  1)의 경우
//	O(n)이다.
//  2)의 경우
//

}
