package class1.wweek12;

public class BinarySearchTreeAssignment {
	
	public class Node{
		char data;
		Node left;
		Node right;
		Node parent;
		
		public Node(char value) {
			data = value;
			left = null; right = null; parent = null;
		}
		public String toString() {
			return "" + this.data;
		}
	}
	
	Node root;
	public BinarySearchTreeAssignment() {
		root = null;
	}
	
	public void insert(char x) {
		if(root == null) {
			root = new Node(x);
		}else if(x == root.data) {
			System.out.print("\n Duplication not allowed." );
			return;
		}
		else if (x < root.data) {
			insert(root.left, x, root);
		}else if(x> root.data) {
			insert(root.right, x, root);
		}
	}
	
	private void insert(Node node, char x, Node parent) {
		if(node == null) {
			//insert
			Node newNode = new Node(x);
			newNode.parent = parent;
			if(parent.data > x) {
				parent.left = newNode;
			}else {
				parent.right = newNode;
			}
			
		}else if(x == node.data) {
			System.out.print("\n Duplication not allowed." );
			return;
		}else if (x < node.data) {
			insert(node.left, x, node);
		}else if(x> node.data) {
			insert(node.right, x, node);
		}
	}

	public Node search(Node start, char x) {
		if(start == null || start.data == x) {
			return start;
		}else if(start.data >x) {
			return search(start.left, x);
		}else {
			return search(start.right, x);
		}
	}
	
	private Node successor(Node v) {
		if(v == null) {
			return null;
		}
		Node p = v.right;
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}
	
	private Node predecessor(Node v) {
		if(v == null) {
			return null;
		}
		Node p = v.left;
		while(p.right !=null) {
			p = p.right;
		}
		return p;
	}
	
	public void delete(char x) {
		Node node = search(root, x);
		if(node == null) {
			System.out.println("not Found. ");
			return;
		}
		delete(node, x);
	}
	
	private void delete(Node node, char x) {
		// case 1 : no child
		if(node.left == null && node.right == null) {
			if(x<node.parent.data) {
				node.parent.left = null;
			}else {
				node.parent.right = null;
			}
			return;
		}
		//case 2 : 1 child
		if (node.left == null && node.right != null) {
			if (node.data > node.parent.data) {
				node.parent.right = node.right;
			} else {
				node.parent.left = node.right;
			}
			return;
		} else if (node.left != null && node.right == null) {
			if (node.data > node.parent.data) {
				node.parent.right = node.left;
			} else {
				node.parent.left = node.left;
			}
			return;
		}
			
		// root node인 경우
		//case 3 : 2 children <= successor/predecessor
		 // recursion
		Node success = this.successor(node);
		node.data = success.data;
		delete(success, success.data);
	}
	
	//postOrder 상태
	public void preOrder(Node node) {
		
		if(node != null) {
			System.out.print(node.data + ", ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	public String inOrder(Node node) {
		if(node == null) {
			return " ";
		}else {
			return inOrder(node.left) + " " +  node.data + " " + inOrder(node.right);
		}
	}
	
	public void postOrder(Node node) {
		if(node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data+ ", ");
		}
	}
	
	private int getHeight(Node node) {
		if(node == null) {
			return -1;
		}else {
			return 1+Math.max(getHeight(node.left), getHeight(node.right));
		}
	}
	
	private int countNode(Node node) {
		if(node == null) {
			return 0;
		}else {
			return 1+countNode(node.left) + countNode(node.right);
		}
	}
	public void showTree() {
		System.out.println(this.toString());
		System.out.println("Height : " + getHeight(root));
		System.out.println("Number of Nodes : " + this.countNode(root));
	}
	public String toString() {
		
		return inOrder(root);
	}

	public static void main(String[] args) {
		BinarySearchTreeAssignment bt = new BinarySearchTreeAssignment();
		
		for(int i = 0; i< 30; i++) {
			int x = (int)(Math.random()*1000);
			char y = (char)((int) 'A' + x%26);
			bt.insert(y);
		}
		
		System.out.println("\n Tree Created: ");
		bt.showTree();
		
		
		char c = 'A';
		bt.delete(c);
		System.out.println("\n After Deleting " + c + " : ");
		bt.showTree();
		
		c = 'D';
		bt.delete(c);
		System.out.println("\n After Deleting " + c + " : ");
		bt.showTree();
		
		c = 'P';
		bt.delete(c);
		System.out.println("\n After Deleting " + c + " : ");
		bt.showTree();
		
		c = 'S';
		bt.delete(c);
		System.out.println("\n After Deleting " + c + " : ");
		bt.showTree();
	}


}
