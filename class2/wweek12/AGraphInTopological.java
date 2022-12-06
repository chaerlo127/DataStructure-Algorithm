package class2.wweek12;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 진출만 있을 때 시작이며, 진입만 있다면 끝. 사이클이 없는 유향 그래프
 *
 */

// 유향 그래프를 상속 받음. 
public class AGraphInTopological extends DGraphInList {

	public void TPSort1() {
		String[] result = new String[Vertices.size()];
		int nOfVertices = Vertices.size();
		for (int i = 0; i < nOfVertices; i++) {
			result[i] = getNextNode(); // 끝에서 부터 지워줌, 진입만 있고 진출이 없는 것을 찾아서 넣고 삭제. 
			deleteVertex(result[i]);
			showGraph();
		}

		System.out.println(">> Result: Start");
		for (int i = 0; i < nOfVertices; i++) {
			System.out.println("=> " + result[i]);
		}

		System.out.println();
	}

	private String getNextNode() {
		for (int i = 0; i < Vertices.size(); i++) {
			int nOfIncoming = 0;
			for (int j = 0; j < Vertices.size(); j++) {
				for (int k = 0; k < adjacentList.get(j).size(); k++) {
					if (adjacentList.get(j).get(k).key.equals(Vertices.get(i))) nOfIncoming++; // 이웃한 node에 저장된 vertex를 하나 씩 살펴봐서 값이 같으면 +1한다.
				}
			}

			if (nOfIncoming == 0) { // 내 뒤에 아무도 없다면? 즉, 진춞이 없다면?
				return Vertices.get(i); // 값을 return 해줘라
			}
		}
		return null;
	}

	// dfs를 이용한 topological
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[Vertices.size()];
		Arrays.fill(visited, false); // 모두 다 false로 설정

		for (String s : Vertices) {
			if (visited[Vertices.indexOf(s)] == false) // 방문하지 않았으면
				dfsTS(visited, s, R); // recursion, vertex를 하나하나 살피면서 확인
		}
	}

	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R) {
		visited[Vertices.indexOf(s)] = true;
		for (String x : adjacent(s)) {
			if (visited[Vertices.indexOf(x)] == false)
				dfsTS(visited, x, R);
		}

		System.out.println(s + " is added a the first");
		R.addFirst(s); // recursion의 마지막 부분으로 인식한 원소를 넣어줌. 
		return R;
	}

	public static void main(String[] args) {
		int maxNoVertex = 10;
		String[] tasks = { "water", "ignition", "openPack", "noodle", "soup", "egg" };
		int[][] graphEdges = { { 0, 1 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 4, 5 } };

		AGraphInTopological myGM = new AGraphInTopological();

		myGM.createGraph();

		for (int i = 0; i < graphEdges.length; i++) {
			myGM.insertEdge(tasks[graphEdges[i][0]], tasks[graphEdges[i][1]], 1);
		}
		myGM.showGraph();

		System.out.println("Topological Sort1: start");
		myGM.TPSort1();

		AGraphInTopological myGM2 = new AGraphInTopological();

		myGM2.createGraph();

		for (int i = 0; i < graphEdges.length; i++) {
			myGM2.insertEdge(tasks[graphEdges[i][0]], tasks[graphEdges[i][1]], 1);
		}
		myGM2.showGraph();

		System.out.println("Topological Sort2: start");
		myGM2.TPSort2();
	}

}
