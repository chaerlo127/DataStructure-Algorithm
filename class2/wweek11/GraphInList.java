package class2.wweek11;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphInList {// Vertices # is fixed, Undirected Graph in List
	
	public class Node {
		String key;
		int num ;  
		
		public Node (String k, int n) {
				key =k;
				num=n;
		}

		public boolean equals(Node other) {
			return this.key.equals(other.key);
		}
		
		// this가 크면 1, this가 작으면 -1
		public int compareTo(Node other) {    
			return this.key.compareTo(other.key);
		}
		
		public String toString() {
			return key+"("+num+") ";
		}
	}
	
	// define Vertices, adjacentList, visited
	ArrayList<String> Vertices;
	ArrayList<LinkedList<Node>> adjacentList;
	boolean [] visited;
		
	public void createGraph() {
		 Vertices = new ArrayList<String>();
		 adjacentList = new ArrayList<LinkedList<Node>>();
	}
	
	private int getIndex(String u) {
		for(int i = 0; i<Vertices.size(); i++) {
			if(Vertices.get(i).equals(u)) return i;
		}
		return -1;
	}
	
	 // 새로운 것이면 추가해주고 아니면 무시해주도록
	public void insertVertex(String v) {
		if(!Vertices.contains(v)) {
			Vertices.add(v);
			adjacentList.add(new LinkedList<Node>());
		}
	}
	
	/*
	 * 추가적
	 * 3의 linkedlist를 보면서 linkedlist에 1이 포함되어 있으면 1한테 가서 3지워라, 2가 포함되어 있으면 2한테가서 3지워라
	 * */
	public void deleteVertex(String v) {
		int vi = getIndex(v); // Vertices.indexOf(v)
		if(vi >= 0) {
			for(int i = 0; i<Vertices.size(); i++) {
				// 대전과 서울이 연결되어 있다면
				// 대전의 linkedList에는 서울 연결, 서울의 linkedList에는 대전 연결 되어 있음.
				deleteEdge(v, Vertices.get(i)); // v에서 다른 놈들로 가는 것임. v가 대전이라면, 대전에서 모든 도시를 다 확인 후 저장되어 있다면 다 지워라 
				deleteEdge(Vertices.get(i), v);
			}
			adjacentList.remove(vi);
			Vertices.remove(vi);
		}
	}
	

	public void insertEdge(String u, String v, int dist) {
		insertVertex(u); // 없었다면 생성하고 있었다면 무시
		insertVertex(v);
		int ui = getIndex(u);
		int vi = getIndex(v);
		
		// v linkedList에 u, distance 저장, u linkedList에 v, distance 저장.
		adjacentList.get(ui).add(new Node(v, dist));
		adjacentList.get(vi).add(new Node(u, dist));
	}
	
	public void deleteEdge(String u, String v) {
		int ui = getIndex(u);
		int vi = getIndex(v);
		
		if(ui>=0 && vi>=0) {
			adjacentList.get(ui).remove(new Node(v, 0));
			adjacentList.get(vi).remove(new Node(u, 0));
		}
	}
	
	public boolean isEmpty() {
		return Vertices.isEmpty();
		
	}
	
	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = getIndex(v);
		for (int i = 0; i < adjacentList.get(vi).size(); i++) {
			retSet.add(adjacentList.get(vi).get(i).key); // set에 넣어준다.
		}
		return retSet;		
	}
	
	public void showGraph() {
		for (int i=0; i<Vertices.size();i++) {
			System.out.print(Vertices.get(i));
			for(int j = 0; j<adjacentList.get(i).size(); j++) {
				System.out.print(" => " + adjacentList.get(i).get(j).toString());
			}
			System.out.println();
		}
	}
	

	public void initVisited() {
		visited = new boolean[Vertices.size()];
		for(int i = 0; i<Vertices.size(); i++) visited[i] = false;
	}

	public void DFS(String v) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(v);
	}
	
	private void DFSRecursion(String v) {
		visited[getIndex(v)] = true;
		for(String u:adjacent(v)) { // 인접한 모든 String u에 대해서 거쳐 가지 않은 것이라면
			if(!visited[getIndex(u)]) DFSRecursion(u);
		}
		System.out.println(v + " is visited"); // 어떤 작업을 한다. 내 차례가 왔다. 
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
		
		GraphInList gm = new GraphInList();
		
		gm.createGraph();
		
		for (int i=0; i<paths.length;i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]); 
		
		System.out.println(gm.adjacent("Seoul"));
		
		gm.showGraph();
		
		gm.DFS("Seoul");
		
		gm.BFS("Seoul");

	}
}
