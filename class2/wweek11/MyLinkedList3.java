package class2.wweek11;

public class MyLinkedList3 <T>{
	public class NodeDL {
		T data;
		NodeDL left, right;

		public NodeDL() {
		}

		public NodeDL(T d) {
			this.data = d;
			this.left = null;
			this.right = null;
		}


		public boolean equals(NodeDL other) {
			return this.data.equals(other.data);
		}

		public String toString() {
			return this.data.toString();
		}

	}
	
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

	public void addLast(T data) {
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

	public void addFirst(T data) {
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
	
	public void add(T data) {
		addLast(data);
	}

	public void add(int index, T data) {
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

	
	public T removeFirst() {
		if(head!=null) {
			T ret = head.data;
			removeANode(head);
			return ret;
		}else return null;
	}
	
	
	public T removeLast() {
		if(tail!=null) {
			T ret = tail.data;
			removeANode(tail);
			return ret;
		}else return null;
	}
	public T remove(int index) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.right;
			}
			T ret = temp.data;
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

	public boolean remove(T data) {
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

	public int size() {
		return size;
	}

	public int indexOf(T data) {
		NodeDL temp = head;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(data)) {
				return i;
			}
			temp = temp.right;
		}
		return -1;
	}

	public T get(int index) {
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
	public void set(int index, T data) {
		if (checkIndexValidation(index)) {
			NodeDL temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.right;
			}
			temp.data = data;
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
