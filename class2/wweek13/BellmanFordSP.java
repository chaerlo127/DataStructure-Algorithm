package class2.wweek13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;


public class BellmanFordSP extends DGraphInList {

	public class EdgeElement{
		public String from;
		public String to;
		int weight;
		
		public EdgeElement(String u, String v, int w) {
			from = u;
			to = v ;
			weight = w;
		}
		
		public String toSString() {
			return from+"->"+to+"("+weight+") ";
		}
	}
	
		int [] d  ;   // distance; 
		int r = -1;   // start node
		HashSet<String> S, V ;

		String [] prev;
		
		ArrayList<EdgeElement> edgeList ;
		

		public BellmanFordSP() {
			edgeList = new ArrayList<>();
		}
		
		public void insertEdge(String from, String to, int w) {
			insertVertex(from);
			insertVertex(to);

			int f = Vertices.indexOf(from);
			
			adjacentList.get(f).add(new Node(to, w));
			edgeList.add(new EdgeElement(from, to, w));
		}
		
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
		
		public void ShortestPath() {

			for (int i=0; i<Vertices.size()-1; i++) {  // N-1 times
				System.out.println(">>> "+i+" -th iteration");
				for (EdgeElement e : edgeList) {  // for all edges
					String u = e.from;
					String v = e.to;
					int wuv = getWeight(u, v);
					int dv = d[Vertices.indexOf(v)];
					int du = d[Vertices.indexOf(u)];
					
					if ((du+wuv)<dv) {
						System.out.println("   "+v+"  "+dv+" ---> "+(du+wuv));
						d[Vertices.indexOf(v)] = du+wuv ;
						prev[Vertices.indexOf(v)] = u;
					}
				}
			}
			
			for (EdgeElement e : edgeList) {  // for all edges
				String u = e.from;
				String v = e.to;
				int wuv = getWeight(u, v);
				int dv = d[Vertices.indexOf(v)];
				int du = d[Vertices.indexOf(u)];
				
				if ((du+wuv)<dv) {
					System.out.println("음의 싸이클 발견 => 해없음  ");
				}
			}
		}
		
		public void showShortestPath() {
			System.out.println("\n< Bellman-Ford Shortest Path(s) >");
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
		
		public static void main(String[] args) {
			String [] vertices = { "서울", "인천", "대전", "대구", "광주", "부산", "울산"};
			int [][] graphEdges = { {0, 1, 11 }, {0, 2, 8}, {0, 3, 9}, {1, 3, 13}, 
					{1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7},
					{3, 2, -3} };

			BellmanFordSP myG = new BellmanFordSP();

			myG.createGraph();
			for (int i = 0; i<graphEdges.length; i++)
				myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
			myG.showGraph();

			System.out.println("\nBellmanFord Shortest Path Algorithm ");
			myG.init("서울");
			myG.ShortestPath();
			myG.showShortestPath();
			
			int [][] graphEdges2 = { {0, 1, 11 }, {2, 0, -8}, {0, 3, 9}, {1, 3, 13}, 
					{1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7},
					{3, 2, -3} };

			BellmanFordSP myG2 = new BellmanFordSP();

			myG2.createGraph();
			for (int i = 0; i<graphEdges2.length; i++)
				myG2.insertEdge(vertices[graphEdges2[i][0]],vertices[graphEdges2[i][1]], graphEdges2[i][2]);
			myG2.showGraph();

			System.out.println("\nBellmanFord Shortest Path Algorithm ");
			myG2.init("서울");
			myG2.ShortestPath();
			myG2.showShortestPath();
		}
	}

