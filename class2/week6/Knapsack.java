package class2.week6;

public class Knapsack {
	int capacity, nOfItems ; 
	int [] weight, price ;
	int [][] dpt; // dynamic programming table
	public Knapsack(int m, int [][] in) {
		capacity = m;
		nOfItems=in.length;
		price=new int[nOfItems+1];
		weight=new int[nOfItems+1];
		for (int i=0;i<nOfItems;i++) {
			price[i+1]= in[i][0];
			weight[i+1]= in[i][1];
		}
	}
	public void initDP() {
		dpt = new int[nOfItems+1][capacity+1];  
	}
	
	public void showDP() {
		for (int i=1 ; i<=nOfItems ; i++) {
			for (int j=1; j<=capacity; j++) {
				System.out.printf("%4d", dpt[i][j]);
			}
			System.out.println();
		}
	}
	
	public int findMax() {
		return findMax(capacity, nOfItems);
	}
	
	private int findMax(int m, int n) {
		if (m <= 0 || n <= 0) // m은 남은 크기, n은 물건의 남은 개수
			return 0;
		else if (m<weight[n]) // 자기자신 미포함, 남은 크기가 현재 내 weight보다 작은 경우
			return findMax(m, n-1);
		else 
			return Math.max(price[n]+findMax(m-weight[n], n-1), findMax(m, n-1)); // 자기 자신을 포함한 것이 큰지 아니면 자기 자신을 제외한 것이 더 큰지 확인
	}
	
	public int findMaxDP() {
		return findMaxDP(capacity, nOfItems);
	}

	private int findMaxDP(int m, int n) {
		if (m<=0 || n <= 0) { // m은 남은 크기, n은 물건의 남은 개수
			return 0;
		}
		else if (m<weight[n]){  // 자기자신 미포함, 남은 크기가 현재 내 weight보다 작은 경우
			if (dpt[m][n-1]==0) // 값이 없으면
				dpt[m][n-1]= findMax(m, n-1); // 저장
			return dpt[m][n-1];
		}
		else { // 남은 크기가 현재 내 weight 보다 큰 경우 
			if (dpt[n][m]==0) // 값이 없으면
				dpt[n][m] = Math.max(price[n]+findMaxDP(m-weight[n], n-1), findMaxDP(m, n-1));
			return dpt[n][m];
		}
	}
	
	public int findMaxDP2() {

		for (int i = 1; i <= nOfItems; i++) {
			for(int j = 1; j <= capacity; j++) {
				if(weight[i] <= j)
					dpt[i][j] = Math.max(price[i] + dpt[i-1][j - weight[i]], dpt[i-1][j]);
				else
					dpt[i][j] = dpt[i-1][j];
			}
		}
		return dpt[nOfItems][capacity];
	}
	
	public static void main(String[] args) {
		// P(M, n) = max[ {pn  + P(M - wn , n-1)}, P(M , n-1)]
		int M = 8;
		int [][] data = {{3,1},{2,2},{5,3},{3,4}};  // {price, weight}
		
		Knapsack me = new Knapsack(M, data);
		System.out.println("Max. Value (Recursion) =  "+me.findMax());
		me.initDP();
		System.out.println("Max. Value (Recursion+DP) =  "+me.findMaxDP());
		me.showDP();
		me.initDP();
		System.out.println("Max. Value (DP Iteration) =  "+me.findMaxDP2());
		me.showDP();
	}

}
