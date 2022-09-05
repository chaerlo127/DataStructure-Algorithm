package class1.week5;
public class MyArrayList1 {
	int initCapacity = 10;
	int[] elementData;
	int size; // # of data added
	int currentCapacity;
	

	public MyArrayList1() {
		elementData = new int[initCapacity];
		size = 0;
		currentCapacity = initCapacity;
	}

	public String get(int index) {
		return null;
	}

	public int index(int data) {
		for (int i = 0; i < size; i++) {
			if (elementData[i]== data) {
				return i;
			}
		}
		return -1; // ���ٸ� -1�� ����ϵ��� ��.
	}

	public void add(int data) { // add last
		if (size == currentCapacity) {
			// array ũ�� Ȯ�� <== 2�� ũ���� array�� ���� ���� �����͸� ������ �� �� data �߰�
			int [] newArray = new int [currentCapacity*2];
			for(int i=0; i<size; i++) {
				newArray[i] = elementData[i];
			}
			elementData = newArray;
			currentCapacity = currentCapacity*2;
		} 
			elementData[size] = data;
			size++;
		

	}

	//�߰��� ����--------------------------------
	//������ ��ġ�� �߰��ϼ���
	public void add(int index, int data) {
		// �뷮�� �����ϸ� grow
		if(size == currentCapacity) {
			// array ũ�� Ȯ�� <== 2�� ũ���� array�� ���� ���� �����͸� ������ �� �� data �߰�
			int[] newArray = new int[currentCapacity * 2];
			for (int i = 0; i < size; i++) {
				newArray[i] = elementData[i];
			}
			elementData = newArray;
			currentCapacity = currentCapacity * 2;
		}
		// index ���� size�� �ϳ� �� �ڷ�, copy 
		for(int i=size;i>index-1;i--) {
			elementData[i] = elementData[i-1];
		}
		// index�� data ���
		elementData[index] = data;
		size ++;
	}

	public void remove(int index) {
//		int retValue = elementData[index];
//		for(int i = index; i<size-1;i++) {
//			elementData[i] = elementData[i+1];
//		}
//		return retValue;
		int [] newArray = new int[size];
		int count = 0;
		for(int i=0; i<size-1; i++) {
			if(index == i+1) {
				newArray[i] = elementData[index+1]; // �ε��� ���� ���� �ִ´�.
			}else if(count<i) {
				count++;
				newArray[i] = elementData[i+1];
			}else if(count==i){
				count++;
				newArray[i] = elementData[i];
			}
		}
		elementData = newArray;
		size = size-1;
	}

	public int sizeOf() {
		return size;
	}

	public int arrSize() {
		return currentCapacity;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++) {
			ret = ret + elementData[i] + ", ";
		}
		return ret;
	}

}
