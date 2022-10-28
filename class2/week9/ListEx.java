package class2.week9;

import java.util.LinkedList;

public class ListEx {
	Node head;
	
	public void insert(int a) {
		Node newNode = new Node(a);
		newNode.next = null;
		
		if(head == null) head = newNode;
		else {
			if(a<=head.data) {
				newNode.next = head;
				head = newNode;
			}else {
				Node p = head;
				while(p.next != null) {
					if(a<=p.next.data) {
						newNode.next = p.next;
						p.next = newNode;
						break;
					}else p = p.next;
				}
				p.next = newNode;
			}
		}
	}
	
//	Node p = head;
//	Node q = p.next;
	
	public String toString() {
		Node p = head;
		String ret = "";
		while(p != null) {
			ret = ret + " " + p.data;
			p = p.next;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		int[] input = {20, 10, 50, 25, 33, 12, 44};
		
		// LinkedList를 살짝 변형한 것이 Stack, Queue이다. 
		// 따로 메소드 오버라이딩 하지 않고도 사용할 수 있다.
		LinkedList<Node> a = new LinkedList<>();
		
		for(int i = 0; i<input.length; i++) {
			a.add(new Node(input[i]));
		}
		
		System.out.println(a.toString());
		
		for(int i = 0; i<input.length-1; i++) {
			for(int j = i+1; j<input.length; j++) {
				if(a.get(i).data>a.get(j).data) {
					// swap
				}
			}
		}
		
		ListEx b = new ListEx();
		
		for(int i= 0; i<input.length; i++) {
			b.insert(input[i]);
		}
		
		System.out.println(b.toString());
	}

}
