package class2.week3;

// 행렬 곱셈 순서 최소가 되도록
// 시간 복잡도 O(n^3)
public class MatrixChain {
	int nOfMatrix;
	int [] p;  // array of dimension
	int count ;
	int [][] memo;
	
	public MatrixChain(int [] dimension) {
		p = dimension;
		nOfMatrix=p.length-1;
		memo=new int[nOfMatrix+1][nOfMatrix+1];
	}
	
	void reset() {
		count=0;
		for (int i=0;i<nOfMatrix+1;i++)
			for (int j=0;j<nOfMatrix+1;j++)
				memo[i][j]=-1;
		for (int i=0; i<=nOfMatrix; i++)
			memo[i][i]=0;
	}
	
	int getCount() {
		return count;
	}
	
	// 1는 1로 고정, j는 matrix의 개수  
	int matrixChain(int i, int j){
		count++;
		if (i==j) return 0;
		int min=99999999;
		for (int k=i;k<j;k++) { // i <= k <= j-1 즉, 최대는 p[i-1]p[k]p[j-1]과 같음
			// breakPoint를 하나 씩 오른 쪽으로 이동시키면서 최소 값을 찾는다.
			int q=matrixChain(i,k)+matrixChain(k+1,j)+p[i-1]*p[k]*p[j]; 
			if (q<min) min=q;
		}
		return min;
	}
	
	// 시간 복잡도는 O(n^3)
	int matrixChainDP(int i, int j){
		count++;
		if (memo[i][j]>=0)
			return memo[i][j];
		int min=99999999;
		for (int k=i;k<j;k++) { // breakPoint를 하나 씩 오른 쪽으로 이동시키면서 최소 값을 찾는다. 
			if (memo[i][k]<0) memo[i][k]=matrixChainDP(i,k); // memo[i][k]<0 저장이 되어 있지 않다면
			if (memo[k+1][j]<0) memo[k+1][j]=matrixChainDP(k+1,j); // memo[k+1][j]<0 저장이 되어 있지 않다면
			memo[i][j]= Math.min(min, memo[i][k]+memo[k+1][j]+p[i-1]*p[k]*p[j]);
			min = memo[i][j];
		}
		return min;
	}

	int MatrixChainIter(int n) {
		int i, j, k, L, q;

		for (L = 2; L < n; L++) {
			for (i = 1; i < n - L + 1; i++) {
				j = i + L - 1;
				if (j == n)
					continue;
				memo[i][j] = Integer.MAX_VALUE;
				for (k = i; k <= j - 1; k++) {
					q = memo[i][k] + memo[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < memo[i][j])
						memo[i][j] = q;
				}
			}
		}
		return memo[1][n - 1];
	}
	
	public void showMemoTable() {
		for (int i=0; i<=nOfMatrix; i++) {
			for (int j=0; j<=nOfMatrix; j++) 
				 System.out.printf("%5d", memo[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) { // 9x5, 5x15, 15x4, 4x20, 20x7, p 입력
		int [] dimension = { 9, 5, 15, 4, 20, 7 };  // dimension.length=numOfMatrix+1
		int numOfMatrix=dimension.length-1; // 5
		
		MatrixChain mc = new MatrixChain(dimension);
		for (int i=1; i<=numOfMatrix; i++) { // matrix 가 1개일 때부터 matrix가 5개 일때까지의 행렬 곱셈 순서가 최소가 되는 경우
			mc.reset();
			System.out.print("Recursion : "+mc.matrixChain(1,i)+"   Count="+mc.getCount());
			mc.reset();
			System.out.println(" ===> DP : "+mc.matrixChainDP(1,i)+"   Count="+mc.getCount());
			mc.showMemoTable();
		}
	}

}
