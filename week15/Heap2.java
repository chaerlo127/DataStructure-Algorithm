package week15;

import java.util.ArrayDeque;
import java.util.Deque;

public class Heap2 {
	
	public class Node {
		char key;
		Node left;
		Node right;
		Node parent;
		
		public Node(char v, Node l, Node r, Node p) {
			key=v;
			left=l;
			right=r;
			parent =p;
		}
		
		public String toString() {
			return " "+key;
		}
	}
	
	Node heap, last;
	
	public Heap2() {
		heap=null;
		last=null;
	}
	
	public void insert(char x) {
		Node pNext; // parent of new node
		
		if (heap==null) {
			heap=new Node (x, null,null,null);
			last=heap;
			pNext=null;
		}
		else if (heap==last) {
			heap.left=new Node(x, null, null, heap);
			last=heap.left;
			pNext=heap;
		}
		else if (last==last.parent.left) {
			last.parent.right=new Node(x, null, null, last.parent);
			last = last.parent.right;
			pNext= last.parent;
		}
		else {
//  final exam-3
			pNext = last;
			//pNext가 null이 아니거나, pNext parent의 right가 내가 아니거나
			while (pNext.parent !=null && pNext != pNext.parent.left) {
				pNext = pNext.parent;
			}
			if(pNext.parent != null) {
				pNext = pNext.parent.right;
			}
			while(pNext != null && pNext.left !=null) {
				pNext = pNext.left;
			}
			pNext.left = new Node(x, null, null, pNext);
			last = pNext.left;
//			
//			
		}
		heapifyUpward(last);
		showHeap();
	}
	
	private void heapifyUpward(Node node) {
		if (node==null || node.parent==null)
			return;
		
		if (node.key>node.parent.key) {
			swap(node, node.parent);
			heapifyUpward(node.parent);
		}
	}
	
	private void swap(Node a, Node b) {
		char temp=a.key;
		a.key=b.key;
		b.key=temp;
	}
	
	public Character delete() {
//  final exam-4
		Node pNext; // parent of new node
		if(heap == null) {
			return null;
		}
		char reValue = heap.key;
		if(last == heap) {
			heap = null;
			return reValue;
		}
		else if(last == last.parent.right) {
			heap.key = last.key;
			last = last.parent.left;
			last.parent.right = null;
			pNext = last.parent;
		}else { //right
			pNext = last;
			//pNext가 null이 아니거나, pNext parent의 right가 내가 아니거나
			while (pNext.parent !=null && pNext != pNext.parent.right) {
				pNext = pNext.parent;
			}
			if(pNext.parent != null) {
				pNext = pNext.parent.left;
			}
			while(pNext != null && pNext.right !=null) {
				pNext = pNext.right;
			}
			heap.key = last.key;
			last.parent.left = null;
			last = pNext;
		}
		heapifyDownward(heap);
		return reValue;
	}
	
	private void heapifyDownward(Node node) {
		if (node==null||node.left==null)
			return;
		Node bigger = node.left;
		if (node.right!=null && node.right.key>node.left.key)
			bigger=node.right;
		if (bigger.key>node.key) {
			swap(bigger, node);
			heapifyDownward(bigger);
		}
	}
	
	public void showHeap() {
		System.out.println(levelOrderTraverseI(heap));
	}

	private String levelOrderTraverseI(Node root) {
//  final exam-5
		if(root == null) return null;
		Deque<Node> q = new ArrayDeque<Node>(); // deque는 interface
		q.addLast(root);
		String string = "";
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			string = string + node.toString();
			if(node.left != null) {
				q.add(node.left);
				if(node.right!=null) {
					q.add(node.right);
				}
			}
		}
		return string;
		//
	}

	public int countsubTreeNodes(char c) {
		Node node = getNode(heap, c);
		return countsubTreeNodes(node);
	}

	private Node getNode(Node node, char c) {  // assume key value is not duplicated 
////  final exam-6
		if(node == null) {
			return null; // 찾을 수 없을 때 
		}
		if(node.key == c) {
			return node;
		}else {
			Node leftNode = getNode(node.left, c);
			if(leftNode != null) {
				return leftNode;
			}
			Node rightNode = getNode(node.right, c);
			if(rightNode != null) {
				return rightNode;
			}
		} 
		return null;
	}

	private int countsubTreeNodes(Node node) {
		if (node==null)
			return 0;
		else 
			return 1+countsubTreeNodes(node.left)+countsubTreeNodes(node.right);
	}
	
	public static void main(String[] args) {
		Heap2 h = new Heap2();

		for (int i=0;i<26;i++) {
			h.insert((char)((int)'A'+i));
		}

		System.out.println("\nTree Created : " );
		h.showHeap();

		System.out.println("\nNumber of Nodes from " + 'Z' + " : " + h.countsubTreeNodes('Z'));
		System.out.println("\nNumber of Nodes from " + 'V' + " : " + h.countsubTreeNodes('V'));
		System.out.println("\nNumber of Nodes from " + 'Q' + " : " + h.countsubTreeNodes('Q'));
		System.out.println("\nNumber of Nodes from " + 'R' + " : " + h.countsubTreeNodes('R'));

		System.out.println("\nSort (Max.Heap) : " );
		Character c;
		while((c=h.delete()) != null) {
			System.out.print(c+" deleted : ");
			h.showHeap();
		}
	}
}

