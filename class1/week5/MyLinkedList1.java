package class1.week5;
public class MyLinkedList1 {
	private class Node{
		int value;
		Node next;
		
		private Node(int data) {
			value = data;
			next = null;
		}

	}
	Node listPointer; // �� �κ��� �߰����ִ� node
	Node tail; // �� �κ��� �߰����ִ� node
	int size;
	
	public MyLinkedList1() {
		listPointer = null;
		tail = null;
		size = 0;
	}		
	
	//�߰��� ����--------------------------------
	public void add(int data) {
		Node newNode = new Node(data);
		if(listPointer == null) {
			addFirst(data);
		}else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	
	public int addFirst(int data) {
		Node newNode = new Node(data);
		newNode.next = listPointer;
		listPointer = newNode;
		size ++;
		//�߰��� ����--------------------------------
		if(listPointer.next == null) {
			this.tail = listPointer;
		}
		return 0;
	}
	
	public int remove(int data) {
		int index = 0;
		
		if(listPointer==null) {
			return -1;
		}
		if(listPointer.value==data) { // ù ��° node
			listPointer = listPointer.next;
			return index;
		}
		Node p = listPointer;
		Node q = p.next;
		while(q.next!=null) {
			index++;
			if(q.value==data) {// found!
				p.next = q.next;
				return index;
			}else {
				p = q;
				q = q.next;
			}
		}
		
		return -1;
	}
	//�߰��� ����--------------------------------
	public int sizeOf() {
		return size;
	}
	
	
	public String toString() {
		String ret = "";
		Node temp = listPointer;
		
		while(temp!=null) {
			ret = ret + temp.value + ", ";
			temp = temp.next;
		}
		return ret;
	}
			
	
	

}
