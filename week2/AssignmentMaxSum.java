package week2;

public class AssignmentMaxSum {
	// 오른쪽과 아래 방향으로만 이동하지 않고, 대각성 이동이 허용된 경우
	int count = 0;
	int [][] maze;
	
	public AssignmentMaxSum(int[][] in) {
		maze = in;
	}
	
	public int findMax(int i, int j) {
		count++;
		if(i==0 && j==0)
			return maze[i][j];
		if(i==0)
			return maze[i][j]+findMax(i, j-1);
		if(j==0)
			return maze[i][j]+findMax(i-1, j);
		// 5, 5를 비교한다면 4, 5와 5, 4 중에서 큰 값을 고르고 그것보다 4, 4의 값이 크다면 대각선으로 이동
		if(Math.max(findMax(i, j-1), findMax(i-1, j))<findMax(i-1, j-1)) {
			return maze[i][j]+findMax(i-1, j-1);
		}
		return maze[i][j]+Math.max(findMax(i, j-1), findMax(i-1, j));
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		int [][] maze ={
			{1, 2, -1, 5, 8, 4},
			{4, 1, 8, 4, 2, 3},
			{-1, 1, 9, 3, 8, 2},
			{1, 5, 3, 10, 7, 3},
			{4, 7, -1, 9, 2, 8},
			{2, 4, 6, 3, 1, 4}};
		MaxSum me = new MaxSum(maze);
		
		System.out.println("MaxSum = " + me.findMax(maze.length-1, maze.length-1) + " Count = " + me.getCount());
	}
	


}
