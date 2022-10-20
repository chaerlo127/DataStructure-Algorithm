package class2.week6;

// 인터넷에서 서치한 Red Black Tree Code
public class InternetRedBlackTree {

	private static final int BLACK = 0;
	private static final int RED = 1;
	
	private static Node root;
	
	private static class Node {
		private int value;
		private int color;
		
		Node left;
		Node right;
		Node parent;
		
		Node(int value) {
			this.value = value;
			color = BLACK;
			
			left = null;
			right = null;
			parent = null;
		}
		
		Node() {
			this(-1);
		}
		
		int getValue() {
			return value;
		}
		
		String getColor() {
			return color == RED ? "RED" : "BLACK";
		}
		
		void setColor(int color) {
			this.color = color;
		}
	}
	
	public static void printTree(Node node) {
		if (node == null)
			return;

		System.out.println(node.getValue() + "(" + node.getColor() + ")");
		printTree(node.left);
		printTree(node.right);
	}
	
	public static Node findNode(Node goal, Node node) {
		if (node == null)
			return null;
		
		if (goal.getValue() < node.getValue()) {
			if (node.left != null)
				return findNode(goal, node.left);
		}
		else if (goal.getValue() > node.getValue()) {
			if (node.right != null)
				return findNode(goal, node.right);
		}
		else {
			return node;
		}
		
		return null;
	}
	
	public static void insertNode(Node node) {
		if (root == null) {
			root = node;
			
			System.out.println("Created RBT !!!\n");
		}
		else {
			Node parent = root;
			
			node.setColor(RED);
			
			while (true) {
				if (parent.getValue() < node.getValue()) {
					if (parent.right == null) {
						parent.right = node;
						node.parent = parent;
						break;
					}
					else {
						parent = parent.right;
					}
				}
				else {
					if (parent.left == null) {
						parent.left = node;
						node.parent = parent;
						break;
					}
					else {
						parent = parent.left;
					}
				}
			}
			
			recolorTree(node);
		}
		
		System.out.println("Inserted " + node.getValue());
	}
	
	// node는 붉은 색
	private static void recolorTree(Node node) {
		while (node.parent != null && "RED".equals(node.parent.getColor())) {
			Node next = null;
			
			if (node.parent == node.parent.parent.left) { // 노드의 부모가 조부모의 왼쪽 자식 이라면? 
				next = node.parent.parent.right; // next는 조부모의 오른쪽 자식
				
				if (next != null && "RED".equals(next.getColor())) { // next가 red라면
					node.parent.setColor(BLACK); // 부모의 색을 black으로
					next.setColor(BLACK); // next의 색을 검은색으로 
					node.parent.parent.setColor(RED); // 조부모는 red -> 자식은 black이어야 함. -> node가 red로 들어오기 때문
					node = node.parent.parent; // node를 조부모로 변경
					continue;
				}
				
				if (node == node.parent.right) { // node가 node 부모의 오른 쪽이라면? 
					node = node.parent;
					
					rotateLeft(node); // y자리에 있던 것을 x로 옮겨서 rotate left로 (AVL에서 x, y 위치)
				}
				
				node.parent.setColor(BLACK);
				node.parent.parent.setColor(RED);
				
				rotateRight(node.parent.parent);
				break;
			}
			else {
				next = node.parent.parent.left;
				
				if (next != null && "RED".equals(next.getColor())) {
					node.parent.setColor(BLACK);
					next.setColor(BLACK);
					node.parent.parent.setColor(RED);
					node = node.parent.parent;
					continue;
				}
				
				if (node == node.parent.left) {
					node = node.parent;
					
					rotateRight(node);
				}
				
				node.parent.setColor(BLACK);
				node.parent.parent.setColor(RED);
				
				rotateLeft(node.parent.parent);
				break;
			}
		}
		
		root.setColor(BLACK);
	}
	
	private static void rotateLeft(Node node) {
		if (node.parent == null) {
			Node right = root.right;
			root.right = root.right.left;
			right.left = new Node();
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = null;
			root = right;
		}
		else {
			if (node == node.parent.left)
				node.parent.left = node.right;
			else
				node.parent.right = node.right;
			
			node.right.parent = node.parent;
			node.parent = node.right;
			
			if (node.right.left != null)
				node.right.left.parent = node;
			
			node.right = node.right.left;
			node.parent.left = node;
		}
	}
	
	private static void rotateRight(Node node) {
		if (node.parent == null) {
			Node left = root.left;
			root.left = root.left.right;
			left.right = new Node();
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = null;
			root = left;
		}
		else {
			if (node == node.parent.left)
				node.parent.left = node.left;
			else
				node.parent.right = node.left;
			
			node.left.parent = node.parent;
			node.parent = node.left;
			
			if (node.left.right != null)
				node.left.right.parent = node;
			
			node.left = node.left.right;
			node.parent.right = node;
		}
	}
	
	public static void main(String[] args) {
		root = null;
		
		insertNode(new Node(100));
		insertNode(new Node(50));
		insertNode(new Node(30));
		insertNode(new Node(80));
		insertNode(new Node(200));
		insertNode(new Node(400));
		insertNode(new Node(10));
		insertNode(new Node(500));
		insertNode(new Node(250));
		insertNode(new Node(120));
		
		System.out.println();
		printTree(root);
	}

}