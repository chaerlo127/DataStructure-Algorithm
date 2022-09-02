   package week9;

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

	
	// quickSort 구현
	public void sort() {
		quickSort(0, size-1);
	}
	
	private void quickSort(int start, int end) {
		int q;
		if(start<end) {
			q = partition(start, end);
			quickSort(start, q-1);
			quickSort(q+1, end);
		}
	}

	private int partition(int start, int end) {
		int pivot = end;
		while(start < end) {
			while(myArray[start].compareTo(myArray[pivot])<0 && start < end) {
				start ++;
			}
			while(myArray[end].compareTo(myArray[pivot])>=0 && start < end) {
				end --;
			}
			if(start < end) {
				swap(start, end);
			}
		}
		swap(pivot, end);
		return end;
	}
	
	private void swap(int i, int j) {
		MyData temp = myArray[i];
		this.myArray[i] = this.myArray[j];
		this.myArray[j] = temp;
	}


//	private int partition(int start, int end) {
//		int pivot = end;
//		int i = start;
//		int j = end;
//		for(int k = 0; k<=end; k++) {
//			if(this.myArray[k].compareTo(this.myArray[pivot])>0) {
//				i = k;
//				break;
//			}
//		}
//		for(int l = end-1; l>=0; l--) {
//			if(this.myArray[l].compareTo(this.myArray[pivot])<0) {
//				j = l;
//				break;
//			}
//		}
//		MyData temp = this.myArray[i];
//		MyData pivotArray = this.myArray[pivot];
//		this.myArray[i] = this.myArray[j];
//		this.myArray[j] = pivotArray;
//		this.myArray[pivot] = temp;
//		return j;
//	}



	// binarySearch 로 구현
	public int indexOf(MyData data) {
		return binarySearchRecursion(data, 0, size-1);
	}
	
	
	//recursion
	private int binarySearchRecursion(MyData data, int start, int end) {
		if(start>end) {
			return -1; // base condition
		}
		int mid = (start+end)/2;
		if(myArray[mid].compareTo(data)==0) {
			return mid;
		}
		else if(myArray[mid].compareTo(data)<0){
			return binarySearchRecursion(data, mid+1, end);
		} else {
			return binarySearchRecursion(data, start, mid-1);
		}
	}
	
	private int binarySearchIteration(MyData data, int start, int end) {
		while(start <= end) {
			int mid = (start+end)/2;
			if(myArray[mid].equals(data)) {
				return mid;
			}else if(myArray[mid].compareTo(data)<0) {
				start = mid + 1;
			}else {
				end = mid -1;
			}
		}
		return -1;
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++) {
			ret = ret + myArray[i].toString() + ", ";
		}
		return ret;   
	}
}
