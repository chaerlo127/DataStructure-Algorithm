package class2.week1;

public class Pebbles {
	int [][] peb;
	int [][] memo;
	int [][] nextPattern = {
			{0, 0, 0},
			{2, 3, 0},
			{1, 3, 4},
			{1, 2, 0},
			{1, 2, 0},
			{2, 0, 0}};
	int nCols;
	int count;
	public Pebbles(int[][] input) {
		peb = input;
		nCols = peb[0].length;
		memo = new int[5][nCols];
	}
	void reset() {
		count = 0;
		for(int mi = 0; mi<5; mi++) {
			for(int mj = 0; mj<nCols; mj++) {
				memo[mi][mj] = -99999;
			}
		}
	}
	int getCount() {
		return count;
	}
	public int maxPebble(int n) {
		int max = -99999;
		for(int j = 1; j<=4; j++) {
			max = Math.max(pebble(n, j), max);
		}
		return max;
	}
	
	public int maxPebbleDP(int n) {
		int max = -99999;
		for(int j = 1; j<=4; j++) {
			max = Math.max(pebbleDP(n, j), max);
		}
		return max;
	}
	private int pebbleDP(int i, int p) {
		count++;
		if(memo[p][i]>-99999) {
			return memo[p][i];
		}
		if(i == 1) {
			memo[p][i] = aPatternValue(i, p);
			return memo[p][i];
		}else {
			int max = -99999;
			int k = 0; 
			while(k<3&&nextPattern[p][k]!=0) {
				int q = nextPattern[p][k];
				if(memo[q][i-1] == -99999) {
					memo[q][i-1] = pebbleDP(i-1, q);
				}
				max =  Math.max(memo[q][i-1], max);
				k++;
			}
			memo[p][i] = aPatternValue(i, p) + max;
			return memo[p][i];
		}
	}
	int pebble(int i, int p) {
		count++;
		if(i == 1) {
			return aPatternValue(i, p);
		}else {
			int max = -99999;
			int k = 0; 
			while(k<3&&nextPattern[p][k]!=0) {
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1, q), max);
				k++;
			}
			return aPatternValue(i, p) + max;
		}
	}
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1: retVal = peb[1][i];
		break;
		case 2: retVal = peb[2][i];
		break;
		case 3: retVal = peb[3][i];
		break;
		case 4: retVal = peb[1][i] + peb[3][i];
		break;
		}
		return retVal;
	}

	public static void main(String[] args) {
		int [][] input = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 6, 7, 12, -5, 5, 3, 11, 3, 7, -2, 5, 4},
			{0, -8, 10, 14, 9, 7, 13, 8, 5, 6, 1, 3, 9},
			{0, 11, 12, 7, 4, 8, -2, 9, 4, -4, 3, 7, 10}};
		Pebbles myPeb = new Pebbles(input);
		
		for(int i = 1; i<input[0].length ; i++) {
			myPeb.reset();
			System.out.printf(">>> %3d : [Recursion] %3d Count = %-10d", i, myPeb.maxPebble(i), myPeb.getCount());
			myPeb.reset();
			System.out.printf("  >>> %3d : [DynamicProg.] %3d Count = %-10d%n", i, myPeb.maxPebbleDP(i), myPeb.getCount());
		}
	}
		

}
