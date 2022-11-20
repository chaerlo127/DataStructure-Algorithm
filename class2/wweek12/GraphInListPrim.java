package class2.wweek12;

import java.util.Arrays;
import java.util.HashSet;

public class GraphInListPrim extends GraphInList{ 

	HashSet<String> V, S;
	int [] d;
	
	public void MSTPrim(String start) {
		V = new HashSet<String>();
		S = new HashSet<String>();
		d= new int[Vertices.size()];
		for (String s : Vertices )
			V.add(s);
		Arrays.fill(d, 9999);
		int r = Vertices.indexOf(start);
		d[r]=0;
		
		Prim();
	}

	public void Prim() {
		
		System.out.println("\n[Minimal Spaning Tree : Prim]\n");

		while(S.size()<Vertices.size()) {
			System.out.println("Set S : "+S);

			String u = extractMin(diff(V,S));  // diff(V,S) == V-S
			S.add(u);
			System.out.println(">>> "+u+" is selected.");
			for (String v : adjacent(u)) {  // L(u) == adjacent(u)
				HashSet<String> temp = diff(V,S);
				int wuv = getWeight(u, v);
				int dv = d[Vertices.indexOf(v)];
				if (temp.contains(v) &&  wuv<dv) 
					d[Vertices.indexOf(v)] = wuv ;
			}
		}
		System.out.println("Set S [Final] : "+S);
	}

	private int getWeight(String u, String v) {

		int ui = getIndex(u);
		MyLinkedList3<Node> aList = adjacentList.get(ui);
		
		for (int i=0; i<aList.size();i++) {
			if (aList.get(i).key.equals(v))
				return aList.get(i).num;
		}
		
		return -1;
		
	}

	private HashSet<String> diff(HashSet<String> s1, HashSet<String> s2) {
		HashSet<String> result = s1;
		for (String s : s2)
			result.remove(s);
		return result;
	}
	
	private String extractMin(HashSet<String> diff) {
		String minVertex = null;
		int min = 9999;;
		for (String s : diff) {
			if (d[Vertices.indexOf(s)] < min) {
				minVertex = s;
				min = d[Vertices.indexOf(s)];
			}
		}
		return minVertex;
	}

	public static void main(String[] args) {
		
		String [] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan","Mokpo", "Chuncheon", "Kyeongju"};
		int [][] paths = {
				{0,1,59},{0,2,140},{0,3,237},{0,7,313},{0,8,75},
				{1,7,295},{1,8,105},
				{2,3,122},{2,4,141},
				{3,4,173},{3,5,88},{3,6,74},{3,8,236},
				{4,5,202},{4,7,57},
				{5,9,76},
				{6,8,293},{6,9,36}
		};
		
		GraphInListPrim gm = new GraphInListPrim();
		
		gm.createGraph();
		
		for (int i=0; i<paths.length;i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);
		
		System.out.println(gm.adjacent("Seoul"));
		
		gm.showGraph();
		
		gm.MSTPrim("Seoul");
		
	}
}
