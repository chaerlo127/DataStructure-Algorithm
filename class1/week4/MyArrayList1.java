package class1.week4;
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

	public int search(int data) {
		return 0;
	}

	public void add(int data) {
		if (size == currentCapacity) {
			// array ũ�� Ȯ�� <== 2�� ũ���� array�� ���� ���� �����͸� ������ �� �� data �߰�
			int [] newArray = new int [currentCapacity*2];
			for(int i=0; i<size; i++) {
				newArray[i] = elementData[i];
			}
			elementData = newArray;
			currentCapacity = currentCapacity*2;
		} else {
			elementData[size] = data;
			size++;
		}

	}

	public void add(int index, int data) {

	}

	public String remove(int index) {
		return null;
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
