package wweek12;

public class SimpleTree {
	
	public class TreeNode{
		char data;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(TreeNode l, char value, TreeNode r) {
			data = value;
			left = l; right = r;
		}
		public String toString() {
			return "" + this.data;
		}
	}
	
	TreeNode root;
	public SimpleTree() {
		root = null;
	}
	
	public TreeNode makeTree(TreeNode l, char v, TreeNode r) {
		return root = new TreeNode(l, v, r);
		
	}
	
	public void showTree(TreeNode node) {
		if(node != null) {
			showTree(node.left);
			System.out.println(node.data);
			showTree(node.right);
		}
	}
	
	public void treeInArray(TreeNode node) {
		char [] treeArray = new char[12];
		
		convert(node, treeArray, 1); // 시작은 1에서 부터
		
		System.out.println("Tree in Array");
		for(int i = 1; i<8; i++) {
			System.out.println("index "+ i+ ": " + treeArray[i]);
		}
	}
	
	private void convert(TreeNode node, char[] treeArray, int i) {
		if(node != null) {
			treeArray[i] = node.data;
			convert(node.left, treeArray, i*2);
			convert(node.right, treeArray, i*2+1);
		}
	}

	public static void main(String[] args) {
		SimpleTree t = new SimpleTree();
		TreeNode n1 = t.makeTree(null, 'A', null);
		TreeNode n2 = t.makeTree(null, 'B', null);
		TreeNode n3 = t.makeTree(n1, '*', n2);
		
		TreeNode n4 = t.makeTree(null, 'C', null);
		TreeNode n5 = t.makeTree(null, 'D', null);
		TreeNode n6 = t.makeTree(n4, '/', n5);
		
		TreeNode n7 = t.makeTree(n3, '-', n6);
		
		
		t.showTree(n7);
		
		t.treeInArray(n7);
		
	}

}
