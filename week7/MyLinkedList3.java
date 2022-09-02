package week7;

public class MyLinkedList3 {
	private NodeDL head, tail;
	private int size;

	public MyLinkedList3() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	private boolean checkIndexValidation(int index) {
		return (index >= 0 && index < size) ? true : false;
	}

	public void addLast(MyData data) {
		NodeDL newNode = new NodeDL(data);
		if (tail == null) {
			tail = newNode;
			head = newNode;
		} else {
			newNode.left = tail;
			tail.right = newNode;
			tail = newNode;
		}
		size++;
	}

	public void addFirst(MyData data) {
		NodeDL newNode = new NodeDL(data);
		if (head == null) {
			tail = newNode;
			head = newNode;
		} else {
			newNode.right = head;
			head.left = newNode;
			head = newNode;
		}
		size++;
	}
	
	public void add(MyData data) {
		addLast(data);
	}

	public void add(int index, MyData data) {
		if (checkIndexValidation(index)) {
			if (index == 0)
				addFirst(data);
			else {
				NodeDL temp = head;
				for (int i = 0; i < index; i++) { // index 번까지 진행
					temp = temp.right; // newNode가 들어가면 index + 1이 되는 값, newNode의 right가 되는 노드
				}
				NodeDL newNode = new NodeDL(data);
				newNode.right = temp;
				newNode.left = temp.left;
				temp.left.right = newNode;
				temp.left = newNode;
				size++;

			}
		} else if (index == size)
			addLast(data);
	}

	
	public MyData removeFirst() {
		if(head!=null) {
			MyData ret = head.data;
			removeANode(head);
			return ret;
		}else return null;
	}
	
	
	public MyData removeLast() {
		if(tail!=null) {
			MyData ret = tail.data;
			removeANode(tail);
			return ret;
		}else return null;
	}
	public MyData remove(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.right;
			}
			MyData ret = temp.data;
			removeANode(temp);
			return ret;
		} else
			return null;
	}

	private void removeANode(NodeDL temp) {
		if (temp == head) {
			if (tail == temp) {
				this.head = null;
				this.tail = null;
			} else {
				this.head = this.head.right;
				this.head.left = null;
			}
		} else {
			if (tail == temp) {
				this.tail = this.tail.left;
				this.tail.right = null;
			}else {
				temp.right.left = temp.left;
				temp.left.right = temp.right;
			}					
		}
		size--;
	}

	public boolean remove(MyData data) {
		NodeDL temp = head;
		while (temp != null) {
			if (temp.data.equals(data)) {
				removeANode(temp);
				return true;
			} else {
				temp = temp.right;
			}
		}
		return false;
	}

	public int sizeOf() {
		return size;
	}

	public int indexOf(MyData data) {
		NodeDL temp = head;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(data)) {
				return i;
			}
			temp = temp.right;
		}
		return -1;
	}

	public MyData get(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.right;
			}
			return temp.data;
		} else
			return null;
	}

	// 데이터 변경하기
	public void set(int index, MyData data) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.right;
			}
			temp.data = data;
		}
	}

	public void sort() {
		MyLinkedList3 tempList = new MyLinkedList3();
		NodeDL temp = head;
		while (temp != null) {
			tempList.addInOrder(temp.data);
			temp = temp.right;
		}
		this.head = tempList.head;
		this.tail = tempList.tail;

	}

	private void addInOrder(MyData data) {
		if(head == null || data.compareTo(head.data)>0) { // data가 head.data보다 큰 경우
			addFirst(data);
		}else {
			NodeDL newNode = new NodeDL(data);
			NodeDL temp = head;
			while(temp != null && temp.data.compareTo(data)>0) { // data가 temp.data보다 작은 경우
				temp = temp.right;
			}
			if(temp!= null) {
				temp.left.right = newNode;
				newNode.left = temp.left;
				temp.left = newNode;
				newNode.right = temp;
			}else { // temp == null
				//addLast
				tail.right = newNode;
				newNode.left = tail;
				tail = newNode;
			}
		}
	}

	public String toString() {
		String ret = " ";
		NodeDL temp = head;
		while (temp != null) {
			ret += temp.data.toString() + ",";
			temp = temp.right;
		}
		return ret;
	}
}
