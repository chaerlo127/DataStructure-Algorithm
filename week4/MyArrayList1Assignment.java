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

	// Array 값이 int 이기 때문에 String 이 아닌 int로 값을 가져오게 만듦
	// index 값을 주면 가져오는 것
	public int get(int index) {
		return elementData[index];
	}

	// data를 주면 search해서 index 값을 찾는 것
	public int search(int data) {
		for (int i = 0; i < size; i++) {
			if (data == elementData[i]) {
				return i;
			}
		}
		return -1; // 없다면 -1을 출력하도록 함.
	}

	public void add(int data) {
		if (size == currentCapacity) {
			// array 크기 확대 <== 2배 크기의 array를 만들어서 기존 데이터를 복사한 뒤 새 data 추가
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

	// 특정 위치에 data를 add 해라
	public void add(int index, int data) {
		if (index <= currentCapacity) {
			elementData[index] = elementData[index] + data;
		}
	}

	// array의 원하는 index에 있는 값을 remove 해라
	public void remove(int index) {
		int [] newArray = new int[size-1];
		int count = 0;
		for(int i=0; i<size-1; i++) {
			if(index == i+1) {
				newArray[i] = elementData[index+1]; // 인덱스 다음 값을 넣는다.
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

	// 데이터 몇개가 추가 되어 있는지
	public int sizeOf() {
		return size;
	}

	// array의 size가 무엇인지
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
