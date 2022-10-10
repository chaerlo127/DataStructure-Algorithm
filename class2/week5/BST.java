package class2.week5;

import java.util.ArrayDeque;
import java.util.Deque;

// binary search tree
public class BST {

	public class Node {

		char data;
		// Single LinkedList: �ڽ��� �θ� ������ �𸥴�.
		// Double LinkedList: �ڽ��� �θ� ������ �ȴ�. 
		Node parent;
		Node left;
		Node right;

		public Node(char value) {
			data=value;
			parent=null;
			left=null;
			right=null;
		}

		public String toString() {
			return ""+data+"("+height(this)+")";
		}

	}
	Node root;

	public BST() {
		root = null;
	}

	// insert �� ��ġ�� ������ ��� ���� return variable �߰�
	// Main���� ��� insert�� �ߴ��� �˱� ���� return variable �� Node�� return 
	public Node insert(char x) {
		if (root==null) {
			return root = new Node(x);
		}
		else if (x<root.data)
			return insert(root.left, root, x);
		else if (x>root.data)
			return insert(root.right, root, x);	
		else { //   if (search(root, x)!=null) 
			System.out.println("\n>>> "+x+" : Duplication is not allowed");	
			return null;
		}
	}
	
	// overloading�� �ؼ� ���� method���� parameter�� �ٸ� method
	private Node insert(Node p, Node pParent, char x) {
		if (p==null) {
			Node temp = new Node(x);
			temp.parent=pParent;
			if (pParent.data>x)
				pParent.left = temp;
			else
				pParent.right = temp;
			return temp;
		}
		else if (x<p.data)
			return insert(p.left, p, x);
		else if (x>p.data)
			return insert(p.right, p, x);
		else return null;

	}

	public Node search(Node startNode, char x) {
		Node p = startNode;
		if ((p==null)||(x==p.data))
			return p;
		else if (x<p.data)
			return search(p.left, x);
		else
			return search(p.right, x);
	}

	// delete�� Node�� �θ� return ������Ѵ�. 
	public Node delete(char x) {
		if (search(root, x)==null) {
			System.out.println("\n>>> "+x+" : Not Found");
			return null;
		}
		else 
			return delete(root, x);
	}
	public Node delete(Node startNode, char x) {
		Node v = search(startNode,x);
		if (v==null)  // not found
			return null;

		// case 1 : no child
		if (v.left==null && v.right==null) {

			if (root==v) {  //  or v.parent==null
				root=null;
				return root;
			}
			else if (x<v.parent.data)
				v.parent.left = null;
			else
				v.parent.right = null;
			return v.parent;
		}
		// case 2 : 1 child
		if (v.left==null || v.right==null) {
			if (v.right!=null) {
				if (v==v.parent.left) {
					v.parent.left = v.right;
					v.right.parent = v.parent;
				}
				else { // v==v.parent.right
					v.parent.right = v.right;
					v.right.parent = v.parent;					
				}
			}
			else { // v.left != null
				if (v==v.parent.left) {
					v.parent.left = v.left;
					v.left.parent = v.parent;
				}
				else { // v==v.parent.right
					v.parent.right = v.left;
					v.left.parent = v.parent;				
				}
			}
			return v.parent;
		}
		// case 3-1 : 2 children , let's pick successor
		else {
			// successor�� value�� ������� �ϴ� Node�� copy ��Ű��
			// successor�� ����� delete�� �Ǵ� ����. 
			Node q = successor(v);
			v.data = q.data;
			return delete(v.right, q.data);
		}
		//		// case 3-2 : 2 children , let's pick predecessor
		//		else {
		//			Node q = predecessor(v);
		//			v.data = q.data;
		//			delete(v.left, q.data);
		//		}
	}

	private Node successor(Node v) {
		if (v==null)
			return null;

		Node p = v.right;
		while (p.left!=null)
			p=p.left;
		return p;
	}
	private Node predecessor(Node v) {
		if (v==null)
			return null;
		Node p = v.left;
		while (p.right!=null)
			p=p.right;
		return p;
	}
	public void showTree() {
		if (root==null)
			return;
		Deque<Node> que = new ArrayDeque<Node>();
		que.add(root);
		int depthLevel = 0;
		while(que.peek()!=null) {
			// level ���� ���� ���ؼ� ���� queue
			// �Ϸķ� ����Ϸ��� �� queue�� ����ϴ� �ǹ̰� ����. 
			Deque<Node> temp = new ArrayDeque<Node>();
			System.out.print("Depth-level "+depthLevel+"  :  ");
			
			// ���� �ִ� �� �� ������ temp �� ����
			while (que.peek()!=null) {
				temp.add(que.poll());
			}
			while(temp.peek()!=null) {
				Node e = temp.poll();
				System.out.print(e.toString()+" ");
				if (e.left!=null)
					que.add(e.left);
				if (e.right!=null)
					que.add(e.right);
			}
			System.out.println();
			depthLevel++;
		}
			System.out.println("Number of nodes : "+countNode());
			System.out.println("Height of Tree : "+height());
	}

	public String toString() {
		return toString(root);
	}
	public String toString(Node v) {
		return inorder(v);
	}
	private String inorder(Node v) {
		if (v==null)
			return "";
		else
			return inorder(v.left)+" "+v.toString()+" "+inorder(v.right);
	}

	public int height() {    // getHeight -> height
		return height(root);
	}

	protected int height(Node t) {
		// leaf node�� ���� 0�� �⺻��.
		if (t==null)
			// leaf node�� �ڽ��� �ǹ�
		// = if(t.left == null && t.right == null) return 0;  �� �����ϰ� �ۼ��ϱ� ���ؼ���. 
			return -1;
		else 
			return 1+Math.max(height(t.left), height(t.right));
	}

	public int countNode() {
		return countNode(root);
	}

	private int countNode(Node t) {
		if (t==null)
			return 0;
		else 
			return 1+countNode(t.left)+countNode(t.right);
	}

	/////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		BST bt = new BST();

		for (int k=0;k<10;k++) {
			char y=(char)((int)'A'+k);
			System.out.print(y+"  ");
			bt.insert(y);			
		}
		System.out.println();

		System.out.print("\nTree Created : \n");
		bt.showTree();

		bt.delete('B');
		System.out.print("\nAfter deleting 'B' : \n");
		bt.showTree();
		bt.delete('G');
		System.out.print("\nAfter deleting 'G' :\n ");
		bt.showTree();
		bt.delete('D');
		System.out.print("\nAfter deleting 'D' :\n ");
		bt.showTree();

	}
}
