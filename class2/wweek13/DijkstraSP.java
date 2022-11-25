package class2.wweek13;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class DijkstraSP extends DGraphInList {
	int [] d  ;   // distance; 
	int r = -1;   // start node
	HashSet<String> S, V ;
	String [] prev;

	int maxNumber = 0;

	public DijkstraSP() {
	}
	
	public void init(String start) {
		maxNumber = Vertices.size();

		d = new int [maxNumber];
		S = new HashSet<>();
		V = new HashSet<>();
	    
		prev = new String[maxNumber];
		
		for (String s : Vertices )
			V.add(s);
		r = Vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r]=0;
	}
	
	public void shortestPath() {

		while(S.size()<maxNumber) {
			String u = extractMin(diff(V,S));  // diff(V,S) == V-S
			S.add(u);
			System.out.println(">>> "+u+" is selected.");
			for (String v : adjacent(u)) {  // L(u) == adjacent(u)
				HashSet<String> temp = diff(V,S);
				int wuv = getWeight(u, v);
				int dv = d[Vertices.indexOf(v)];
				
				int du = d[Vertices.indexOf(u)];
				
				if (temp.contains(v) &&  (du+wuv)<dv) {
					d[Vertices.indexOf(v)] = du+wuv ;
					prev[Vertices.indexOf(v)] = u;
				}
			}
		}
		for (int i=0; i<maxNumber; i++)
			System.out.print(Vertices.get(i)+"("+d[i]+")");
		System.out.println();

	}
	
	public void showShortestPath() {
		System.out.println("\n< Shortest Path(s) : Dijkstra >");
		for (int i=0; i<Vertices.size();i++){
			System.out.print(prev[i] +"  ");
			System.out.print(" => "+ Vertices.get(i)+"("+d[i]+")");
			System.out.println();
		}
	}
	
	private int getWeight(String u, String v) {
		int ui = getIndex(u);
		LinkedList<Node> aList = adjacentList.get(ui);
		
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

		DijkstraSP myG = new DijkstraSP();

		myG.createGraph();
		for (int i = 0; i<paths.length; i++)
			myG.insertEdge(cities[paths[i][0]],cities[paths[i][1]], paths[i][2]);
		myG.showGraph();

		System.out.println("\nDijkstra Shortest Path Algorithm ");
		myG.init("Seoul");
		myG.shortestPath();
		myG.showShortestPath();
	}

}

