package class2.week7;

import java.util.HashSet;
import java.util.Set;

public class DisjointSet {

	String key; // ���ø�
	int rank; // union �� ������ rank�� ����
	DisjointSet parent;

	public DisjointSet() {
		key = null;
		rank = -1; // rank�� leaf node�� 0�� �Ǿ�� �ϹǷ�
		parent = null;
	}

	public boolean equals(DisjointSet other) {
		if (key == other.key)
			return true;
		else
			return false;
	}

	public String toString() {
//		return ""+key+"["+parent.key+","+rank+"]";
		return "" + key + "[" + rank + "]";
	}

	public void showParent() {
		DisjointSet p = this;
		System.out.print(p.toString());
		while (!p.equals(p.parent)) {
			p = p.parent;
			System.out.print(" --> " + p.toString());
		}
		System.out.println();
	}

	public DisjointSet makeSet(String k) {
		this.key = k;
		this.rank = 0;
		this.parent = this;
		return this;
	}

	public DisjointSet findSet() {
		DisjointSet p = this;
		while (!p.equals(p.parent))
			p = p.parent;
		return p;
	}

	
	// rank�� ����� union 
	public DisjointSet union(DisjointSet other) {
		DisjointSet u = this.findSet();
		DisjointSet v = other.findSet();

		if (u.rank > v.rank) {
			v.parent = u;
			System.out.println("--- union : " + v.toString() + " > " + u.toString());
			return u;
		} else if (v.rank > u.rank) {
			u.parent = v;
			System.out.println("--- union : " + u.toString() + " > " + v.toString());
			return v;
		} else { // same ranks ==> anyone can be selected and rank++
			v.parent = u;
			System.out.println("--- union : " + v.toString() + " > " + u.toString());
			u.rank++;
			return u;
		}
	}

	public static void main(String[] args) {
		String[] cities = { "Seoul", "Incheon", "Daejeon", "Daegu", "Kwangju", "Pusan", "Ulsan", "Mokpo", "Chuncheon",
				"Kyeongju" };
		int[][] distance = { { 0, 1, 59 }, { 0, 2, 140 }, { 0, 3, 237 }, { 0, 7, 313 }, { 0, 8, 75 }, { 1, 7, 295 },
				{ 1, 8, 105 }, { 2, 3, 122 }, { 2, 4, 141 }, { 3, 4, 173 }, { 3, 5, 88 }, { 3, 6, 74 }, { 3, 8, 236 },
				{ 4, 5, 202 }, { 4, 7, 57 }, { 5, 9, 76 }, { 6, 8, 293 }, { 6, 9, 36 } };
		int n = distance.length;

		// Sort distance array
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++) {
				if (distance[i][2] > distance[j][2]) {
					int a, b, c;
					a = distance[i][0];
					b = distance[i][1];
					c = distance[i][2];
					distance[i][0] = distance[j][0];
					distance[i][1] = distance[j][1];
					distance[i][2] = distance[j][2];
					distance[j][0] = a;
					distance[j][1] = b;
					distance[j][2] = c;
				}
			}

		System.out.println("<< Edge : Sorted List >>");
		for (int i = 0; i < n; i++)
			System.out.println(i + " : " + distance[i][0] + "  " + distance[i][1] + "  " + distance[i][2]);

		// array ���� �ϳ��ϳ��� set�� ���� �� �ֵ���
		DisjointSet[] nodeSet = new DisjointSet[cities.length];

		System.out.println("\n<< MakeSet >>");

		for (int i = 0; i < cities.length; i++) {
			nodeSet[i] = new DisjointSet();
			nodeSet[i] = nodeSet[i].makeSet(cities[i]);
			nodeSet[i].showParent();
		}

		System.out.println("\n<< Union Processing... >>");

		Set<Integer> edgeSelected = new HashSet<>();

		// city�� ������ �Ѿ�� cycle�� ����� �ȴ�.
		for (int i = 0; i < n; i++) {
			DisjointSet tempA = nodeSet[distance[i][0]].findSet(); // ù ��° ���� (�� �� ����) -> �� ��ǥ ����(���, root)
			DisjointSet tempB = nodeSet[distance[i][1]].findSet(); // �� ��° ����
			if (tempA != tempB) { // A�� B�� ���� �ٸ� ���ø�? ���� �ؽ� ���� ������, ��Ȯ�� �Ʊ� ���� ������ Ȯ���ؾ� ��. 
				edgeSelected.add(i); // ������ �����Ѵ�.
				tempA.union(tempB); // rank�� ���̸� ȿ�������� �� .
				System.out.println(" >>> edge " + i + " : SELECTED ");
			} else {
				System.out.println(" xxx edge " + i + " : REJECTED "); // ���� Tree�� �̹� �����ִ� ���
			}
		}

		System.out.println("\n<< Union Result >>");

		for (int i = 0; i < cities.length; i++) {
			nodeSet[i].showParent();
		}

		System.out.println("\n<< Edge Selected >>");
		for (int i : edgeSelected) {
			System.out.println(i + " : " + distance[i][0] + "  " + distance[i][1] + "  " + distance[i][2]);
		}

	}

}
