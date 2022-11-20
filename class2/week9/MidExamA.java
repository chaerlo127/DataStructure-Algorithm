package class2.week9;

public class MidExamA {

	/////////////////////////////////////////////////////////////////////////
	//Q1 : Graph in LinkedList
	/////////////////////////////////////////////////////////////////////////
	class Node	{
		String key;
		Node next;
		int num ;  

		public Node (String k) {
			key =k;
			next = null;
			num=0;
		}
	}

	Node [] graph;
	int nOfCities;
	public void setCities(String [] cities) {
		nOfCities = cities.length;
		graph = new Node [nOfCities];
		for (int i=0;i<nOfCities;i++)
			graph[i] = new Node(cities[i]);
		// ���� ���ǰ� �Ǿ� ������, �ϳ��ϳ��� ��� Ÿ���� ���� �ʱ�ȭ�� �Ǿ� ���� ����. �ٽ� new �� ����� ��������� ���������� ���� ������ �ǰ� �Ǵ� ���̴�.
	}	
	/////////////////////////////////////////////////////////////////////////
	//fill your codes for the following method...
	/////////////////////////////////////////////////////////////////////////
	/**
	 * ȸ��: ���� ������ ���� ���� ���� ����. 
	 */
	public void insertEdgeAscendingOrder(int c1, int c2, int dist) {
		
		graph[c1].num++;

		Node p = graph[c1];
		while (p.next!=null) {
			if (p.next.num>dist) { // ����Ʈ�� ���� Ȯ���ϴٰ� �ִ� ���� ���� ���
				Node newNode = new Node (graph[c2].key); 
				newNode.num = dist;
				newNode.next = p.next;
				p.next = newNode;
				return;
			}
			p=p.next;
		}
		p.next =new Node(graph[c2].key);
		p.next.num = dist;
	}
	/////////////////////////////////////////////////////////////////////////

	public void showGraph() {
		for (int i=0; i<nOfCities;i++) {
			System.out.print("  City-Index "+i+" : "+graph[i].key+"["+graph[i].num+"]");
			Node p = graph[i].next;
			while (p!=null) {
				System.out.print(" -> "+p.key+"("+p.num+")");
				p=p.next;
			}
			System.out.println();
		}
	}


	/////////////////////////////////////////////////////////////////////////
	//Q2 -1: longest Consecutive Sequence
	/////////////////////////////////////////////////////////////////////////

	// 1. ���̰� 0�̸�
	// 2. ���̰� 1�̸�
	// 3. ���̰� ���� �� ���� ã�ƶ�
	public String longConsecutiveSeq(String s) {
		if(s.isEmpty())
			return "";
		int size=s.length();
		if (size==1)
			return s;
		int k=size-1;

		// ������ ���� ���ǿ� �´� ������ ����. 
		char temp = s.charAt(size-1);
		while (k>0 && temp>s.charAt(k-1)) {
			temp =s.charAt(k-1);
			k--;
		}
		String temp1 = s.substring(k,size);
		String temp2="";
		if (k>0)
			temp2 = longConsecutiveSeq(s.substring(0,k));
		if (temp1.length()>temp2.length())
			return temp1;
		else 
			return temp2;
	}
	/////////////////////////////////////////////////////////////////////////
	//Q2 -2: longest Hidden Sequence
	/////////////////////////////////////////////////////////////////////////

	public String longEmbeddedSeq(String s) {
		if(s.isEmpty())
			return "";
		int size=s.length();
		if (size==1)
			return s;
		int k=size-1;

		String sTemp= ""+s.charAt(size-1);
		char cTemp = s.charAt(size-1);
		while (k>0) {
			if (cTemp>s.charAt(k-1)) {
				sTemp = s.charAt(k-1)+ sTemp;
				cTemp =s.charAt(k-1);
			}
			k--;
		}
		String temp1 = sTemp;
		String temp2= longEmbeddedSeq(s.substring(0,size-1));
		if (temp1.length()>temp2.length())
			return temp1;
		else 
			return temp2;
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 : Max. Price
	/////////////////////////////////////////////////////////////////////////

	int [][] priceWeight ;
	int nProduct, maxW;
	int [][] dptable;
	public void setPriceWeight(int [][] input) {
		priceWeight = input;
		nProduct = priceWeight.length-1;
		maxW=getMaxWeight();
		dptable=new int [nProduct+1][getMaxWeight()+1];
	}

	private int getMaxWeight() {
		int max=0;
		for (int i=1;i<=nProduct;i++) 
			if (priceWeight[i][1]>max) 
				max=priceWeight[i][1];
		return max;
	}
	public int getMaxPrice() {
		int order = nProduct;
		int wLimit = getMaxWeight();

		return getMaxPrice(order, wLimit);
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 -1: Max Price : Recursion
	/////////////////////////////////////////////////////////////////////////
	private int getMaxPrice(int order, int wLimit) {
		if (order<=0)
			return 0;
		if (priceWeight[order][1]>wLimit) {
			return getMaxPrice(order-1, wLimit);
		}
		else {
			int tempA=priceWeight[order][0]+getMaxPrice(order-1, priceWeight[order][1]);
			int tempB=getMaxPrice(order-1, wLimit);
			return Math.max(tempA, tempB);
		}
	}
	/////////////////////////////////////////////////////////////////////////

	public int getMaxPriceDP() {
		return getMaxPriceDP(nProduct, getMaxWeight() );
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 -2: Max Price : Dynamic Programming
	/////////////////////////////////////////////////////////////////////////
	private int getMaxPriceDP(int o, int w) {
		if (o<=0 || w<=0)
			return 0;
		if (priceWeight[o][1]>w) {
			return dptable[o][w]=getMaxPriceDP(o-1, w);
		}
		else {
			if (dptable[o][w]==0) {
				if (dptable[o][priceWeight[o][1]]==0)
					dptable[o][priceWeight[o][1]]=priceWeight[o][0]+getMaxPriceDP(o-1, priceWeight[o][1]);
				dptable[o][w]=Math.max(dptable[o][priceWeight[o][1]],getMaxPriceDP(o-1, w));
			}
			return dptable[o][w];
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void showDPT() {
		for (int i=1 ; i<=nProduct ; i++) {
			for (int j=1; j<=getMaxWeight(); j++) {
				System.out.printf("%5d", dptable[i][j]);
			}
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		MidExamA q = new MidExamA();

		/////////////////////////////////////////////////////////////////////////

		System.out.print("\n<<< Question 1 : Graph in LinkedList  >>>\n\n");
		String [] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan","Mokpo", "Chuncheon", "Kyeongju"};
		int [][] distance = {
				{0,1,59},{0,2,140},{0,3,237},{0,7,313},{0,8,75},
				{1,7,295},{1,8,105},
				{2,3,122},{2,4,141},
				{3,4,173},{3,5,88},{3,6,74},{3,8,236},
				{4,5,202},{4,7,57},
				{5,9,76},
				{6,8,293},{6,9,36}
		};
		int n = distance.length;
		q.setCities(cities);
		for (int i=0; i<n;i++)
			q.insertEdgeAscendingOrder(distance[i][0], distance[i][1], distance[i][2]);
		q.showGraph();

		/////////////////////////////////////////////////////////////////////////

		System.out.print("\n<<< Question 2 : Longest Subsequence  >>>\n");
		String input ="AFHYBCLDTEHMWXK";
		System.out.print("\n     2-1 Consecutive Subsequence : ");
		System.out.print(q.longConsecutiveSeq(input) +"\n");
		System.out.print("\n     2-2 Embedded Subsequence : ");
		System.out.print(q.longEmbeddedSeq(input) +"\n");

		/////////////////////////////////////////////////////////////////////////

		System.out.print("\n<<< Question 3 : Max. Price >>> \n");

		int [][] priceWeight = {
				{0,0},
				{30,3},
				{20,7},
				{35,2},
				{25,5},
				{17,8},
				{50,5},
				{33,4},
				{40,7},
				{39,5}
		};
		q.setPriceWeight(priceWeight);
		System.out.print("\n     3-1 Max. Price <- Recursion : "+q.getMaxPrice()+"\n");
		System.out.print("\n     3-2 Max. Price <- DP : "+q.getMaxPriceDP()+"\n");
		q.showDPT();

		/////////////////////////////////////////////////////////////////////////
	}
}