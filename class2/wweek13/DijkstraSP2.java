package class2.wweek13;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;


public class DijkstraSP2 extends DGraphInList {
	int [] d  ;   // distance; 
	int r = -1;   // start node
	HashSet<String> S, V ;
	
	String [] prev;

		public void init(String start) {
		d = new int [Vertices.size()];
		S = new HashSet<>();
		V = new HashSet<>();
		
		prev = new String[Vertices.size()];

		for (String s : Vertices )
			V.add(s);
		r = Vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r]=0;
	}
	
	public void shortestPath() {
		initVisited();
		System.out.println("\n *** Dijkstra BFS Iteration *** \n");
		BFSSP(Vertices.get(r));
	}

	public void BFSSP(String s) {
		Deque<String> que = new ArrayDeque<String>();
		visited[Vertices.indexOf(s)]=true;
//		System.out.println(s+" is visited ");
		que.add(s);
		
		while (!que.isEmpty()) {
			String u= que.poll();
			int iu = Vertices.indexOf(u);
			for (String v : adjacent(u))  {

				int iv = Vertices.indexOf(v);
				
				if (d[iv]>d[iu]+getWeight(u,v)) {
						d[iv]=d[iu]+getWeight(u,v);
						prev[iv]=u;
					}
//					System.out.println(v+" 's distance is updated ");
					que.add(v);
				}
			}
	}
	
	public void showShortestPath() {
		for (int i=0; i<Vertices.size(); i++)	{
			System.out.println(prev[i]+" => "+Vertices.get(i)+"("+d[i]+")");
		}
	}
	
	private int getWeight(String u, String v) {
		LinkedList<Node> list =adjacentList.get(Vertices.indexOf(u));
		for (int i=0;i<list.size();i++) {
			if (list.get(i).key.equals(v))
				return list.get(i).num;
		}
		return -1;
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

		DijkstraSP2 myG = new DijkstraSP2();

		myG.createGraph();
		for (int i = 0; i<paths.length; i++)
			myG.insertEdge(cities[paths[i][0]],cities[paths[i][1]], paths[i][2]);
		myG.showGraph();

		System.out.println("\nDijkstra Shortest Path Algorithm2 ");
		myG.init("Seoul");
		myG.shortestPath();
		myG.showShortestPath();

}
}
