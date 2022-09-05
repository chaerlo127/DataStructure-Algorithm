package class1.week8;
// 1번 문제용


public class MyLinkedList3Rec {

	NodeDL head, tail;
	int size;

	public MyLinkedList3Rec() {
		head=null;
		tail=null;
		size=0;
	}

	private boolean checkIndexValidation(int index) {
		return (index>=0 && index<size)? true:false;
	}
	
	public void add(MyData data) { //save data at the end of array
		addLast(data);
	}
	
	public void addLast(MyData data) {
		NodeDL newNode = new NodeDL(data);
		if (tail==null) {
			tail=newNode;
			head=newNode;
		}
		else {
			newNode.left=tail;
			tail.right=newNode;
			tail=newNode;
		}
		size++;
	}
	public void addFirst(MyData data) {
		NodeDL newNode = new NodeDL(data);
		if (head==null) {
			tail=newNode;
			head=newNode;
		}
		else {
			newNode.right=head;
			head.left=newNode;
			head=newNode;
		}
		size++;
	}

	public void add(int index, MyData data) {  // index<=size
		if (checkIndexValidation(index)) {
			if (index==0)
				addFirst(data);
			else {
				NodeDL temp = head;
				for (int i=0;i<index;i++)
					temp=temp.right;
				NodeDL newNode = new NodeDL(data);
				newNode.right=temp;
				newNode.left=temp.left;
				temp.left.right=newNode;
				temp.left=newNode;
				size++;
			}	
		}
		else if(index==size)
			addLast(data);
	}
	
	public MyData removeFirst() {
		if (head!=null) {
			MyData ret=head.data;
			removeANode(head);
			return ret;
		}
		else
			return null;
	}
	
	public MyData removeLast() {
		if (tail!=null) {
			MyData ret=tail.data;
			removeANode(tail);
			return ret;
		}
		else
			return null;
	}
		
	public MyData remove(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i=0;i<index;i++)
				temp=temp.right;
			MyData ret = temp.data;
			removeANode(temp);
			return ret;
		}
		else
			return null;
	}
	
	private void removeANode(NodeDL temp) {
		if (head==temp) {
			if (tail==temp) {
				head=null;
				tail=null;
			}
			else {
				head=head.right;
				head.left=null;
			}
		}
		else {
			if (tail==temp) {
				tail=tail.left;
				tail.right = null;
			}
			else {
				temp.right.left = temp.left;
				temp.left.right = temp.right;
			}
		}
		size--;
	}
////////////////////////////////////////////////////////////////
	//학번 : 60201976
	// 이름: 장채은
////////////////////////////////////////////////////////////////
	public int indexOf(MyData data) {
		return indexOf(data,head, 0);
	}
	
	private int indexOf(MyData data, NodeDL head2, int i) {
		if(head2.right == null || size == i) {
			return -1;
		}
		if(!data.equals(head2.data)) {
			return indexOf(data, head2.right, i+1);
		}else {
			return i; // base condition 
		}	

	}
////////////////////////////////////////////////////////////////
//학번 : 60201976
// 이름: 장채은
////////////////////////////////////////////////////////////////
	public boolean remove(MyData data) {
		return remove(data, head);
	}
	private boolean remove(MyData data, NodeDL head2) {
		if(head2 == null || head2.right == null) {
			return false;
		}else if(head2.data.equals(data)) {
			removeANode(head2);
			return true; // base condition 
		}else {
			return remove(data, head2.right);
		}
		
	}
////////////////////////////////////////////////////////////////
//학번 : 60201976
//이름: 장채은
////////////////////////////////////////////////////////////////
	public String toString() {
		return toString(head);
	}
	
	private String toString(NodeDL head2) {
		if(head2 == null) {
			return "";
		}else if(head2.right == null) {
			return head2.data.toString(); // base condition 
		}
		else {
			return head2.data.toString() + ", "+ toString(head2.right);
		}
		
	}
////////////////////////////////////////////////////////////////

	public MyData get(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i=0;i<index;i++)
				temp=temp.right;
			return temp.data;
		}
		else
			return null;
	}
}