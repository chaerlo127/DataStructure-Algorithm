package class1.wweek13;
import java.util.ArrayList;

public class Heap {
	ArrayList<Character> heap = new ArrayList<>();
	
	
	public Heap() {
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
		return 0;
	}
	public void showHeap() {
		System.out.println(heap);
	}
	
	public static void main(String[] args) {
		Heap h = new Heap();

		for (int i = 0; i < 26; i++) {
			
			h.insert((char)((int)'A'+i));
		}

		System.out.println("\n Heap Created: ");
		h.showHeap();
//		System.out.println("\n Sort [Max.heap]");
//		Character c;
//		while((c=h.delete())!=null) {
//			System.out.print(c + " ");
//		}
	}
}
