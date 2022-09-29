package class2.week3;

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
	
	
	int matrixChain(int i, int j){
		count++;
		if (i==j) return 0;
		int min=99999999;
		for (int k=i;k<j;k++) {
			int q=matrixChain(i,k)+matrixChain(k+1,j)+p[i-1]*p[k]*p[j];
			if (q<min) min=q;	
		}
		return min;
	}
	int matrixChainDP(int i, int j){
		count++;
		if (memo[i][j]>=0)
			return memo[i][j];
		int min=99999999;
		for (int k=i;k<j;k++) {
			if (memo[i][k]<0) memo[i][k]=matrixChainDP(i,k);
			if (memo[k+1][j]<0) memo[k+1][j]=matrixChainDP(k+1,j);
			memo[i][j]= Math.min(min, memo[i][k]+memo[k+1][j]+p[i-1]*p[k]*p[j]);
			min = memo[i][j];
		}
		return min;
	}
	
	public void showMemoTable() {
		for (int i=0; i<=nOfMatrix; i++) {
			for (int j=0; j<=nOfMatrix; j++) 
				 System.out.printf("%5d", memo[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int [] dimension = { 9, 5, 15, 4, 20, 7 };  // dimension.length=numOfMatrix+1
		int numOfMatrix=dimension.length-1;

		MatrixChain mc = new MatrixChain(dimension);
		for (int i=1; i<=numOfMatrix; i++) {
			mc.reset();
			System.out.print("Recursion : "+mc.matrixChain(1,i)+"   Count="+mc.getCount());
			mc.reset();
			System.out.println(" ===> DP : "+mc.matrixChainDP(1,i)+"   Count="+mc.getCount());
			mc.showMemoTable();
		}
	}

}
