package class2.wweek12;

public class MyLinkedList3<T> {
	
	public class NodeDL {
		T data;
		NodeDL left, right;
		
		public NodeDL() {
			
		}
		
		public NodeDL(T d) {
			data=d;
			left=null;
			right=null;
		}
		
		public boolean equals(NodeDL other) {
			return this.data.equals(other.data);
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	
	NodeDL head, tail;
	int size;

	public MyLinkedList3() {
		head=null;
		tail=null;
		size=0;
	}

	private boolean checkIndexValidation(int index) {
		return (index>=0 && index<size)? true:false;
	}
	
	public void add(T data) { //save data at the end of array
		addLast(data);
	}
	
	public void addLast(T data) {
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
	public void addFirst(T data) {
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

	public void add(int index, T data) {  // index<=size
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
	
	public T removeFirst() {
		if (head!=null) {
			T ret=head.data;
			removeANode(head);
			return ret;
		}
		else
			return null;
	}
	
	public T removeLast() {
		if (tail!=null) {
			T ret=tail.data;
			removeANode(tail);
			return ret;
		}
		else
			return null;
	}
		
	public T remove(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i=0;i<index;i++)
				temp=temp.right;
			T ret = temp.data;
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

	public boolean remove(T data) {
		NodeDL temp = head;
		while (temp !=null) {
			if (temp.data.equals(data)) {
				removeANode(temp);
				return true;
			}
			else {
				temp = temp.right;
			}	
		}
		return false;
	}
	
	public String toString() {
		String ret = "";
		NodeDL temp=head;
		while(temp!=null) {
			ret = ret +temp.data.toString()+" ";
			temp=temp.right;
		}
		return ret;
	}
	
	public int indexOf(T data) {
		NodeDL temp=head;
		for (int i=0;i<size;i++) {
			if (temp.data.equals(data))
				return i;
			temp=temp.right;
		}
		return -1;
	}
	
	public T get(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i=0;i<index;i++)
				temp=temp.right;
			return temp.data;
		}
		else
			return null;
	}
	
	public void set(int index, T data) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i=0;i<index;i++)
				temp=temp.right;
			temp.data = data;
		}
	}
	
	public int size() {
		return size;
	}
	
}