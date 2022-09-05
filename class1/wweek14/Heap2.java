package class1.wweek14;

import java.util.ArrayDeque;
import java.util.Deque;

public class Heap2 {
	
	class Node {
		char key;
		Node left; 
		Node right;
		Node parent;
		
		public Node(char v, Node l, Node r, Node p) {
			this.key = v;
			this.left = l;
			this.right = r;
			this.parent = p;
		}
		
		public String toString() {
			return " " + key;
		}
	}
	
	Node heap, last;
	
	public Heap2() {
		heap = null;
		last = null;
	}
	//
	public void insert(char x) {
		Node pNext; // parent of new node
		if(heap == null) {
			heap = new Node(x, null, null, null);
			last = heap;
			pNext = null;
		}else if(heap == last) {
			heap.left = new Node(x, null, null, heap);
			last = heap.left;
			pNext = heap;
		}else if(last == last.parent.left) {
			last.parent.right = new Node(x, null, null, last.parent);
			last = last.parent.right;
			pNext = last.parent;
		}else { //right
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
		}
		heapifyUpward(last);
		showHeap();
	}
	
	private void heapifyUpward(Node node) {
		if(node == null || node.parent == null) return;
		if(node.key > node.parent.key) {
			swap(node, node.parent);
			heapifyUpward(node.parent);
		}
	}
	private void swap(Node a, Node b) {
		char temp = a.key;
		a.key = b.key;
		b.key = temp;
	}
	//
	public Character delete() {
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
		if(node == null || node.left == null) return;
		Node bigger = node.left;
		if(node.right!=null && node.right.key>node.left.key) {
			bigger = node.right;
		}
		if(bigger.key>node.key) {
			swap(bigger, node);
			heapifyDownward(bigger);
		}
	}
	
	public void showHeap() {

		System.out.println(levelOrderTraverse(heap));
	}

	private String levelOrderTraverse(Node node) {
		if(node == null) return null;
		Deque<Node> q = new ArrayDeque<Node>(); // deque는 interface
		q.addLast(heap);
 		return levelOrderTraverseIteration(q, "");
	}

	private String levelOrderTraverse(Deque<Node> q, String string) {
		if(q.isEmpty()) return string;
		Node node = q.poll();
		string = string + node.toString();
		
		if(node.left != null) {
			q.add(node.left);
			if(node.right!=null) {
				q.add(node.right);
			}
		}
		return levelOrderTraverseIteration(q, string);
	}
	//
	private String levelOrderTraverseIteration(Deque<Node> q, String string) {
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
	}

	public static void main(String[] args) {
		Heap2 h = new Heap2();
		for (int i = 0; i < 26; i++) {
			h.insert((char)((int)'A'+i));
		}
		System.out.println("\n Heap Created: ");
		h.showHeap();
		System.out.println("\n Sort [Max.heap]");
		Character c;
		while((c=h.delete())!=null) {
			System.out.print(c+ ": ");
			h.showHeap();
		}
	}
}
