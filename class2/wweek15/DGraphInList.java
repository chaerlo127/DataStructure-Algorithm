package class2.wweek15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DGraphInList {// Vertices # is fixed, directed Graph in List
	
	public class Node {
		public String key;
		public int num ;  
		
		public Node (String k, int n) {
				key =k;
				num=n;
		}

		public boolean equals(Node other) {
			return this.key.equals(other.key);
		}
		
		public int compareTo(Node other) {    
			return this.key.compareTo(other.key);
		}
		
		public String toString() {
			return key+"("+num+") ";
		}
	}
	
	// define Vertices, adjacentList, visited
	protected ArrayList<String> Vertices;
	protected ArrayList<LinkedList<Node>> adjacentList;
	
	protected boolean [] visited;
		
	public void createGraph() {
		Vertices = new ArrayList<>();
		adjacentList = new ArrayList<>();
	}
	
	protected int getIndex(String u) {
		for (int i=0;i<Vertices.size(); i++) {
			if (Vertices.get(i).equals(u))
				return i;
		}
		return -1;

	}
	
	public void insertVertex(String v) {
		if (!Vertices.contains(v)) {
			Vertices.add(v);
			adjacentList.add(new LinkedList<Node>());
		}
	}
	
	public void deleteVertex(String v) {
		int vi=getIndex(v); // Vertices.indexOf(v)
		if (vi>=0) {
			for (int i=0;i<Vertices.size();i++) {
				deleteEdge(v, Vertices.get(i));
				deleteEdge(Vertices.get(i), v);
			}
			adjacentList.remove(vi);
			Vertices.remove(vi);
		}
	}
	
	public void insertEdge(String u, String v, int dist) {
		insertVertex(u);
		insertVertex(v);
		int ui=getIndex(u); 
		
		adjacentList.get(ui).add(new Node(v, dist));
	}
	
	public void deleteEdge(String u, String v) {
		int ui=getIndex(u); 
		int vi=getIndex(v); 

		if (ui>=0 && vi>=0) {
			adjacentList.get(ui).remove(new Node(v, 0));
		}
	}
	
	public boolean isEmpty() {
		return Vertices.isEmpty();
	}
	
	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = getIndex(v);
		for (int i=0;i<adjacentList.get(vi).size();i++) {
				retSet.add(adjacentList.get(vi).get(i).key);
		}
		return retSet;
	}
	
	public void showGraph() {
		for (int i=0; i<Vertices.size();i++) {
			System.out.print(Vertices.get(i) );

			for (int j=0;j<adjacentList.get(i).size();j++) {
				System.out.print(" => "+adjacentList.get(i).get(j).toString());
			}
			System.out.println();
		}
	}
	
	public void initVisited() {
		visited = new boolean[Vertices.size()];
		for (int i=0; i<Vertices.size();i++) 
			visited[i] = false;		
	}

	public void DFS(String v) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(v);
	}
	private void DFSRecursion(String v) {
		visited[getIndex(v)]=true;
		for(String u:adjacent(v))
			if (!visited[getIndex(u)])
				DFSRecursion(u);
		System.out.println(v+" is visited");
	
	}

	public void BFS(String v) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(v);
	}

	public void BFSIteration(String v) {
		Deque<String> que = new ArrayDeque<String>();
		visited[getIndex(v)]=true;
		System.out.println(v+" is visited ");
		que.add(v);

		while (!que.isEmpty()) {
			String u = que.poll();
			
			for (String w : adjacent(u)) {
				int wi = getIndex(w);
				if (!visited[wi]) {
					visited[wi]=true;
					System.out.println(w+" is visited ");
					que.add(w);
				}
			}
		}
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
		
		DGraphInList gm = new DGraphInList();
		
		gm.createGraph();
		
		for (int i=0; i<paths.length;i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);
		
		System.out.println(gm.adjacent("Seoul"));
		
		gm.showGraph();
		
		gm.DFS("Seoul");
		
		gm.BFS("Seoul");

	}
}
