package wweek10;

import java.util.Arrays;
import java.util.Stack;

public class MyArrayList4_quick_merge {

	public class MyData {
		String key;
		int value;
		public MyData() {
		}
		public MyData(String s, int v) {
			key = s;
			value =v;
		}
		public boolean equals(MyData that) {
			if (this.key==that.key)
				return true;
			else
				return false;
		}
		public int compareTo(MyData that) {
			if (this.key.compareTo(that.key)>0)
				return 1;
			else if (this.key.compareTo(that.key)<0)
				return -1;
			else
				 return 0;
		}
		public String toString() {
			return ""+"("+key+","+value+") ";
		}
	}

	int capacity ;
	MyData [] myArray ;
	int size;
	
	public MyArrayList4_quick_merge(int initialCapacity) {
		capacity = initialCapacity;
		myArray = new MyData[capacity];
		size = 0;
	}
	
	public int indexOf(MyData data) {
		for (int i=0;i<size;i++) {
			if (myArray[i].equals(data))
				return i;
		}
		return -1;
	}
	
	public MyData get(int index) {
		if (checkIndexValidation(index))
			return myArray[index];
		else 
			return null;
	}
	
	private boolean checkIndexValidation(int index) {
		return (index>=0 && index<size)? true:false;
	}
	
	public void set(int index, MyData data) {
		if (checkIndexValidation(index))
			myArray[index]=data;
	}
	public void addFirst(MyData data) {
		add(0, data);
	}
	public void addLast(MyData data) {
		add(data);
	}
	public void add(MyData data) { //save data at the end of array
		if (size==capacity)
			grow(capacity);
		myArray[size]=data;
		size++;
	}
	
	private void grow(int increment) { // 증가량을 받는다
		capacity =capacity+ increment;
		myArray = Arrays.copyOf(myArray,capacity);
	}
	
	public void add(int index, MyData data) {
		if (checkIndexValidation(index)) {
			if (size == capacity) 
				grow(capacity);
			makeSlot(index);
			myArray[index]=data;
			size++;
		}
	}
	
	private void makeSlot(int index) {
		for (int i=size-1; i>=index;i--)
			myArray[i+1]=myArray[i];
	}
	
	public MyData removeFirst() {
		return remove(0);
	}
	public MyData removeLast() {
		return remove(size-1);
	}
	
	public MyData remove(int index) {
		MyData ret =null;
		if (checkIndexValidation(index)) {
			ret=myArray[index];
			removeSlot(index);
			size--;
			return ret;
		}
		else 
			return null;
	}
	private void removeSlot(int index) {
		for (int i=index;i<size-1;i++)
			myArray[i]=myArray[i+1];		
	}
	
	public boolean remove(MyData data) {
		int index = indexOf(data);
		return (remove(index)==null) ? false:true ;
	}
	
	public void removeAll() {
		size=0;
	}
	
	public int sizeOf() {
		return size;
	}
	
	public int capacity() {
		return capacity;
	}
	
	public void sort() {  
		// Selection Sort from biggest to smaller..
		for (int i=0; i<size-1;i++) {
			for (int j=i+1; j<size; j++) {
				count++;
				if (myArray[i].compareTo(myArray[j])>0) {
					swap(i, j); 
				}
			}
		}
	}
	private void swap(int i, int j) {
		MyData temp = myArray[i];
		myArray[i]=myArray[j];
		myArray[j]=temp;
	}

	
	public void quickSortRecursion(int p, int r) {
		count++;
		if (p>=r)
			return;
		int q=partition(p,r);
		quickSortRecursion(p,q-1);
		quickSortRecursion(q+1,r);
	}
	class Range {
		int p, r;
		public Range(int p, int r) {
			this.p = p;
			this.r = r;
		}
	}
	public void quickSortIteration(int p, int r) {
//		Stack<Range> s = new Stack<>();
//		s.push(new Range(p, r));
//		int q; Range t;
//		while(!s.empty()) {
//			count++;
//			t = s.pop();
//			if(t.p<t.r) {
//				q = partition(t.p, t.r);
//				s.push(new Range(q+1, t.r));
//				s.push(new Range(t.p, q-1));
//				
//			}
//		}
		
		Stack<Integer> s = new Stack<>();
		s.push(p);
		s.push(r);
		int q, tp, tr;
		while(!s.isEmpty()) {
			count ++;
			tr = s.pop();
			tp = s.pop();
			if(tp < tr) {
				q = partition(tp, tr);
				s.push(q+1);
				s.push(tr);
				s.push(tp);
				s.push(q-1);
			}
		}
		
	}
	
	private int partition(int p, int r) {
		int pivot = r;
		int left=p;
		int right=r;
		
		while(left<right) {
			while(myArray[left].compareTo(myArray[pivot])<0 && left<right) {
				left++;
			}
			while(myArray[right].compareTo(myArray[pivot])>=0 && left<right) {
				right--;
			}
			if (left<right)
				swap(left, right);
		}
		swap(pivot, right);
		return right;
	}

	private void mergeSort(int p, int r) {
		count++;
		if(p<r) {
			int q = (p+r)/2;
			mergeSort(p, q);
			mergeSort(q+1, r);
			merge(p, q, r);
		}
	}
		
	private void merge(int p, int q, int r) {
		MyData [] temp = new MyData[size]; // sorted stack
		int i = p;
		int j = q + 1;
		int index = p;
		while(i<=q && j<=r) {
			if(myArray[i].compareTo(myArray[j])<0) {
				temp[index++] = myArray[i++];
			}else {
				temp[index++] = myArray[j++];
			}
		}
		while(i<=q) {
			temp[index++] = myArray[i++];
		}
		while(j<=r) {
			temp[index++] = myArray[j++];
		}
		for(int l = p; l<=r;l++) {
			myArray[l] = temp[l];
		}
	}
	
	public void mergeSortIteration(int p, int r) {

	}

	public String toString() {
		String ret = "";
		for (int i=0;i<size;i++)
			ret = ret + myArray[i].toString()+" ";
		return ret;
	}
	
	int count=0;
	public int getCount() {
		return count;
	}
	public void resetCount() {
		count=0;
	}
	public int seqSearch(MyData data) {
	
		for (int i=0;i<size;i++) {
			count++;
			if (myArray[i].equals(data))
				return i;
		}
		return -1;
	}
	
	public int binarySearch(MyData data, int start, int end) {
		count++;
		if (start>end)
			return -1;
		int m= (start+end)/2;
		if (myArray[m].equals(data))
			return m;
		else if (myArray[m].compareTo(data)>0)
			return binarySearch(data, start, m-1);
		else
			return binarySearch(data, m+1, end);
	}

	
	public static void main(String[] args) {
		int initSize = 10;
		MyArrayList4_quick_merge al = new MyArrayList4_quick_merge(initSize);
		
		int inputSize=1000;
		MyData[] input = new MyData[inputSize];
		for (int i=0; i<inputSize; i++) {
			int tempD=(int)(inputSize*10*Math.random());
			input[i] = al.new MyData("s"+tempD, tempD);
		}	
		
		for (int i=0; i<inputSize; i++) 
			al.add(input[i]);
		System.out.println(al.toString());
		
		MyData [] temp = new MyData[10];
		int nOfData=0;
		while(nOfData<10) { 
			MyData d=al.get((int)(1000*Math.random()));
			if (d!=null) {
				temp[nOfData]=d;
				nOfData++;
			}
		}
		System.out.println("\n<Sequential Search>");
		for (MyData k:temp) {
			al.resetCount();
			System.out.println(k.toString()+" - "+al.seqSearch(k)+" <= "+al.getCount());
		}
		
		al.resetCount();
		al.sort();
		System.out.println("\n<Selection Sort Count(Sequential Search)> = "+al.getCount());
		System.out.println(al.toString());
		
		System.out.println("\n<Binary Search>");
		for (MyData k:temp) {
			al.resetCount();
			System.out.println(k.toString()+" - "+al.binarySearch(k, 0, al.sizeOf()-1)+" <= "+al.getCount());
		}
		
		al.removeAll();
		System.out.println("\nInput Again !");
		for (int i=0; i<inputSize; i++) 
			al.add(input[i]);
		System.out.println(al.toString());

		al.resetCount();
		al.quickSortRecursion(0,inputSize-1);
		System.out.println("\n<QuickSort Count> = "+al.getCount());
		System.out.println(al.toString());
		
		System.out.println("\n<Binary Search>");
		for (MyData k:temp) {
			al.resetCount();
			System.out.println(k.toString()+" - "+al.binarySearch(k, 0, al.sizeOf()-1)+" <= "+al.getCount());
		}
		
		
		//merge sorting
		al.removeAll();
		System.out.println("\nInput Again !");
		for (int i=0; i<inputSize; i++) 
			al.add(input[i]);
		System.out.println(al.toString());

		al.resetCount();
		al.mergeSortIteration(0,inputSize-1);
		System.out.println("\n<mergeSort Count> = "+al.getCount());
		System.out.println(al.toString());
		
		//quick sort iteration
		al.removeAll();
		System.out.println("\nInput Again !");
		for (int i=0; i<inputSize; i++) 
			al.add(input[i]);
		System.out.println(al.toString());

		al.resetCount();
		al.quickSortIteration(0,inputSize-1);
		System.out.println("\n<quickSortIteration Count> = "+al.getCount());
		System.out.println(al.toString());
		
	}
}
