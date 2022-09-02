package week7;

import java.util.Arrays;

public class MyArrayList3 {
	private int capacity;

	private MyData[] myArray;
	private int size;

	public MyArrayList3(int initialCapacity) {
		this.capacity = initialCapacity;
		this.myArray = new MyData[this.capacity];
		this.size = 0;
	}

	public int indexOf(MyData data) {
		for (int i = 0; i < size; i++) {
			if (myArray[i].equals(data)) {
				return i;
			}
		}
		return -1;
	}

	public MyData get(int index) {
		if (checkIndexValidation(index)) {
			return myArray[index];
		}
		return null;
	}

	private boolean checkIndexValidation(int index) {
		return (index >= 0 && index < size) ? true : false;
	}

	// 데이터 변경하기
	public void set(int index, MyData data) {
		if (checkIndexValidation(index)) {
			myArray[index] = data;
		}
	}

	public void addFirst(MyData data) {
		this.add(0, data);
	}

	public void addLast(MyData data) {
		this.add(data);
	}
	public void add(MyData data) { // save data tat theend of array
		if (size == this.capacity) {
			grow(this.capacity);
		}
		myArray[size] = data;
		size++;
	}

	private void grow(int increment) { // 증가량을 받는다.
		this.capacity += increment;
		myArray = Arrays.copyOf(myArray, this.capacity);
	}

	public void add(int index, MyData data) {
		if (checkIndexValidation(index)) {
			if (size == this.capacity) {
				grow(this.capacity);
			}
			makeSlot(index);
			myArray[index] = data;
			size++;
		}
	}

	private void makeSlot(int index) {
		for (int i = size - 1; i >= index; i--) {
			myArray[i + 1] = myArray[i];
		}
	}

	
	public MyData removeFirst() {
		return this.remove(0);
	}
	
	public MyData removeLast() {
		return this.remove(size-1);
	}
	public MyData remove(int index) {
		MyData ret = null;
		if(checkIndexValidation(index)) {
			ret = myArray[index];
			removeeSlot(index);
			size --;
			return ret;
		}else return null;
	}

	private void removeeSlot(int index) {
		for(int i = index; i < size -1 ; i ++) {
			myArray[i] = myArray[i+1];
		}
	}

	public boolean remove(MyData data) {
		int index = indexOf(data);
		return (remove(index) == null) ? false : true;
	}

	public int sizeOf() {
		return size;
	}
	
	public int capacity(){
		return capacity;
	}

	public void sort() {
		// Selection Sort from biggest to smaller
		for(int i = 0; i<size -1; i ++) { // index
			for(int j = i +1;  j <size ; j++) { // index와 index + 1 ~ size까지 구함.
				if(this.myArray[i].compareTo(this.myArray[j])<0) {
					swap(i, j);
				}
			}
		}
	}

	private void swap(int i, int j) {
		MyData temp = myArray[i];
		this.myArray[i] = this.myArray[j];
		this.myArray[j] = temp;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++) {
			ret = ret + myArray[i].toString() + ", ";
		}
		return ret;
	}
}
