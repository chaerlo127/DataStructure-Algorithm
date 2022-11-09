package class2.wweek10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// �ڵ带 �ٽ� �ۼ��ϴ� ���̶� �ڵ�� �����ð� �״�� ������ �Ǵ� ���̱� ������ �ּ��� �޾� �������� ��ü�ϰ��� �մϴ�.
// list��� vertex�� ������� �߰��Ǵ� ���� ����� ���̴�.
// delete ��, vertex�� �����ϰ� ���ǵǾ� �ִ� ��带 ������ edge�� ������ �Ѵ�. -> ��ٷο�����. 
public class GraphInMatrix {  // Vertices # is fixed, Undirected Graph in Matrix
	
	String [] Vertices ;
	int[][] Edges;
	
	boolean [] visited ; // dfs, bfs �� �� ���
	
	public void createGraph(String [] input) {
		Vertices=input;
		Edges = new int[Vertices.length][Vertices.length];
		visited = new boolean[Vertices.length];
	}
	
	// vertex�� �������� �߰��� ���� �ʾƼ�, ����� �ʿ䰡 ����. 
	public void insertVertex(String v) {
		
	}
	public void deleteVertex(String v) {
		
	}
	
	// vertex�� �̸��� �����ߴµ�, 2���� array�� index�� ����� �Ѵ�.
	private int getIndex(String u) {
		for (int i=0;i<Vertices.length;i++) {
			if (Vertices[i].equals(u))
				return i;
		}
		return -1; // Not Found!
	}

	// ���￡�� �������� ���µ�, �Ÿ����� 140�̶�� ���� �޾Ƽ�
	// �̸� ���� matrix�� �����Ѵ�. 
	// ������ ���ٰ� �����߱� ������ �� �� �����Ѵ�. 
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
	
	// ���ð� ������� �����ϱ� �ǹ̰� ����.
	public boolean isEmpty() {
		return false;
	}
	
	// �߻� �ڷ������� set���� ǥ�� 
	// adjacent ������ ���� string ���� ������ return�� �غ���
	public Set<String> adjacent(String v){
		Set<String> retSet = new HashSet<String>();
		int vi = getIndex(v);
		for (int i=0;i<Vertices.length;i++) {
			if (Edges[vi][i]!=0) // seoul�̶�� seoul�� ����Ǿ� �ִ� ���� ã�Ƽ� 
				retSet.add(Vertices[i]); // set�� �־��ش�.
		}
		return retSet;
	}
	
//	public int adjacent(String v) {
//		// 2���� array�� row�� return 
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
	
	// ��Ī�� ǥ���Ѵ�.
	// ������ ���� ���� Ȯ���� �� �ִ� graph �����ֱ�
	// ���� �Ÿ��� return
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
		for (String u : adjacent(v)) // adjacent�� set���� �����ص�. set�� return ��. for loop�� �θ���.
			if (!visited[getIndex(u)])
				DFSRecursion(u);
		System.out.println(v+" is visited ");
	}

	public void BFS(String v) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(v);
	}

	// level order algo �״��
	// ��� iteration ���� ���°� �ڿ�������
	// ������ ����
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
		
		gm.createGraph(cities); // cities�� fixed �Ǿ� �־� �׳� cities�� �����ϸ� ��.
		
		for (int i=0; i<paths.length;i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);  // ���� �־��ֱ�
		
		System.out.println(gm.adjacent("Seoul")); // seoul�� ������ ���� Ȯ������� 
		
		gm.showGraph();
		gm.showGraph2();
		
		gm.DFS("Seoul");
		
		gm.BFS("Seoul");

	}

}
