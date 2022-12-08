package class2.wweek12;

import java.util.Arrays;
import java.util.HashSet;

/* MST (최소 신장 트리)
 * 1. 사이클이 있으면 안됨
 * 2. Edge의 합이 최소, 모든 edge는 가중치를 갖고 있기 때문
 * 3. Greedy 알고리즘
 * 4. 무향 그래프
 * 5. 모두 연결이 되어있어야 함.
 */
// 현재 GraphInList를 상속 받아서 사용
// [인접한 것]들의 최소 값을 찾아서 한다. 
public class GraphInListPrim extends GraphInList {

	HashSet<String> V, S;
	int[] d;

	public void MSTPrim(String start) {
		V = new HashSet<String>(); // vertex 복붙
		S = new HashSet<String>(); // 도착한 vertex 저장
		d = new int[Vertices.size()]; // vertex 내부에 가중치 저장할 공간
		for (String s : Vertices)
			V.add(s); // Vertices의 값을 저장
		Arrays.fill(d, 9999); // d에 무한 값 미리 넣기
		int r = Vertices.indexOf(start); // 처음 시작하는 곳에 가중치를 0으로 저장함.
		d[r] = 0;

		Prim();
	}

	public void Prim() {

		System.out.println("\n[Minimal Spaning Tree : Prim]\n");

		while (S.size() < Vertices.size()) { // node의 값이 n-1일 때까지만 진행
			System.out.println("Set S : " + S);

			String u = extractMin(diff(V, S)); // diff(V,S) == V-S
			// 이미 이동한 가장 작은 값을 제외하고, 다시 adjacentList 중 가장 작은 값 return
			S.add(u);
			System.out.println(">>> " + u + " is selected.");
			for (String v : adjacent(u)) { // L(u) == adjacent(u)
				// u와 인접한 모든 node를 확인해서
				HashSet<String> temp = diff(V, S);
				int wuv = getWeight(u, v); // 현재 자신과 인접한 모든 것들을 비교 (u와 v를 비교)
				int dv = d[Vertices.indexOf(v)]; // 현재 vertices의 가중치, for문을 돌고 잇는 인접한 곳의 가중치 
				if (temp.contains(v) && wuv < dv)
					// vertex의 집합 중 이미 지나간 vertex를 지운 것들 중에서 v가 vertex 집합에 아직 포함이 되고, 
					// 이미 저장된 가중치보다 새로운 길이의 가중치가 더 적은 경우에는
					d[Vertices.indexOf(v)] = wuv; // 값을 바꾼다.
			}
		}
		System.out.println("Set S [Final] : " + S);
	}

	private int getWeight(String u, String v) {
		int ui = getIndex(u);
		MyLinkedList3<Node> aList = adjacentList.get(ui);

		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i).key.equals(v)) // 서로 인접한 게 맞다면
				return aList.get(i).num; // 이어진 사이 edge의 값을 return 해줌.
		}
		return -1;
	}

	// s2와 s1이 모두 저장된 공집합이 있으면 지움.
	// 즉, vertices에도 저장이 되어 있으며 도착한 Vertex 또한 저장이 된 경우 vertices에 값을 지운다.
	private HashSet<String> diff(HashSet<String> s1, HashSet<String> s2) {
		HashSet<String> result = s1;
		for (String s : s2)
			result.remove(s); // result에 저장된 s2의 연결 값들을 빼줌.
		return result;
	}

	// vertices - 도착한 vertices에서 하나하나 다 살펴보며, 이미 다녀간 edge 중에
	private String extractMin(HashSet<String> diff) {
		String minVertex = null;
		int min = 9999;
		for (String s : diff) {
			if (d[Vertices.indexOf(s)] < min) { // 이미 도착한 값을 제외하고 현재 작은 값보다 vertex 의 값이 더 작다면
				minVertex = s;
				min = d[Vertices.indexOf(s)]; // 값을 변경
			}
		}
		return minVertex;
	}

	public static void main(String[] args) {

		String[] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan", "Mokpo", "Chuncheon",
				"Kyeongju" };
		int[][] paths = { { 0, 1, 59 }, { 0, 2, 140 }, { 0, 3, 237 }, { 0, 7, 313 }, { 0, 8, 75 }, { 1, 7, 295 },
				{ 1, 8, 105 }, { 2, 3, 122 }, { 2, 4, 141 }, { 3, 4, 173 }, { 3, 5, 88 }, { 3, 6, 74 }, { 3, 8, 236 },
				{ 4, 5, 202 }, { 4, 7, 57 }, { 5, 9, 76 }, { 6, 8, 293 }, { 6, 9, 36 } };

		GraphInListPrim gm = new GraphInListPrim();

		gm.createGraph();

		for (int i = 0; i < paths.length; i++)
			gm.insertEdge(cities[paths[i][0]], cities[paths[i][1]], paths[i][2]);

		System.out.println(gm.adjacent("Seoul"));

		gm.showGraph();

		gm.MSTPrim("Seoul");

	}
}
