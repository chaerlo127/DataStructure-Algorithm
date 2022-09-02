package practice;

public class BinarySearchTree {
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
	private Node root;
	public BinarySearchTree() {
		root = null;
	}
	private void insert(char x) {
		if(root == null) {
			root = new Node(x);
		}else if(x == root.data) {
			System.out.println("\n can't not put in this data");
			return;
		}else if(x<root.data) {
			insert(root.left, x, root);
		}else if(x > root.data) {
			insert(root.right, x, root);
		}
	}
	private void insert(Node node, char x, Node parent) {
		if(node == null) {
			Node newNode = new Node(x);
			if(parent.data > x) {
				parent.left = newNode;
			} else
				parent.right = newNode;
			newNode.parent = parent;
		}else if(x == node.data) {
			System.out.println("\n can't not put in this data");
			return;
		}else if(x < node.data) {
			insert(node.left, x, node);
		}else if(x > node.data) {
			insert(node.right, x, node);
		}
	}
	
	public Node search(Node start, char x) {
		if(start == null || start.data == x) {
			return start;
		}else if(start.data > x) {
			return search(start.left, x);
		}else {
			return search(start.right, x);
		}
	}
	public void delete(char x) {
		Node node = search(root, x);
		if(node == null) {
			System.out.println("can't find this data");
			return;
		}
		delete(node, x);
	}
	
	public void delete(Node node, char x) {
		if (node.left == null && node.right == null) {
			if (root == node) {
				root = null;
				if (x < node.parent.data) {
					node.parent.left = null;
				} else {
					node.parent.right = null;
				}
				return;
			}
		}
		if(node.left != null || node.right != null) {
			if(node.right != null) {
				if(node == node.parent.left) {
					node.parent.left = node.right;
				}else {
					node.parent.right = node.right;
				}
				node.right.parent = node.parent;
			}else {
				if(node == node.parent.left) {
					node.parent.left = node.left;
				}else 
					node.parent.right = node.left;
				node.left.parent = node.parent;
			}
			return;
		}
		else {
			Node q = successor(node);
			node.data = q.data;
			delete(node.right, q.data);
		}
	}
	private Node successor(Node node) {
		if(node == null) return null;
		Node p = node.right;
		while(p.left!=null) {
			p = p.left;
		}
		return p;
	}
	
	private Node predecessor(Node node) {
		if(node == null) return node;
		Node p = node.left;
		if(p.left!= null) {
			p = p.left;
		}
		return p;
	}
	public void preOrder(Node node) {
		if(node != null) {
			System.out.print(node.data + ", ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	public String inOrder(Node node) {
		if(node != null) {
			return inOrder(node.left) + node.data + ", "+ inOrder(node.right);
		}else return " ";
	}
	public void postOrder(Node node) {
		if(node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.data + ", ");
		}
	}

	
	private int countTree(Node node) {
		if(node == null) return 0;
		else return 1 + countTree(node.left)+ countTree(node.right);
	}
	private int getHeight(Node node) {
		if(node == null) return -1;
		else return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}

	public void showTree() {
		System.out.println(this.toString());
	}
	public String toString() {
		return inOrder(root);
	}
	public static void main(String[] args) {
		BinarySearchTree bt = new BinarySearchTree();

		for (int i = 0; i < 30; i++) {
			int x = (int) (Math.random() * 1000);
			char y = (char) ((int) 'A' + x % 26);
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
