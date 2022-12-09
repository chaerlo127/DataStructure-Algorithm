package class2.wweek15;

import java.util.ArrayList;
import java.util.Arrays;

public class FinalExam_60201976 extends DGraphInList {
	int [] d  ;   // distance; 
	int r = -1;   // start node
	String [] prev;
	boolean []  visited;
	
	boolean [] included;
	int [][] adjacentMatrix;
	
	int maxNumber = 0;
	ArrayList<SCNode> newG;
	
	public void no1Init() {
		adjacentMatrix = new int[adjacentList.size()][adjacentList.size()];
		for (int i=0;i<adjacentList.size();i++)
			Arrays.fill(adjacentMatrix[i], 0);
	}
/////////////////////////////////////////////////////////////////////////////////////		
//  No.1 :  Adjacent Matrix Converted from Adjacent List
/////////////////////////////////////////////////////////////////////////////////////
	public void convertToMatrix() {
		// 두 번의 확인 과정을 거쳐야 하기 때문에 for문을 2번 돌림
		for(int i =0; i<adjacentList.size(); i++) { // 각 node에 저장되어 있는 값 확인
			for(int j = 0; j<adjacentList.get(i).size(); j++) { // node와 인접한 갑 확인
				int index = Vertices.indexOf(adjacentList.get(i).get(j).key); 
				// node와 인접 한 값의 index 확인 -> matrix의 인덱스 위치를 파악하기 위해
				adjacentMatrix[i][index] = adjacentList.get(i).get(j).num;
				// matrix에 인접한 결과를 넣어줌.
			}
		}
////////////////////////////////////////////////////////////////////////////////////		
		for (int i=0; i<Vertices.size();i++) {
			for (int j=0;j<Vertices.size();j++) 
					System.out.printf("%12d ", adjacentMatrix[i][j]);
			System.out.println();
		}
	}
////////////////////////////////////////////////////////////////////////////////////

	public void no2Init(int[][] reset) {
		adjacentMatrix=reset;
		visited = new boolean[Vertices.size()];
		for (int i=0; i<Vertices.size();i++) 
			visited[i] = false;		
	}
/////////////////////////////////////////////////////////////////////////////////////		
//  No.2 :  Depth First Search 
//  - Graph in Matrix 
//  - Do not use Set, adjacent method.. 
/////////////////////////////////////////////////////////////////////////////////////	
// 2-1	
	public void DFS(String v) {
		visited[getIndex(v)] = true;
		for(String u:adjacent(v)) { // 인접한 모든 String u에 대해서 
			if(!visited[getIndex(u)]) {
				DFS(u); // 거쳐 가지 않은 것이라면 그 곳으로 들어가 같은 방법을 반복함
			}
			// 마지막인 Node라면 다시 recursion을 통해서, return 을 계속 거치면서 계산을 수행함.
		}
		System.out.println(v + " is visited"); // 어떤 작업을 한다. 내 차례가 왔다. 
	}

// 2-2 ==> explanation!

////////////////////////////////////////////////////////////////////////////////////
	
	public void no3Init(String start) {
		maxNumber =Vertices.size();
		d = new int [maxNumber];
		included = new boolean [maxNumber];
	    
		prev = new String[maxNumber];
		
		Arrays.fill(d, 9999);
		Arrays.fill(included, false);
		r = Vertices.indexOf(start);
		
		d[r]=0;
	}
/////////////////////////////////////////////////////////////////////////////////////		
//  No.3 :  Dijkstra Shortest Path Algorithm 
//  - Graph in Matrix 
//  - Use include[] instead of adjacent set... 
/////////////////////////////////////////////////////////////////////////////////////	
// 3-1
	public void shortestPath() {
		// S라는 set 대신에 boolean index를 사용한다.
		for(int i = 0; i<Vertices.size()-1; i++) { // vertex 보다 하나 작게 
			int min = 9999; // 최소 값
			int nodeIdx = 0; // 노드의 인덱스
			for(int j = 0; j < Vertices.size(); j++) { // 값을 보고
				if (!visited[j] && d[j] < min) { // 방문하지 않았고, d의 값이 최소보다 작다면
					min = d[j]; // 최소를 변경하고
					nodeIdx = j; // 노드의 index 수를 저장
				}
				visited[nodeIdx] = true; // 방문한 것으로 변경 
			}
			// 최소의 개수 구해서 더하는 로직 수행
			for (int j = 0; j < adjacentList.get(nodeIdx).size(); j++) {
				int num = adjacentList.get(nodeIdx).get(j).num;
				int index = Vertices.indexOf(adjacentList.get(nodeIdx).get(j).key);
				if(d[index] > d[nodeIdx] + num) {
					d[index] = d[nodeIdx] + num;
				}
			}
			initVisited();
		}
	}
// 3-2 ==> explanation!
	
/////////////////////////////////////////////////////////////////////////////////////	
	
	private String extractMin() {
		String minVertex = null;
		int min = 9999;;
		for (int i=0; i<Vertices.size();i++)
			if (included[i]==false && d[i] < min) {
				minVertex = Vertices.get(i);
				min = d[i];
			}
		return minVertex;
	}
	
	public void showShortestPath() {
		System.out.println("\n< Shortest Path(s) : Dijkstra >");
		System.out.println("\nStart Node = "+Vertices.get(0));

		for (int i=1; i<Vertices.size();i++){
			System.out.print(prev[i] +"  ");
			System.out.print(" => "+ Vertices.get(i)+"("+d[i]+")");
			System.out.println();
		}
	}
	
	
////////////////////////////////////////////////////////////////////////////////////
//  No.4 :  Graph in list of SCNode 
//  - adjacentMatrix --> ArrayList<SCNode>
////////////////////////////////////////////////////////////////////////////////////

	public void no4Init() {
		newG = new ArrayList<>();
	}	
	private int indexNewG(String s) {
		for (int i=0;i<newG.size();i++) {
			if (newG.get(i).key.equals(s))
				return i;
		}
		return -1;
	}
	
	public void insertVertexNewG(String s) {
		if (indexNewG(s)<0) {
			newG.add(new SCNode(s, 0));
		}
	}
	
	public void insertEdgeNewG(String u, String v, int i) {
		insertVertexNewG(u);	
		int index = indexNewG(u);
		newG.get(index).addNext(new SCNode(v,i));
		newG.get(index).attr++;
	}
/////////////////////////////////////////////////////////////////////////////////////	
// 4-1	
	public void deleteVertexNewG(String u) {
		int iu = getIndex(u);
		if(iu>=0) {
			for(SCNode node: newG) { // 노드의 인접한 것을 찾아서
				if(!node.key.equals(u)) { // 자기 자신 제외하고 
					deleteEdgeNewG(node.key, u); // 지우기
				}
			}
			SCNode nodeU = newG.get(iu); // 노드 찾아서
			nodeU.next = null; // edge 지우고
			nodeU.delete(); // 자기 자신 vertex를 지움
		}
	}
/////////////////////////////////////////////////////////////////////////////////////	
// 4-2	
	public void deleteEdgeNewG(String u, String v) {
		int iu = getIndex(u); // u의 index
		
		if(iu>=0) { // u의 index를 찾았다면?
			SCNode nodeU = newG.get(iu); // u의 노드 리스트들
			while(nodeU!= null){ // u 끝까지 찾아봄
				if(nodeU.key == v) { // key의 값이 같다면
					nodeU.delete(); // 지우고
					newG.get(iu).attr--; // u의 attr의 값을 하나 줄인다.
					break; // 멈춰라
				}
				nodeU = nodeU.next; // 없다면 옆으로 넘어가면서 확인해라
			}
		}
	}
/////////////////////////////////////////////////////////////////////////////////////	
	
	public void showNewGraph() {
		for (int i=0; i<newG.size(); i++) {
			System.out.print("\n"+newG.get(i).toString()+" : ");
			SCNode p=newG.get(i).next;
			while(p!=null) {
				System.out.print(" -- "+p.toString());
				p=p.next;
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
		FinalExam_60201976 myG = new FinalExam_60201976();
		myG.createGraph();
		for (int i = 0; i<cities.length; i++)
			myG.insertVertex(cities[i]);
	
		for (int i = 0; i<paths.length; i++)
			myG.insertEdge(cities[paths[i][0]],cities[paths[i][1]], paths[i][2]);
		myG.showGraph();
		
		System.out.println("\n[ No.1 :  Adjacent Matrix Converted from Adjacent List ]\n");
		myG.no1Init();
		
		myG.convertToMatrix();

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		int [][] matrix = { {0, 59, 140,  237,  0,  0,  0,  313,  75,  0 },
								{0,  0,  0,  0,  0,  0,  0, 295, 105, 0 },
								{0,  0,  0, 122, 141, 0,  0,  0,  0,  0 },
								{0,  0,  0,  0, 173, 88, 74,  0, 236, 0 },
								{0,  0,  0,  0,  0, 202, 0, 57,  0,  0 },
								{0,  0,  0,  0,  0,  0,  0,  0,  0, 76 },
								{0,  0,  0,  0,  0,  0,  0,  0, 293, 36 },
								{0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
								{0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
								{0,  0,  0,  0,  0,  0,  0,  0,  0,  0 } 	};
		
		myG.no2Init(matrix);
		System.out.println("\n[ No.2 :  Depth First Search ]\n");

		myG.DFS("Seoul");

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[ No.3 :  Dijkstra Shortest Path Algorithm ]\n");
		
		myG.no3Init("Seoul");
		
		myG.shortestPath();
		
		myG.showShortestPath();

	
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n[ No.4 :  New Graph with SCNode ]");
		
		myG.no4Init();
		
//		myG.newGraph();
		
		for (int i=0;i<cities.length;i++)
			myG.insertVertexNewG(cities[i]);
		
		for (int i=0; i<paths.length;i++)
			myG.insertEdgeNewG(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

		myG.showNewGraph();
		
		myG.deleteEdgeNewG( "Seoul", "Incheon");
		System.out.println("\n\n>> After deleting Edge Seoul-Incheon ");
		myG.showNewGraph();

		myG.deleteVertexNewG( "Chuncheon");
		System.out.println("\n\n>> After deleting Vertex Chuncheon ");
		myG.showNewGraph();
	
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	}
}

