package week4;

public class MyArrayList1Assignment {
	int initCapacity = 10;
	int[] elementData;
	int size; // # of data added
	int currentCapacity;

	public MyArrayList1Assignment() {
		elementData = new int[initCapacity];
		size = 0;
		currentCapacity = initCapacity;
	}

	// Array ���� int �̱� ������ String �� �ƴ� int�� ���� �������� ����
	// index ���� �ָ� �������� ��
	public int get(int index) {
		return elementData[index];
	}

	// data�� �ָ� search�ؼ� index ���� ã�� ��
	public int search(int data) {
		for (int i = 0; i < size; i++) {
			if (data == elementData[i]) {
				return i;
			}
		}
		return -1; // ���ٸ� -1�� ����ϵ��� ��.
	}

	public void add(int data) {
		if (size == currentCapacity) {
			// array ũ�� Ȯ�� <== 2�� ũ���� array�� ���� ���� �����͸� ������ �� �� data �߰�
			int[] newArray = new int[currentCapacity * 2];
			for (int i = 0; i < size; i++) {
				newArray[i] = elementData[i];
			}
			elementData = newArray;
			currentCapacity = currentCapacity * 2;
		} else {
			elementData[size] = data;
			size++;
		}

	}

	// Ư�� ��ġ�� data�� add �ض�
	public void add(int index, int data) {
		if (index <= currentCapacity) {
			elementData[index] = elementData[index] + data;
		}
	}

	// array�� ���ϴ� index�� �ִ� ���� remove �ض�
	public void remove(int index) {
		int [] newArray = new int[size-1];
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

	// ������ ��� �߰� �Ǿ� �ִ���
	public int sizeOf() {
		return size;
	}

	// array�� size�� ��������
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
