package class2.week2;

// 조약돌 놓기 알고리즘
// 시간 복잡도 O(n)
public class Pebbles {
	int [][] peb;
	int [][] memo;
	int [][] nextPattern = { // 행이 p일 때 나올 수 있는 다음 Pattern의 경우의 수
			{0, 0, 0},
			{2, 3, 0},  // 패턴 1을 사용하는 경우는 행이 2, 3 
			{1, 3, 4},  // 패턴 2를 사용하는 경우는 행이 1, 3, 4
			{1, 2, 0},  // 패턴 3을 사용하는 경우는 행이 1, 2
			{2, 0, 0}}; // 패턴 4를 사용하는 경우는 행이 2
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
		for(int j = 1; j<=4; j++) { // 패턴 1 ~ 4인 경우
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
	

	private int maxPebbleIter(int n) {
		int max = -99999;
		for(int j = 1; j<=4; j++) {
			max = Math.max(pebbleIter(n, j), max);
		}
		return max;
	}
	
	// i = 2, p = 1
	private int pebbleIter(int i, int p) {
		if(i == 1) {
			memo[p][i] = aPatternValue(i, p);
			count++;
			return memo[p][i];
		}
		// 배열에 열이 1인 것을 미리 저장해둔다.
		for(int j = 1; j<=4; j++) {
			memo[j][1] = aPatternValue(1, j);
		}
		
		for(int j = 2; j<=i ; j++) { // 지삭 행의 개수 하나 씩 늘리기
			for(int a = 1; a<=4; a++) { // 다음 열인 경우 -> 패턴 경우의 수가 1 ~ 4까지 있음. 
				int k = 0;
				int max = -99999;
				while(k<3&&nextPattern[a][k]!=0) {
					count++;
					int q = nextPattern[a][k];
					
					max =  Math.max(memo[q][j-1], max);
					k++;
				}
				memo[a][j] = aPatternValue(j, a) + max;
			}
		}
		int max2 = memo[1][i];
		for(int j = 2; j<=4;j++) {
			max2 = Math.max(memo[j][i], max2);
		}
		return max2; 
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
	
	// i는 가로(형), p는 세로(패턴) 
	private int pebble(int i, int p) {
		count++;
		if(i == 1) {
			return aPatternValue(i, p);
		}else {
			int max = -99999;
			int k = 0; 
			while(k<3&&nextPattern[p][k]!=0) { // 패턴 p를 사용할 때, k: 패턴에서 나올 수 있는 경우의 수
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1, q), max);  /// 패턴에서 나올 수 있는 모든 경우의 수를 따진다.
				k++;
			}
			return aPatternValue(i, p) + max;
		}
	}
	
	public void showDP() {
		for (int i=1 ; i<this.memo.length; i++) { // 0인 부분을 표현할 필요가 없음
			for (int j=1; j<memo[0].length; j++) {
				System.out.printf("%4d", memo[i][j]);
			}
			System.out.println();
		}
	}
	
	// p가 세로, i가 가로 
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1: retVal = peb[1][i]; // 첫 번째 패턴일 때의 input 값
		break;
		case 2: retVal = peb[2][i]; // 두 번째 패턴일 때의 input 값
		break;
		case 3: retVal = peb[3][i]; // 세 번째 패턴일 때의 input 값
		break;
		case 4: retVal = peb[1][i] + peb[3][i]; // 네 번째 패턴일 때의 input 값
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
			System.out.printf("  >>> %3d : [DynamicProg.] %3d Count = %-10d", i, myPeb.maxPebbleDP(i), myPeb.getCount());
			System.out.println();
			myPeb.showDP();
			myPeb.reset();
			System.out.printf("  >>> %3d : [Iteration] %3d Count = %-10d%n", i, myPeb.maxPebbleIter(i), myPeb.getCount());
		}
	}
}
