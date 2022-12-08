package class2.wweek12;

import java.util.Arrays;
import java.util.HashSet;

/* MST (�ּ� ���� Ʈ��)
 * 1. ����Ŭ�� ������ �ȵ�
 * 2. Edge�� ���� �ּ�, ��� edge�� ����ġ�� ���� �ֱ� ����
 * 3. Greedy �˰���
 * 4. ���� �׷���
 * 5. ��� ������ �Ǿ��־�� ��.
 */
// ���� GraphInList�� ��� �޾Ƽ� ���
// [������ ��]���� �ּ� ���� ã�Ƽ� �Ѵ�. 
public class GraphInListPrim extends GraphInList {

	HashSet<String> V, S;
	int[] d;

	public void MSTPrim(String start) {
		V = new HashSet<String>(); // vertex ����
		S = new HashSet<String>(); // ������ vertex ����
		d = new int[Vertices.size()]; // vertex ���ο� ����ġ ������ ����
		for (String s : Vertices)
			V.add(s); // Vertices�� ���� ����
		Arrays.fill(d, 9999); // d�� ���� �� �̸� �ֱ�
		int r = Vertices.indexOf(start); // ó�� �����ϴ� ���� ����ġ�� 0���� ������.
		d[r] = 0;

		Prim();
	}

	public void Prim() {

		System.out.println("\n[Minimal Spaning Tree : Prim]\n");

		while (S.size() < Vertices.size()) { // node�� ���� n-1�� �������� ����
			System.out.println("Set S : " + S);

			String u = extractMin(diff(V, S)); // diff(V,S) == V-S
			// �̹� �̵��� ���� ���� ���� �����ϰ�, �ٽ� adjacentList �� ���� ���� �� return
			S.add(u);
			System.out.println(">>> " + u + " is selected.");
			for (String v : adjacent(u)) { // L(u) == adjacent(u)
				// u�� ������ ��� node�� Ȯ���ؼ�
				HashSet<String> temp = diff(V, S);
				int wuv = getWeight(u, v); // ���� �ڽŰ� ������ ��� �͵��� �� (u�� v�� ��)
				int dv = d[Vertices.indexOf(v)]; // ���� vertices�� ����ġ, for���� ���� �մ� ������ ���� ����ġ 
				if (temp.contains(v) && wuv < dv)
					// vertex�� ���� �� �̹� ������ vertex�� ���� �͵� �߿��� v�� vertex ���տ� ���� ������ �ǰ�, 
					// �̹� ����� ����ġ���� ���ο� ������ ����ġ�� �� ���� ��쿡��
					d[Vertices.indexOf(v)] = wuv; // ���� �ٲ۴�.
			}
		}
		System.out.println("Set S [Final] : " + S);
	}

	private int getWeight(String u, String v) {
		int ui = getIndex(u);
		MyLinkedList3<Node> aList = adjacentList.get(ui);

		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i).key.equals(v)) // ���� ������ �� �´ٸ�
				return aList.get(i).num; // �̾��� ���� edge�� ���� return ����.
		}
		return -1;
	}

	// s2�� s1�� ��� ����� �������� ������ ����.
	// ��, vertices���� ������ �Ǿ� ������ ������ Vertex ���� ������ �� ��� vertices�� ���� �����.
	private HashSet<String> diff(HashSet<String> s1, HashSet<String> s2) {
		HashSet<String> result = s1;
		for (String s : s2)
			result.remove(s); // result�� ����� s2�� ���� ������ ����.
		return result;
	}

	// vertices - ������ vertices���� �ϳ��ϳ� �� ���캸��, �̹� �ٳణ edge �߿�
	private String extractMin(HashSet<String> diff) {
		String minVertex = null;
		int min = 9999;
		for (String s : diff) {
			if (d[Vertices.indexOf(s)] < min) { // �̹� ������ ���� �����ϰ� ���� ���� ������ vertex �� ���� �� �۴ٸ�
				minVertex = s;
				min = d[Vertices.indexOf(s)]; // ���� ����
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
