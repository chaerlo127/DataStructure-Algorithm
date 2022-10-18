package class2.week5;

import java.util.ArrayDeque;
import java.util.Deque;

// binary search tree
// preOrder : 전위 순회 (가운데 먼저) M -> L -> R
// inOrder : 중위 순회 (가운데가 두번째) L -> M -> R
// postOrder : 후위 순회 (가운데가 마지막) L -> R -> M
public class BST {

	public class Node {

		char data;
		// Single LinkedList: 자식은 부모가 누군지 모른다.
		// Double LinkedList: 자식이 부모가 누군지 안다. 
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

	// insert 된 위치로 균형을 잡기 위해 return variable 추가
	// Main에서 어디에 insert를 했는지 알기 위해 return variable 을 Node로 return 
	public Node insert(char x) {
		if (root==null) {
			return root = new Node(x);
		}
		else if (x<root.data) // x가 root data보다 값이 작다면? 왼 쪽
			return insert(root.left, root, x);
		else if (x>root.data) // x가 root data보다 값이 크다면? 오른 쪽
			return insert(root.right, root, x);	
		else { //   if (search(root, x)!=null) 
			System.out.println("\n>>> "+x+" : Duplication is not allowed");	
			return null;
		}
	}
	
	// overloading을 해서 같은 method지만 parameter가 다른 method
	private Node insert(Node p, Node pParent, char x) {
		if (p==null) { // 부모의 왼 쪽/오른 쪽에 값이 없는 상태라면
			Node temp = new Node(x);
			temp.parent=pParent;
			if (pParent.data>x) // 부모보다 값이 작다면
				pParent.left = temp; // 왼쪽
			else // 크다면 
				pParent.right = temp; // 오른 쪽
			return temp;
		}
		// 값이 있는 상태라면
		else if (x<p.data) // p보다 x가 더 작으면 -> 왼쪽
			return insert(p.left, p, x);
		else if (x>p.data) // p보다 x가 더 크다면 -> 오른 쪽
			return insert(p.right, p, x);
		else return null; // p가 x와 같다면 null return

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

	// delete된 Node의 부모를 return 해줘야한다. 
	public Node delete(char x) {
		if (search(root, x)==null) {
			System.out.println("\n>>> "+x+" : Not Found");
			return null;
		}
		else 
			return delete(root, x);
	}
	
	public Node delete(Node startNode, char x) {
		Node v = search(startNode, x);
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
			if (v.right!=null) { // v의 오른 쪽에 자식이 있다면? 
				if (v==v.parent.left) {
					v.parent.left = v.right;
					v.right.parent = v.parent;
				}
				else { // v==v.parent.right
					v.parent.right = v.right;
					v.right.parent = v.parent;					
				}
			}
			else { // v.left != null v의 왼 쪽에 자식이 있다면? 
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
			// successor의 value를 지우려고 하는 Node에 copy 시키고
			// successor를 지우면 delete가 되는 것임. 
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

	private Node successor(Node v) { // right 자식에서 left 아래로 내려감. 
		if (v==null)
			return null;

		Node p = v.right;
		while (p.left!=null)
			p=p.left;
		return p;
	}
	
	private Node predecessor(Node v) { // left 자식에서 right 아래로 내려감. 1
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
			// level 별로 쓰기 위해서 만든 queue
			// 일렬로 사용하려면 이 queue는 사용하는 의미가 없음. 
			Deque<Node> temp = new ArrayDeque<Node>();
			System.out.print("Depth-level "+depthLevel+"  :  ");
			
			// 현재 있는 걸 다 꺼내서 temp 에 저장
			while (que.peek()!=null) { // que에 값이 남이 있다면? 
				temp.add(que.poll());
			}
			while(temp.peek()!=null) {
				Node e = temp.poll();
				System.out.print(e.toString()+" ");
				if (e.left!=null) // e가 root 였다면, e의 left 저장
					que.add(e.left);
				if (e.right!=null) // e가 root 였다면, e의 right 저장
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
	//postOrder 상태
	public void preorder(Node node) {
		if(node != null) {
			System.out.print(node.data + ", ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	public void postorder(Node node) {
		if(node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data+ ", ");
		}
	}
	public int height() {    // getHeight -> height
		return height(root);
	}

	protected int height(Node t) {
		// leaf node는 원래 0이 기본임.
		if (t==null)
			// leaf node의 자식을 의미
		// = if(t.left == null && t.right == null) return 0;  더 간단하게 작성하기 위해서임. 
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
