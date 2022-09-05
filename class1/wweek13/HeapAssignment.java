package class1.wweek13;

import java.util.ArrayList;

public class HeapAssignment {
	ArrayList<Character> heap = new ArrayList<>();
	
	
	public HeapAssignment() {
		heap.add(null); // index 0 을 쓰지 않겠다.
	}
	
	public void insert(char x) {
		int emptyIndex = heap.size();
		heap.add(emptyIndex, x);
		
		heapifyUpward(emptyIndex); // 새로 들어온 데이터가 위 데이터보다 크다면 자리를 변경하도록 하는 메소드
		showHeap();
	}
	
	private void heapifyUpward(int i) {
		int parentIndex = i/2;
		if(parentIndex>0) {
			if(heap.get(i)>heap.get(parentIndex)) {
				swap(parentIndex, i);
				heapifyUpward(parentIndex);
			}
		}
	}

	private void swap(int i, int j) {
		char temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public Character delete() {
		int deleteIndex = heap.size();

		if(heap.get(1)!=null) {
			Character delete = heap.get(1);
			heap.set(1, heap.get(deleteIndex-1));
			heapifyDownward(1);
			heap.remove(deleteIndex-1);
			showHeap();
			return delete;
		}
		
		return null;
	}
	private void heapifyDownward(int i) {
		int left = i *2;
		int right = i * 2 + 1;
		if(heap.get(i)!=null && heap.size()>left && heap.size()>right) {
			if(heap.get(left)>heap.get(right)) {
				this.swap(i, left);
				heapifyDownward(left);
			}else {
				this.swap(i, right);
				heapifyDownward(right);
			}
		}
	}

	public void showHeap() {
		System.out.println(heap);
	}
	
	public static void main(String[] args) {
		HeapAssignment h = new HeapAssignment();

		for (int i = 0; i < 26; i++) {
			h.insert((char)((int)'A'+i));
		}

		System.out.println("\n Heap Created: ");
		h.showHeap();
		System.out.println("\n Sort [Max.heap]");
		Character c;
		while(((c=h.delete())!=null) && (h.heap.size()>1)) {
			System.out.print(c + " ");
		}
	}
}
