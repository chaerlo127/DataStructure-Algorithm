package class2.week2;

// ���൹ ���� �˰���
// �ð� ���⵵ O(n)
public class Pebbles {
	int [][] peb;
	int [][] memo;
	int [][] nextPattern = { // ���� p�� �� ���� �� �ִ� ���� Pattern�� ����� ��
			{0, 0, 0},
			{2, 3, 0},  // ���� 1�� ����ϴ� ���� ���� 2, 3 
			{1, 3, 4},  // ���� 2�� ����ϴ� ���� ���� 1, 3, 4
			{1, 2, 0},  // ���� 3�� ����ϴ� ���� ���� 1, 2
			{2, 0, 0}}; // ���� 4�� ����ϴ� ���� ���� 2
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
		for(int j = 1; j<=4; j++) { // ���� 1 ~ 4�� ���
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
		// �迭�� ���� 1�� ���� �̸� �����صд�.
		for(int j = 1; j<=4; j++) {
			memo[j][1] = aPatternValue(1, j);
		}
		
		for(int j = 2; j<=i ; j++) { // ���� ���� ���� �ϳ� �� �ø���
			for(int a = 1; a<=4; a++) { // ���� ���� ��� -> ���� ����� ���� 1 ~ 4���� ����. 
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
	
	// i�� ����(��), p�� ����(����) 
	private int pebble(int i, int p) {
		count++;
		if(i == 1) {
			return aPatternValue(i, p);
		}else {
			int max = -99999;
			int k = 0; 
			while(k<3&&nextPattern[p][k]!=0) { // ���� p�� ����� ��, k: ���Ͽ��� ���� �� �ִ� ����� ��
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1, q), max);  /// ���Ͽ��� ���� �� �ִ� ��� ����� ���� ������.
				k++;
			}
			return aPatternValue(i, p) + max;
		}
	}
	
	public void showDP() {
		for (int i=1 ; i<this.memo.length; i++) { // 0�� �κ��� ǥ���� �ʿ䰡 ����
			for (int j=1; j<memo[0].length; j++) {
				System.out.printf("%4d", memo[i][j]);
			}
			System.out.println();
		}
	}
	
	// p�� ����, i�� ���� 
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1: retVal = peb[1][i]; // ù ��° ������ ���� input ��
		break;
		case 2: retVal = peb[2][i]; // �� ��° ������ ���� input ��
		break;
		case 3: retVal = peb[3][i]; // �� ��° ������ ���� input ��
		break;
		case 4: retVal = peb[1][i] + peb[3][i]; // �� ��° ������ ���� input ��
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
