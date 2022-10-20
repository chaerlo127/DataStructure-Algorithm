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
		price=new int[nOfItems+1]; // �� ���� ���� ���� 0�� �־������ dp table �ۼ��� ���ؼ� �־���.
		weight=new int[nOfItems+1];
		count = 0;
		for (int i=0;i<nOfItems;i++) { 
			price[i+1]= in[i][0];
			weight[i+1]= in[i][1];
		}
	}
	
	public void initDP() {
		dpt = new int[nOfItems+1][capacity+1];  // table�� 0�� ���� ���� �ε��� �����ϱ� ���ؼ� item�� ����, ������ price�� �ִ� �뷮
		count = 0;
	}
	
	public void showDP() {
		for (int i=1 ; i<=nOfItems ; i++) { // 0�� �κ��� ǥ���� �ʿ䰡 ����
			for (int j=1; j<=capacity; j++) {
				System.out.printf("%4d", dpt[i][j]);
			}
			System.out.println();
		}
	}
	
	public int findMax() {
		return findMax(capacity, nOfItems);
		// �迭 ������, nOfItems + 1�� ���� �ʾҴٸ�, ���⼭ ���� nOfItems - 1�� ����� ��.
		// ���� x4�� array index 3�� �� ���� �Ŵϱ�
		// ���� array �迭 ���ڿ� x1 ~ x4������ ���ڸ� �����ϰ� �ϱ� ���� index 0�� �����͸� �־���� ����.
	}
	
	private int findMax(int m, int n) {
		count++;
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
		count++;
		if (m<=0 || n <= 0) { // m�� ���� ũ��, n�� ������ ���� ����
			return 0;
		}
		else if (m<weight[n]){  // �ڱ��ڽ� ������, ���� ũ�Ⱑ ���� �� weight���� ���� ���
			if (dpt[n-1][m]==0) // ���� ������
				dpt[n-1][m]= findMaxDP(m, n-1); // ����
			return dpt[n-1][m];
		}
		else { // ���� ũ�Ⱑ ���� �� weight ���� ū ��� 
			if (dpt[n][m]==0) // ���� ������
				dpt[n][m] = Math.max(price[n]+findMaxDP(m-weight[n], n-1), findMaxDP(m, n-1));
			return dpt[n][m];
		}
	}
	
	public int findMaxDP2() {
		for (int i = 1; i <= nOfItems; i++) { // data�� ���� 
			for(int j = 1; j <= capacity; j++) { //�뷮
				count++;
				if(weight[i] <= j) // �뷮���� �������� ���԰� ���� ���
					dpt[i][j] = Math.max(price[i] + dpt[i-1][j - weight[i]], dpt[i-1][j]);
				else
					dpt[i][j] = dpt[i-1][j]; // �뷮���� �������� ���԰� ū ���
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
		// �տ������� �� ä��� ��
		System.out.println("Max. Value (DP Iteration) =  "+me.findMaxDP2()+ " Count: " + me.count);
		me.showDP();
	}

}
