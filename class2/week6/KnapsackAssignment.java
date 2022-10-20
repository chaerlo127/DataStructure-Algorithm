package class2.week6;

public class KnapsackAssignment {
	/*
	 * recursion: O(2^n)
	 * DP: O(mn)
	 * */
	int capacity, nOfItems ; 
	int [] weight, price ;
	int [][] dpt; // dynamic programming table
	int count;
	public KnapsackAssignment(int m, int [][] in) {
		capacity = m;
		nOfItems=in.length;
		price=new int[nOfItems+1]; // 맨 위와 왼쪽 끝에 0을 넣어줘야지 dp table 작성이 편해서 넣어줌.
		weight=new int[nOfItems+1];
		count = 0;
		for (int i=0;i<nOfItems;i++) { 
			price[i+1]= in[i][0];
			weight[i+1]= in[i][1];
		}
	}
	
	public void initDP() {
		dpt = new int[nOfItems+1][capacity+1];  // table에 0은 값이 없게 두도록 저장하기 위해서 item의 개수, 저장할 price의 최대 용량
		count = 0;
	}
	
	public void showDP() {
		for (int i=1 ; i<=nOfItems ; i++) { // 0인 부분을 표현할 필요가 없음
			for (int j=1; j<=capacity; j++) {
				System.out.printf("%4d", dpt[i][j]);
			}
			System.out.println();
		}
	}
	
	public int findMax() {
		return findMax(capacity, nOfItems);
		// 배열 생성시, nOfItems + 1을 하지 않았다면, 여기서 원래 nOfItems - 1을 해줘야 함.
		// 원래 x4가 array index 3에 들어가 있을 거니까
		// 현재 array 배열 숫자와 x1 ~ x4까지의 숫자를 동일하게 하기 위해 index 0에 데이터를 넣어두지 않음.
	}
	
	private int findMax(int m, int n) {
		count++;
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
		count++;
		if (m<=0 || n <= 0) { // m은 남은 크기, n은 물건의 남은 개수
			return 0;
		}
		else if (m<weight[n]){  // 자기자신 미포함, 남은 크기가 현재 내 weight보다 작은 경우
			if (dpt[n-1][m]==0) // 값이 없으면
				dpt[n-1][m]= findMaxDP(m, n-1); // 저장
			return dpt[n-1][m];
		}
		else { // 남은 크기가 현재 내 weight 보다 큰 경우 
			if (dpt[n][m]==0) // 값이 없으면
				dpt[n][m] = Math.max(price[n]+findMaxDP(m-weight[n], n-1), findMaxDP(m, n-1));
			return dpt[n][m];
		}
	}
	
	public int findMaxDP2() {
		for (int i = 1; i <= nOfItems; i++) { // data의 개수 
			for(int j = 1; j <= capacity; j++) { //용량
				count++;
				if(weight[i] <= j) // 용량보다 데이터의 무게가 작은 경우
					dpt[i][j] = Math.max(price[i] + dpt[i-1][j - weight[i]], dpt[i-1][j]);
				else
					dpt[i][j] = dpt[i-1][j]; // 용량보다 데이터의 무게가 큰 경우
			}
		}
		return dpt[nOfItems][capacity];
	}
	
	public static void main(String[] args) {
		// P(M, n) = max[ {pn  + P(M - wn , n-1)}, P(M , n-1)]
		int M = 8;
		int [][] data = {{3,1},{2,2},{5,3},{3,4}};  // {price, weight}
		
		KnapsackAssignment me = new KnapsackAssignment(M, data);
		System.out.println("Max. Value (Recursion) =  "+me.findMax() + " Count: " + me.count);
		me.initDP();
		System.out.println("Max. Value (Recursion+DP) =  "+me.findMaxDP()+ " Count: " + me.count);
		me.showDP();
		me.initDP();
		// 앞에서부터 다 채우는 것
		System.out.println("Max. Value (DP Iteration) =  "+me.findMaxDP2()+ " Count: " + me.count);
		me.showDP();
	}

}
