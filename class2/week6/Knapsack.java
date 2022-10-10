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
		if (m <= 0 || n <= 0) // m�� ���� ũ��, n�� ������ ���� ����
			return 0;
		else if (m<weight[n]) // �ڱ��ڽ� ������, ���� ũ�Ⱑ ���� �� weight���� ���� ���
			return findMax(m, n-1);
		else 
			return Math.max(price[n]+findMax(m-weight[n], n-1), findMax(m, n-1)); // �ڱ� �ڽ��� ������ ���� ū�� �ƴϸ� �ڱ� �ڽ��� ������ ���� �� ū�� Ȯ��
	}
	
	public int findMaxDP() {
		return findMaxDP(capacity, nOfItems);
	}

	private int findMaxDP(int m, int n) {
		if (m<=0 || n <= 0) { // m�� ���� ũ��, n�� ������ ���� ����
			return 0;
		}
		else if (m<weight[n]){  // �ڱ��ڽ� ������, ���� ũ�Ⱑ ���� �� weight���� ���� ���
			if (dpt[m][n-1]==0) // ���� ������
				dpt[m][n-1]= findMax(m, n-1); // ����
			return dpt[m][n-1];
		}
		else { // ���� ũ�Ⱑ ���� �� weight ���� ū ��� 
			if (dpt[n][m]==0) // ���� ������
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
