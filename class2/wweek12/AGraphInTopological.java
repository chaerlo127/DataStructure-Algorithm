package class2.wweek12;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * ÁøÃâ¸¸ ÀÖÀ» ¶§ ½ÃÀÛÀÌ¸ç, ÁøÀÔ¸¸ ÀÖ´Ù¸é ³¡. »çÀÌÅ¬ÀÌ ¾ø´Â À¯Çâ ±×·¡ÇÁ
 *
 */

// À¯Çâ ±×·¡ÇÁ¸¦ »ó¼Ó ¹ŞÀ½. 
public class AGraphInTopological extends DGraphInList {

	public void TPSort1() {
		String[] result = new String[Vertices.size()];
		int nOfVertices = Vertices.size();
		for (int i = 0; i < nOfVertices; i++) {
			result[i] = getNextNode(); // ³¡¿¡¼­ ºÎÅÍ Áö¿öÁÜ, ÁøÀÔ¸¸ ÀÖ°í ÁøÃâÀÌ ¾ø´Â °ÍÀ» Ã£¾Æ¼­ ³Ö°í »èÁ¦. 
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
					if (adjacentList.get(j).get(k).key.equals(Vertices.get(i))) nOfIncoming++; // ÀÌ¿ôÇÑ node¿¡ ÀúÀåµÈ vertex¸¦ ÇÏ³ª ¾¿ »ìÆìºÁ¼­ °ªÀÌ °°À¸¸é +1ÇÑ´Ù.
				}
			}

			if (nOfIncoming == 0) { // ³» µÚ¿¡ ¾Æ¹«µµ ¾ø´Ù¸é? Áï, Áø­yÀÌ ¾ø´Ù¸é?
				return Vertices.get(i); // °ªÀ» return ÇØÁà¶ó
			}
		}
		return null;
	}

	// dfs¸¦ ÀÌ¿ëÇÑ topological
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[Vertices.size()];
		Arrays.fill(visited, false); // ¸ğµÎ ´Ù false·Î ¼³Á¤

		for (String s : Vertices) {
			if (visited[Vertices.indexOf(s)] == false) // ¹æ¹®ÇÏÁö ¾Ê¾ÒÀ¸¸é
				dfsTS(visited, s, R); // recursion, vertex¸¦ ÇÏ³ªÇÏ³ª »ìÇÇ¸é¼­ È®ÀÎ
		}
	}

	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R) {
		visited[Vertices.indexOf(s)] = true;
		for (String x : adjacent(s)) {
			if (visited[Vertices.indexOf(x)] == false)
				dfsTS(visited, x, R);
		}

		System.out.println(s + " is added a the first");
		R.addFirst(s); // recursionÀÇ ¸¶Áö¸· ºÎºĞÀ¸·Î ÀÎ½ÄÇÑ ¿ø¼Ò¸¦ ³Ö¾îÁÜ. 
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
