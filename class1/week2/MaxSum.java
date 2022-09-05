package class1.week2;
public class MaxSum {
	int count = 0;
	int [][] maze;
	
	public MaxSum(int[][] in) {
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
		return maze[i][j]+Math.max(findMax(i, j-1), findMax(i-1, j));
	}
	
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		int [][] maze ={
			{1, 2, 1, 5, 8, 4},
			{4, 1, 9, 4, 2, 3},
			{8, 5, 4, 3, 8, 2},
			{1, 5, 3, 5, 7, 3},
			{4, 7, 7, 9, 2, 8},
			{2, 4, 6, 3, 1, 4}};
		MaxSum me = new MaxSum(maze);
		
		System.out.println("MaxSum = " + me.findMax(maze.length-1, maze.length-1) + " Count = " + me.getCount());
	}
	


}
