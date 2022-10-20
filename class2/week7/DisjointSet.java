package class2.week7;

// 알고리즘이 필요한 것은 다른 알고리즘에서 필요한 부분 알고리즘
public class DisjointSet {

	String key; // 도시명
	int rank; // union 될 때마다 rank를 조정
	DisjointSet parent;

	public DisjointSet() {
		key = null;
		rank = -1; // rank의 leaf node가 0이 되어야 하므로
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
	
	// 경로 압축
	public DisjointSet findSetCompression() {
		DisjointSet p = this;
		if(p != p.parent) p.parent = findSetCompression(this.parent);
		return p;
	}
	
	// 경로 압축
	private DisjointSet findSetCompression(DisjointSet node) {
		DisjointSet p = node;
		if(p != p.parent) p.parent = findSetCompression(node.parent);
		return p;
	}
	
	// 경로 압축 Iteration 하기
	

	// rank를 고려한 union 
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
	
	// rank를 고려한 union 
		public DisjointSet unionCompression(DisjointSet other) {
			DisjointSet u = this.findSetCompression();
			DisjointSet v = other.findSetCompression();

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

	}

}
