package class2.week7;

// �˰����� �ʿ��� ���� �ٸ� �˰��򿡼� �ʿ��� �κ� �˰���
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
	
	// ��� ����
	public DisjointSet findSetCompression() {
		DisjointSet p = this;
		if(p != p.parent) p.parent = findSetCompression(this.parent);
		return p;
	}
	
	// ��� ����
	private DisjointSet findSetCompression(DisjointSet node) {
		DisjointSet p = node;
		if(p != p.parent) p.parent = findSetCompression(node.parent);
		return p;
	}
	
	// ��� ���� Iteration �ϱ�
	

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
	
	// rank�� ����� union 
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
