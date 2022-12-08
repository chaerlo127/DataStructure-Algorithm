package class2.wweek12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * 1. Greedy 알고리즘
 * 2. 그래프 간선을 오름차순으로 정렬
 * 3. 간선의 가중치가 적은 순으로 집합을 생성 (단, 사이클이 생성되면 안됨)
 * 4. MST(최소 신장 트리) 임.
 *  */

//현재 GraphInList를 상속 받아서 사용
// 가중치를 확인하여 인접하지 않더라도, 가장 작은 가중치를 가지고 있는 노드들의 집합끼리 묶어서 저장하고 이를 합치는 과정으로 최소 신장 트리 개념을 적용한다.
public class GraphInListKruscal extends GraphInList {

	public class EdgeElement {
		String from;
		String to;
		int weight;

		public EdgeElement(String u, String v, int w) {
			from = u;
			to = v;
			weight = w;
		}

		public String toString() {
			return from + "->" + to + "(" + weight + ") ";
		}
	}

	ArrayList<String> parent;
	HashSet<EdgeElement> T;
	LinkedList<EdgeElement> Q;

	public GraphInListKruscal() {
		parent = new ArrayList<>();
		Q = new LinkedList<EdgeElement>();
		T = new HashSet<>();
	}

	// vertices를 복제
	public void initKruscal() {
		for (int i = 0; i < Vertices.size(); i++) {
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

		sortInsert(new EdgeElement(u, v, dist)); // 기존의 것에서 sorting 추가
	}

	
	// 이미 insert edge 할 때부터 정렬을 해서 보여줌.
	// 가중치가 적은 노드끼리 먼저 집합을 생성해야 하기 때문
	private void sortInsert(EdgeElement newEdge) {
		int index = 0;
		Iterator<EdgeElement> iter = Q.iterator();
		while (iter.hasNext()) {
			if (newEdge.weight > iter.next().weight)
				index++;
		}
		Q.add(index, newEdge);
		showQ();
	}

	private void showQ() {
		System.out.print("\n>>> Q state : ");
		for (EdgeElement e : Q) {
			System.out.print("-> " + e.weight);
		}
		System.out.println();

	}

	public void MSTKruscal() {
		initKruscal(); // 자신 하나를 갖고 있는 노드를 생성한다. 
		System.out.println("\n[Minimal Spaning Tree : Kruscal]\n");

		while (T.size() < Vertices.size() - 1) { // 최소 신장 트리
			// 넘어서면 graph가 사이클을 갖게 됨.
			EdgeElement euv = Q.remove(0); // 첫 번째 것을 지우고 return

			if (findSet(euv.from) != findSet(euv.to)) { // 집합의 처음과 끝이 같지 않다면 (처음과 끝이 내가 아니라면) 즉, 사이클이 발생하지 않는다면?
				union(euv.from, euv.to); // 합침
				System.out.println(euv + "  is selected");
				T.add(euv);
			}
		}

		System.out.println("\nSet T [Final] : " + T);

	}

	// 노드들의 집합을 확인하여, 원소를 갖고 있는 것의 문자 값 중 갖아 앞에 있는 부모를 찾아냄. 
	private String findSet(String s) {
		String p = parent.get(getIndex(s));
		if (p == s)
			return s;
		else
			return findSet(p);
	}

	private void union(String s, String d) {
		parent.set(Vertices.indexOf(d), parent.get(Vertices.indexOf(s)));
	}

	public static void main(String[] args) {

		String[] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan", "Mokpo", "Chuncheon",
				"Kyeongju" };
		int[][] paths = { { 0, 1, 59 }, { 0, 2, 140 }, { 0, 3, 237 }, { 0, 7, 313 }, { 0, 8, 75 }, { 1, 7, 295 },
				{ 1, 8, 105 }, { 2, 3, 122 }, { 2, 4, 141 }, { 3, 4, 173 }, { 3, 5, 88 }, { 3, 6, 74 }, { 3, 8, 236 },
				{ 4, 5, 202 }, { 4, 7, 57 }, { 5, 9, 76 }, { 6, 8, 293 }, { 6, 9, 36 } };

		GraphInListKruscal gm = new GraphInListKruscal();

		gm.createGraph();

		for (int i = 0; i < paths.length; i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

		System.out.println(gm.adjacent("Seoul"));

		gm.showGraph();

		gm.MSTKruscal();

	}
}
