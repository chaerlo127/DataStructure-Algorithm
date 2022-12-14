package class1.wweek11;


public class Traverse {
	
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
	public Traverse() {
		root = null;
	}
	
	public TreeNode makeTree(TreeNode l, char v, TreeNode r) {
		return root = new TreeNode(l, v, r);
		
	}
	public void preOrder(TreeNode node) {
		if(node != null) {
			System.out.print(node.data + " , ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public void inOrder(TreeNode node) {
		if(node != null) {
			inOrder(node.left);
			System.out.print(node.data + " , ");
			inOrder(node.right);
		}
	}
	
	public void postOrder(TreeNode node) {
		if(node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data + " , ");
		}
	}
	

	
	public void treeInArray(TreeNode node) {
		char [] treeArray = new char[100];
		
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
		Traverse t = new Traverse();
		TreeNode n0 = t.makeTree(null, 'K', null);
		TreeNode n1 = t.makeTree(null, 'J', null);
		TreeNode n2 = t.makeTree(null, 'I', null);
		TreeNode n3 = t.makeTree(null, 'H', null);
		TreeNode n4 = t.makeTree(null, 'G', n0);
		TreeNode n5 = t.makeTree(null, 'F', null);
		TreeNode n6 = t.makeTree(n2, 'E', n1);
		TreeNode n7 = t.makeTree(n3, 'D', null);
		TreeNode n8 = t.makeTree(n5, 'C', n4);
		TreeNode n9 = t.makeTree(n7, 'B', n6);
		TreeNode n10 = t.makeTree(n9, 'A', n8);
		
		System.out.println("preOrder");
		t.preOrder(n10);
		System.out.println();
		
		System.out.println("InOrder");
		t.inOrder(n10);
		System.out.println();
		
		System.out.println("postOrder");
		t.postOrder(n10);
		System.out.println();
		
		
		t.treeInArray(n10);
		

		
	}

}
