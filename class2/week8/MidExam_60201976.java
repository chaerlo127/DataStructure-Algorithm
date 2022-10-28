package class2.week8;

import java.util.ArrayList;

// 60201976 장채은
public class MidExam_60201976 {

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
	}	
	/////////////////////////////////////////////////////////////////////////
	//fill your codes for the following method...
	/////////////////////////////////////////////////////////////////////////
	public void insertEdgeAscendingOrder(int c1, int c2, int dist) { // city, city, distance
	// Q1 code 	
		// insert 되는 부분만 만들면 됨.
		Node p = graph[c1];
		while(p.next != null) { // LinkedList의 마지막 부분을 찾는다.
			graph[c1].num++; // graph의 첫 번째 노드에 num을 넣어준다.
			p = p.next;
		}
		p.next = new Node(graph[c2].key); // 마지막 부분이 null이면 새로운 노드를 만들어주고, 그 노드의 key 값을 넣어준다.
		p.next.num = dist; // distance의 값을 넣어준다.
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

	public String longConsecutiveSeq(String s) { //"AFHYBCLDTEHMWXK"
		int[] array = new int[s.length()+1];
		for(int i = s.length(); i>0; i--) {
			array[i] = (int) s.charAt(i-1);
		}
		ArrayList<String> stArray =  new ArrayList<>();
		String answer = "";
		for(int i = s.length()-1; i>0 ; i--) {
			char a = (char) array[i+1]; // array X
			String b = String.valueOf(a);
			while (array[i] != a) {
				a--;// array[i-1]와 a에서 뺀 값이 같아야 함.
				if (a == 'A'-1)
					break; // 끝까지 왔으면 break;
			}
			if(a != 'A'-1) {
				answer += a; //  break;
			}else {
				answer = "";
			}
		}
//		ArrayList<Character> chArray = new ArrayList<>();
//		ArrayList<String> stArray =  new ArrayList<>();
//		for(int i = s.length(); i>0; i--) {
//			array[i] = (int) s.charAt(i-1);
//			if(array[i] >= array[i-1]) {
//				chArray.add((char)array[i]);
//			}else {
//				String size = "";
//				while(!chArray.isEmpty()) {
//					size += String.valueOf(chArray.poll());
//				}
//				if(!size.equals("")) stArray.add(size);
//				chArray.clear();
//				chArray.add((char)array[i]);
//			}
//		}
//		
//		String k = stArray.get(0);
//		for(String answer: stArray){
//			if(k.length()<answer.length()) {
//				k = answer;
//			}
//		}
		return answer; 
	}
	/////////////////////////////////////////////////////////////////////////
	//Q2 -2: longest Hidden Sequence
	/////////////////////////////////////////////////////////////////////////

	public String longEmbeddedSeq(String s) {
		
		return null;
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 : Max. Price
	/////////////////////////////////////////////////////////////////////////

	int [][] priceWeight ;
	int nProduct, maxW;
	int [][] DPT;
	public void setPriceWeight(int [][] input) {
		priceWeight = input;
		nProduct = priceWeight.length-1;
		maxW=getMaxWeight();
		DPT=new int [nProduct+1][getMaxWeight()+1];
	}
	private int getMaxWeight() {
		int max=0;
		for (int i=1;i<=nProduct;i++) 
			if (priceWeight[i][1]>max) 
				max=priceWeight[i][1];
		return max;
	}

	public int getMaxPrice() {
		int order = nProduct-1;
		int wLimit = getMaxWeight();

		return getMaxPrice(order, wLimit);
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 -1: Max Price : Recursion
	/////////////////////////////////////////////////////////////////////////
	private int getMaxPrice(int order, int wLimit) { // price weight 의 왼쪽, price weight 의 오른 쪽
		// recursion
		if (order <= 0 || wLimit <= 0) // wLimit은 남은 크기, order은 물건의 남은 개수
			return 0;
		else if (wLimit<priceWeight[order][1]) // 자기자신 미포함, 남은 크기가 현재 내 weight보다 작은 경우
			return getMaxPrice(order-1, wLimit);
		else 
			return Math.max(priceWeight[order][1]+getMaxPrice(order-1, wLimit-priceWeight[order][1]), getMaxPrice(order-1, wLimit)); 
		// 자기 자신을 포함한 것이 큰지 아니면 자기 자신을 제외한 것이 더 큰지 확인
	}
	/////////////////////////////////////////////////////////////////////////

	public int getMaxPriceDP() {
		return getMaxPriceDP(nProduct, getMaxWeight() );
	}

	/////////////////////////////////////////////////////////////////////////
	//Q3 -2: Max Price : Dynamic Programming
	/////////////////////////////////////////////////////////////////////////
	private int getMaxPriceDP(int o, int w) {
		if (o <= 0 || o <= 0) // wLimit은 남은 크기, order은 물건의 남은 개수
			return 0;
		else if (w<priceWeight[o][1]) {// 자기자신 미포함, 남은 크기가 현재 내 weight보다 작은 경우
			if(DPT[o-1][w]==0) DPT[o-1][w]= getMaxPriceDP(o-1, w);
			return DPT[o-1][w];
		}
		else {
			if(DPT[o][w]==0) Math.max(priceWeight[o][1]+getMaxPriceDP(o-1, w-priceWeight[o][1]), getMaxPriceDP(o-1, w));
			return DPT[o][w]; 
		}
		// 자기 자신을 포함한 것이 큰지 아니면 자기 자신을 제외한 것이 더 큰지 확인

	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public void showDPT() {
		for (int i=1 ; i<=nProduct ; i++) {
			for (int j=1; j<=getMaxWeight(); j++) {
				System.out.printf("%5d", DPT[i][j]);
			}
			System.out.println();
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		MidExam_60201976 q = new MidExam_60201976();

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

		/////////////////////////////////s////////////////////////////////////////

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