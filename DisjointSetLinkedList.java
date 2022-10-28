package class2.week7;

import java.util.HashSet;
import java.util.Set;

// 알고리즘이 필요한 것은 다른 알고리즘에서 필요한 부분 알고리즘
public class DisjointSetLinkedList {

	String key; // 도시명
	int rank; // union 될 때마다 rank를 조정
	DisjointSetLinkedList parent;
	DisjointSetLinkedList next;
	DisjointSetLinkedList tail;

	public DisjointSetLinkedList() {
		key = null;
		rank = -1; // rank의 leaf node가 0이 되어야 하므로
		parent = null;
		tail = null;
	}

	public boolean equals(DisjointSetLinkedList other) {
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
		DisjointSetLinkedList p = this;
		System.out.print(p.toString());
		while (!p.equals(p.parent)) {
			p = p.parent;
			System.out.print(" --> " + p.toString());
		}
		System.out.println();
	}

	public DisjointSetLinkedList makeSet(String k) {
		this.key = k;
		this.rank = 1;
		this.parent = this;
		this.tail = this;
		this.next = null;
		return this;
	}

	public DisjointSetLinkedList findSet() {
		return this.parent;
	}

	// rank를 고려한 union 
	public DisjointSetLinkedList union(DisjointSetLinkedList other) {
		DisjointSetLinkedList u = this.findSet();
		DisjointSetLinkedList v = other.findSet();
		if (u.rank >= v.rank) {
			DisjointSetLinkedList temp = v;
			u.tail.next = v;
			u.rank++;
			while (temp != temp.tail) {
				temp = temp.next;
				temp.parent = u;
				u.rank++;
			}
			temp.parent = u;
			u.tail = temp.tail;
			System.out.println("--- union : " + v.toString() + " > " + u.toString());
			return u;
		}else {
			DisjointSetLinkedList temp = u;
			v.tail.next = u;
			v.rank++;
			while (temp != temp.tail) {
				temp = temp.next;
				temp.parent = v;
				u.rank++;
			}
			temp.parent = v;
			v.tail = temp.tail;
			System.out.println("--- union : " + v.toString() + " > " + u.toString());
			return v;
		}
	}
	

	// main은 disjointSet이라는 알고리즘을 이렇게 사용한다는 예시를 알려줌.
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

		// array 원소 하나하나가 set이 들어가갈 수 있도록
		DisjointSetLinkedList[] nodeSet = new DisjointSetLinkedList[cities.length];

		System.out.println("\n<< MakeSet >>");

		// DisjointSet의 내부 구현을 access를 하기 위해서 만들어진 것임.
		for (int i = 0; i < cities.length; i++) {
			nodeSet[i] = new DisjointSetLinkedList();
			nodeSet[i] = nodeSet[i].makeSet(cities[i]);
			nodeSet[i].showParent();
		}

		
		// sorting 된 distance를 찾아나가면 됨.
		System.out.println("\n<< Union Processing... >>");

		// 최소 경로 및 최대 이익을 얻기 위한 지역을 선정했으면 set에 저장 -> cycle이 생기지 않도록 하기 위해
		Set<Integer> edgeSelected = new HashSet<>();

		
		// n = distance의 길이
		// 실제로는 city의 개수로 해도 된다
		// city의 개수를 넘어가면 cycle이 생기게 된다.
		for (int i = 0; i < n; i++) {
			DisjointSetLinkedList tempA = nodeSet[distance[i][0]].findSet(); // 첫 번째 도시 (한 쪽 도시) -> 의 대표 선수(노드, root)
			DisjointSetLinkedList tempB = nodeSet[distance[i][1]].findSet(); // 두 번째 도시
			if (tempA != tempB) { // A와 B가 서로 다른 도시면? 고유 해쉬 값이 같으냐, 정확히 아까 만든 애인지 확인해야 함. 서로 각기 다른 트리에 속해 있다면?
				edgeSelected.add(i); // 집합을 저장한다.
				tempA.union(tempB); // rank를 붙이면 효율적으로 됨 .
				System.out.println(" >>> edge " + i + " : SELECTED ");
			} else { // 같은 트리에 속해 있다면? 
				System.out.println(" xxx edge " + i + " : REJECTED "); // 같은 Tree에 이미 속해있는 경우
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
