package class2.wweek12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

	public class GraphInListKruscal extends GraphInList  {

		public class EdgeElement{
			String from;
			String to;
			int weight;
			
			public EdgeElement(String u, String v, int w) {
				from = u;
				to = v ;
				weight = w;
			}
			
			public String toString() {
				return from+"->"+to+"("+weight+") ";
			}
		}
		
		ArrayList<String> parent;
		HashSet<EdgeElement> T ;
		LinkedList<EdgeElement> Q;

		public GraphInListKruscal() {
			parent = new ArrayList<>() ;
			Q = new LinkedList<EdgeElement>();
			T = new HashSet<>();
		}	
		
		public void initKruscal() {
			for (int i=0; i<Vertices.size();i++) {
				parent.add(Vertices.get(i)); // MakeSet
			}
		}
		
		// overriding
		public void insertEdge(String u, String v, int dist) {
			insertVertex(u);
			insertVertex(v);

			int ui = Vertices.indexOf(u);
			int vi = Vertices.indexOf(v);

			adjacentList.get(ui).add(new Node(v, dist));
			adjacentList.get(vi).add(new Node(u, dist));

			sortInsert(new EdgeElement(u, v, dist));
		}	

		private void sortInsert(EdgeElement newEdge) {
			int index=0;
			Iterator<EdgeElement> iter = Q.iterator();
			while (iter.hasNext()) {
				if (newEdge.weight>iter.next().weight) 
					index++; 
			}
			Q.add(index,newEdge);
			showQ();
		}

		private void showQ() {
			System.out.print("\n>>> Q state : ");
			for (EdgeElement e : Q) {
				System.out.print("-> "+e.weight);
			}
			System.out.println();

		}

		public void MSTKruscal() {
			initKruscal();
			System.out.println("\n[Minimal Spaning Tree : Kruscal]\n");

			while(T.size()<Vertices.size()-1) {
				EdgeElement euv = Q.remove(0);

				if (findSet(euv.from)!=findSet(euv.to)) {
					union(euv.from, euv.to);
					System.out.println(euv+"  is selected");
					T.add(euv);
				}
			}
			
			System.out.println("\nSet T [Final] : "+T);

		}
		
		private String findSet(String s) {
			String p= parent.get(getIndex(s));
			if (p==s) 
				return s;
			else 
				return findSet(p);
		}
		
		private void union(String s, String d) {
			parent.set(Vertices.indexOf(d), parent.get(Vertices.indexOf(s)));
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
			
			GraphInListKruscal gm = new GraphInListKruscal();
			
			gm.createGraph();
			
			for (int i=0; i<paths.length;i++)
				gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);
			
			System.out.println(gm.adjacent("Seoul"));
			
			gm.showGraph();
			
			gm.MSTKruscal();
			
		}
	}
