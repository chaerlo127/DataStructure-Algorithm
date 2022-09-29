package class2.week2;

public class MatrixChain {
	int [] p;
	int nOfMatrix;
	int count;
	int [][] memo;
	public MatrixChain(int[] dimension) {
		p = dimension;
		nOfMatrix = p.length -1;
		memo = new int [nOfMatrix][nOfMatrix];
	}
	
	int matrixChain(int i, int j) {
		count++;
		if(i==j) return 0;
		int min= 999999999;
		for(int k = i; k<j ; k++) {
			int q = matrixChain(i, k) + matrixChain(k+1, j)+ p[i-1]*p[k]*p[j];
			if(q<min) min = q;
		}
		return min;
	}
	
//	int matrixChainDP(int i, int j) {
//		count++;
//		if(memo[i][j]>=0) {
//			return memo[i][j];
//		}
//		int min = 99999999;
//		for (int k = i; k<j; k++) {
//			if(memo[i][k]<0) memo[i][k] = matrixChainDP(i, k);
//			if(memo[k+1][j]<0) memo[k+1][j] = matrixChainDP(k+1, j);
//		}
//	}
	void reset() {
		count = 0;
		for(int mi = 0; mi<nOfMatrix+1; mi++) {
			for(int mj = 0; mj<nOfMatrix+1; mj++) {
				memo[mi][mj] = -99999;
			}
		}
	}
	int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		int[] dimension = {9, 5, 15, 4, 20, 7};// 9x5, 5x15, 15x4, 4x20, 20x7, p ют╥б
		int numOfMatrix = dimension.length -1;
		
		MatrixChain mc = new MatrixChain(dimension);
		for (int i =0; i<=numOfMatrix; i++) {
			System.out.println("Recursion : "+ mc.matrixChain(1, i) + " Count: " + mc.getCount());
		}
	}

	
}
