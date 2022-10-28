package class2.week2;

// 저장 된 배열을 더하면서 길 찾아나가는데, 합이 최소여야 함.
public class PathFind {
	int [][] maze, memo;
	int count, mSize;
	
	public PathFind(int[][] input) {
		maze = input;
		count = 0;
		mSize = maze.length;
		memo = new int[mSize][mSize];
	}
	
	// bottom - up 방식, Recursion
	public int maxPathRec(int row, int col) {
		count++;
		if(row == 0 && col == 0)
			return maze[row][col];
		else if(row == 0) 
			return maze[row][col] + maxPathRec(row, col-1);
		else if(col == 0)
			return maze[row][col] + maxPathRec(row-1, col);
		else 
			return maze[row][col]
					+ Math.max(maxPathRec(row, col-1), maxPathRec(row-1, col));
	}
	
	// top - down 방식 
	public int maxPathIter(int row, int col) {
		// 숙제
		for(int i = 0 ; i<=row; i++) {
			count++;
			if(i == 0) memo[i][0] = maze[i][0];
			else memo[i][0] = maze[i][0] + maze[i-1][0]; // 세로가 0일 때, 가로 줄 쭉 더하기
		}
		for(int i = 0 ; i<=col; i++) {
			count++;
			if(i == 0) memo[0][i] = maze[0][i];
			else memo[0][i] = maze[0][i] + maze[0][i-1]; // 가로가 0일 때, 세로 줄 쭉 더하기
		}
		for(int i = 1; i<=row; i++) {
			for(int j = 1; j<=col; j++) {
				count++;
				memo[i][j] = maze[i][j] + Math.max(memo[i-1][j], memo[i][j-1]); // maze + 가로 세로 더한 memo array 중에서 큰 값 더하기
			}
		}
		return memo[row][col];
	}
	
	public void reset() {
		count = 0;
		for(int i = 0; i<mSize; i++) {
			for(int j = 0; j<mSize; j++) {
				memo[i][j] = 0;
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	
	// recursion이므로 bottom - up 방식 사용
	public int maxpathDP(int row, int col) {
		count++;
		if(memo[row][col] != 0) { // 값을 가지고 있다면
			return memo[row][col];
		}
		else {
			if(row == 0 && col == 0) 
				memo[row][col] = maze[row][col];
			else if(row == 0) // row는 변함 없고 col을 하나 줄여서 return -> bottom - up 방식 사용
				memo[row][col] = maze[row][col] + maxpathDP(row, col-1);
			else if(col == 0) // col은 변함 없고 row를 하나 줄여서 return -> bottom - up 방식 사용
				memo[row][col] = maze[row][col] + maxpathDP(row-1, col);
			else
				memo[row][col] = maze[row][col]
						+ Math.max(maxpathDP(row, col-1), maxpathDP(row-1, col));
			return memo[row][col];
		}
		
	}
	
	public static void main(String[] args) {
		int nDim = 4;
		int[][] matrix = {{6, 7, 12, 5},
						  {5, 3, 11, 18},
				          {1, 17, 3, 3},
				          {8, 10, 14, 9}};
		PathFind pf = new PathFind(matrix);
		System.out.println("Recursion : " + pf.maxPathRec(nDim-1, nDim-1) + "(" + pf.getCount() + ")");
		pf.reset();
		System.out.println("Iteration : " + pf.maxPathIter(nDim-1, nDim-1) + "(" + pf.getCount() + ")");
		pf.reset();
		System.out.println("Dynamic Programming : " + pf.maxpathDP(nDim-1, nDim-1) + "(" + pf.getCount() + ")");
	}
}
