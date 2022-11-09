package class2.wweek10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// 코드를 다시 작성하는 것이라도 코드는 수업시간 그대로 제출이 되는 것이기 때문에 주석을 달아 설명으로 대체하고자 합니다.
// list라면 vertex가 사라지고 추가되는 것을 고려할 것이다.
// delete 시, vertex의 인접하게 정의되어 있는 노드를 저장한 edge를 지워야 한다. -> 까다로워진다. 
public class GraphInMatrix {  // Vertices # is fixed, Undirected Graph in Matrix
	
	String [] Vertices ;
	int[][] Edges;
	
	boolean [] visited ; // dfs, bfs 할 때 사용
	
	public void createGraph(String [] input) {
		Vertices=input;
		Edges = new int[Vertices.length][Vertices.length];
		visited = new boolean[Vertices.length];
	}
	
	// vertex는 동적으로 추가가 되지 않아서, 사용할 필요가 없음. 
	public void insertVertex(String v) {
		
	}
	public void deleteVertex(String v) {
		
	}
	
	// vertex의 이름을 정의했는데, 2차원 array라서 index로 해줘야 한다.
	private int getIndex(String u) {
		for (int i=0;i<Vertices.length;i++) {
			if (Vertices[i].equals(u))
				return i;
		}
		return -1; // Not Found!
	}

	// 서울에서 대전으로 가는데, 거리리가 140이라는 것을 받아서
	// 미리 만든 matrix에 저장한다. 
	// 방향이 없다고 가정했기 때문에 두 번 저장한다. 
	public void insertEdge(String u, String v, int dist) {
		int ui = getIndex(u);
		int vi = getIndex(v);
		
		Edges[ui][vi]=dist;
		Edges[vi][ui]=dist;
	}
	
	public void deleteEdge(String u, String v) {
		int ui = getIndex(u);
		int vi = getIndex(v);
		
		Edges[ui][vi]=0;
		Edges[vi][ui]=0;		
	}
	
	// 도시가 사라지지 않으니까 의미가 없음.
	public boolean isEmpty() {
		return false;
	}
	
	// 추상 자료형에서 set으로 표현 
	// adjacent 인접한 놈의 string 들의 집합을 return을 해보자
	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = getIndex(v);
		for (int i=0;i<Vertices.length;i++) {
			if (Edges[vi][i]!=0) // seoul이라면 seoul과 연결되어 있는 값을 찾아서 
				retSet.add(Vertices[i]); // set에 넣어준다.
		}
		return retSet;
	}
	
//	public int adjacent(String v) {
//		// 2차원 array의 row를 return 
//		int vi = getIndex(v);
//		return Edges[vi];
//	}
	
	public void showGraph() {
		for (int i=0; i<Vertices.length;i++) {
			for (int j=0;j<Vertices.length;j++) {
				String temp = "----------";
				if (Edges[i][j]!=0)
					temp=Vertices[j];
				System.out.printf("%10s ",temp);
			}
			System.out.println();
		}
	}
	
	// 대칭을 표현한다.
	// 방향이 없는 것을 확인할 수 있는 graph 보여주기
	// 실제 거리를 return
	public void showGraph2() {
		for (int i=0; i<Vertices.length;i++) {
			for (int j=0;j<Vertices.length;j++) {
				System.out.printf("%10d ", Edges[i][j]);
			}
			System.out.println();
		}
	}
	
	public void initVisited() {
		for (int i=0; i<Vertices.length;i++) 
			visited[i] = false;
	}

	public void DFS(String v) {
		initVisited(); // initialize
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(v);
	}
	
	private void DFSRecursion(String v) {
		visited[getIndex(v)]=true;
		for (String u : adjacent(v)) // adjacent를 set으로 저장해둠. set이 return 됨. for loop을 부른다.
			if (!visited[getIndex(u)])
				DFSRecursion(u);
		System.out.println(v+" is visited ");
	}

	public void BFS(String v) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(v);
	}

	// level order algo 그대로
	// 통상 iteration 으로 쓰는게 자연스러움
	// 공부좀 하자
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
		
		GraphInMatrix gm = new GraphInMatrix();
		
		gm.createGraph(cities); // cities는 fixed 되어 있어 그냥 cities만 전달하면 됨.
		
		for (int i=0; i<paths.length;i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);  // 값을 넣어주기
		
		System.out.println(gm.adjacent("Seoul")); // seoul에 인접한 것을 확인해줘라 
		
		gm.showGraph();
		gm.showGraph2();
		
		gm.DFS("Seoul");
		
		gm.BFS("Seoul");

	}

}
