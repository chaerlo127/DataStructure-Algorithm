package class2.wweek12;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 진출만 있을 때 시작이며, 진입만 있다면 끝. 사이클이 없는 유향 그래프
 * 그래프가 있고, 유향이라면 이를 한 줄로 나열하되, sorting 하게 나열을 해야함. 
 */

// 유향 그래프를 상속 받음. 
public class AGraphInTopological extends DGraphInList {

	// 기본적인 Iteration을 활용한 코드
	public void TPSort1() {
		String[] result = new String[Vertices.size()];
		int nOfVertices = Vertices.size();
		for (int i = 0; i < nOfVertices; i++) {
			result[i] = getNextNode(); // 끝에서 부터 지워줌, 진입만 있고 진출이 없는 것을 찾아서 넣고 삭제. 즉, 부모는 있고 자식은 없는 마지막과 같은 존재
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
					if (adjacentList.get(j).get(k).key.equals(Vertices.get(i))) nOfIncoming++; 
					// 현재 vertex 리스트의 값과 각 vertex 내의 인접 한 노드들의 값이 같다면 +1한다.
					// + 1이 없다면 자식으로 갖고 있는 연결된 진출 노드가 없는 것이다. 
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

	//getNextNode을 사용하지 않고서도 진입만있고 진출은 없는 노드를 확인 할 수 있다. -> recursion
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R) {
		visited[Vertices.indexOf(s)] = true; // 방문을 했다!
		for (String x : adjacent(s)) {
			if (visited[Vertices.indexOf(x)] == false) // 인접한 노드들이 방문을 하지 않은 경우 recursion
				dfsTS(visited, x, R);
		}

		System.out.println(s + " is added a the first");
		R.addFirst(s); // recursion의 마지막 부분으로 인식한 원소를 넣어줌. 진입은 있는데, 진출은 업는 노드를 구했으므로 이에 대해 print를 해주고 recursion이기 때문에 
		// 그 앞에 있는 노드들을 print 해줌
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
